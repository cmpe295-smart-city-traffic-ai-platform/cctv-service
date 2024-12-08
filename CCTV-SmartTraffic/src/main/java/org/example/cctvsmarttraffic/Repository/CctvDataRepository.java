package org.example.cctvsmarttraffic.Repository;

import org.example.cctvsmarttraffic.Model.CctvData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CctvDataRepository extends MongoRepository<CctvData, String> {
    // No additional methods required for now
}
