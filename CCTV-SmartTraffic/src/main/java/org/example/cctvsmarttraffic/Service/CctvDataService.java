package org.example.cctvsmarttraffic.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cctvsmarttraffic.Model.CctvData;
import org.example.cctvsmarttraffic.Repository.CctvDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CctvDataService {

    private final CctvDataRepository cctvDataRepository;

    @Autowired
    public CctvDataService(CctvDataRepository cctvDataRepository) {
        this.cctvDataRepository = cctvDataRepository;
    }

    public List<CctvData> fetchAndStoreCctvData(String countyFilter) throws IOException {
        String url = "https://cwwp2.dot.ca.gov/data/d4/cctv/cctvStatusD04.json";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode != 200) {
            throw new IOException("Failed to fetch data. HTTP Response Code: " + responseCode);
        }

        try (var inputStream = connection.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(inputStream);

            System.out.println("Fetched JSON data: " + root);

            List<CctvData> filteredData = new ArrayList<>();
            for (JsonNode node : root.get("data")) {
                JsonNode cctvNode = node.get("cctv");
                JsonNode locationNode = cctvNode.get("location");

                System.out.println("Processing node: " + cctvNode);

                // Filter by county
                String county = locationNode.get("county").asText();
                String nearbyPlace = locationNode.get("nearbyPlace").asText();
                if (countyFilter.equalsIgnoreCase(county)) {
                    CctvData cctvData = new CctvData();

                    // Map JSON fields to model fields
                    cctvData.setDeviceId(cctvNode.get("index").asText());
                    cctvData.setDeviceStatus(Boolean.parseBoolean(cctvNode.get("inService").asText())); // Convert "true" to boolean
                    String longitude = locationNode.get("longitude").asText();
                    String latitude = locationNode.get("latitude").asText();
                    cctvData.setLocation(latitude + ", " + longitude);
                    cctvData.setAddress(locationNode.get("locationName").asText());
                    cctvData.setCounty(county);
                    cctvData.setCity(nearbyPlace);
                    cctvData.setStreamingVideoURL(cctvNode.get("imageData").get("streamingVideoURL").asText());

                    // Log and save
                    System.out.println("Saving to MongoDB: " + cctvData);
                    filteredData.add(cctvData);
                    cctvDataRepository.save(cctvData);
                }
            }

            System.out.println("Filtered data saved successfully.");
            return filteredData;
        } finally {
            connection.disconnect();
        }
    }
}
