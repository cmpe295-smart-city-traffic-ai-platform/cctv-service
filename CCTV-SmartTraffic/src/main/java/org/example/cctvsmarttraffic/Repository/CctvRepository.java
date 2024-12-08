package org.example.cctvsmarttraffic.Repository;

import org.example.cctvsmarttraffic.Model.Cctv;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CctvRepository extends JpaRepository<Cctv, UUID> {

    // Find all active devices
    List<Cctv> findByActive(Boolean active);

    // Find devices by user ID
    List<Cctv> findByUserId(UUID userId);
}
