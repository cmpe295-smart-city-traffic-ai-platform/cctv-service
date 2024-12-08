package org.example.cctvsmarttraffic.Service;

import org.example.cctvsmarttraffic.Model.Cctv;
import org.example.cctvsmarttraffic.Repository.CctvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CctvService {

    private final CctvRepository cctvRepository;

    @Autowired
    public CctvService(CctvRepository cctvRepository) {
        this.cctvRepository = cctvRepository;
    }

    public Cctv saveCctv(Cctv cctv) {
        return cctvRepository.save(cctv);
    }

    // Retrieve all active CCTVs
    public List<Cctv> getAllActiveCctvs() {
        return cctvRepository.findByActive(true);
    }

    // Get devices by user ID
    public List<Cctv> getDevicesByUserId(UUID userId) {
        return cctvRepository.findByUserId(userId);
    }

   public Cctv updateCctv(UUID id, Cctv updatedCctv) {
       return cctvRepository.findById(id).map(existingCctv -> {
           // Update only non-null fields
           if (updatedCctv.getDeviceId() != null) {
               existingCctv.setDeviceId(updatedCctv.getDeviceId());
           }
           if (updatedCctv.getName() != null) {
               existingCctv.setName(updatedCctv.getName());
           }
           if (updatedCctv.getActive() != null) {
               existingCctv.setActive(updatedCctv.getActive());
           }
           if (updatedCctv.getUserId() != null) {
               existingCctv.setUserId(updatedCctv.getUserId());
           }
           if (updatedCctv.getLocation() != null) {
               existingCctv.setLocation(updatedCctv.getLocation());
           }
           if (updatedCctv.getAddress() != null) {
               existingCctv.setAddress(updatedCctv.getAddress());
           }
           if (updatedCctv.getCounty() != null) {
               existingCctv.setCounty(updatedCctv.getCounty());
           }
           if (updatedCctv.getCity() != null) {
               existingCctv.setCity(updatedCctv.getCity());
           }
           if (updatedCctv.getStreamingVideoURL() != null) {
               existingCctv.setStreamingVideoURL(updatedCctv.getStreamingVideoURL());
           }

           // Save and return the updated CCTV device
           return cctvRepository.save(existingCctv);
       }).orElseThrow(() -> new RuntimeException("CCTV device not found with ID: " + id));
   }


    public void deleteCctv(UUID id) {
        if (cctvRepository.existsById(id)) {
            cctvRepository.deleteById(id);
        } else {
            throw new RuntimeException("CCTV device not found with ID: " + id);
        }
    }
}

