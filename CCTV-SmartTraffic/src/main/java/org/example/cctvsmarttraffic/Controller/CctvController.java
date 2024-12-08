package org.example.cctvsmarttraffic.Controller;

import org.example.cctvsmarttraffic.Model.Cctv;
import org.example.cctvsmarttraffic.Service.CctvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cctvs")
public class CctvController {

    private final CctvService cctvService;

    @Autowired
    public CctvController(CctvService cctvService) {
        this.cctvService = cctvService;
    }

    // Endpoint to create a new CCTV record
    @PostMapping("/add")
    public Cctv addCctv(@RequestBody Cctv cctv) {
        return cctvService.saveCctv(cctv);
    }

    @PostMapping
    public Cctv saveCctv(@RequestBody Cctv cctv) {
        return cctvService.saveCctv(cctv);
    }

    // API 1: Get all active CCTVs
    @GetMapping("/active")
    public List<Cctv> getAllActiveCctvs() {
        return cctvService.getAllActiveCctvs();
    }

    // API 2: Get devices created by the logged-in traffic agent
    @GetMapping("/my-devices")
    public List<Cctv> getDevicesCreatedByAgent(Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName()); // Retrieve user ID from authentication
        return cctvService.getDevicesByUserId(userId);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cctv> updateCctv(@PathVariable UUID id, @RequestBody Cctv updatedCctv) {
        try {
            Cctv updatedDevice = cctvService.updateCctv(id, updatedCctv);
            return ResponseEntity.ok(updatedDevice);  // Return the updated device
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Return 404 if the device is not found
        }
    }

    @DeleteMapping("/{id}")
    public String deleteCctv(@PathVariable UUID id) {
        cctvService.deleteCctv(id);
        return "CCTV device with ID " + id + " has been deleted.";
    }
}
