package com.example.hackaton1.limit.infrastructure;

import com.example.hackaton1.limit.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    List<Limit> findAllByUserId(Long userId);
}
