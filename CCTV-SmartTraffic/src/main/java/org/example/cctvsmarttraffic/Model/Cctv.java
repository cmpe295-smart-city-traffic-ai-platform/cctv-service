package org.example.cctvsmarttraffic.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cctv")
public class Cctv {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "device_id", nullable = true)
    private Integer deviceId;

    @Column(name = "name")
    private String name; // Add the 'name' field here



    @Column(nullable = false)
    private Boolean active;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private String location;
    private String address;
    private String county;
    private String city;

    @Column(name = "streaming_video_url")
    private String streamingVideoURL;



    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Integer getDeviceId() { return deviceId; }
    public void setDeviceId(Integer deviceId) { this.deviceId = deviceId; }

    public String getName() { return name; } // Getter for name
    public void setName(String name) { this.name = name; } // Setter for name

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

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



    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
