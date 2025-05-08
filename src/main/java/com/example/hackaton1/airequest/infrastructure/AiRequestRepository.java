package com.example.hackaton1.airequest.infrastructure;

import com.example.hackaton1.airequest.domain.AiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiRequestRepository extends JpaRepository<AiRequest, Long> {
    List<AiRequest> findByUserId(Long userId);
    List<AiRequest> findByCompanyId(Long companyId);
}
