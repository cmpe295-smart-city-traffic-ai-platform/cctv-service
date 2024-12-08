package org.example.cctvsmarttraffic.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cctv_data")
public class CctvData {

    @Id
    private String id; // MongoDB auto-generated ID

    private String deviceId; // Corresponds to "index"
    private Boolean deviceStatus; // Corresponds to "inService"
    private String location; // Concatenated "latitude" and "longitude"
    private String address; // Corresponds to "locationName"
    private String county; // Mapped from "county"
    private String city; // Mapped from "nearbyPlace" (note: lowercase 'city')
    private String streamingVideoURL; // Corresponds to "streamingVideoURL"

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public Boolean getDeviceStatus() { return deviceStatus; }
    public void setDeviceStatus(Boolean deviceStatus) { this.deviceStatus = deviceStatus; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCounty() { return county; }
    public void setCounty(String county) { this.county = county; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreamingVideoURL() { return streamingVideoURL; }
    public void setStreamingVideoURL(String streamingVideoURL) { this.streamingVideoURL = streamingVideoURL; }
}
