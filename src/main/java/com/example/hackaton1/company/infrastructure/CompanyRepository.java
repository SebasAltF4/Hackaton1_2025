package com.example.hackaton1.company.infrastructure;

import com.example.hackaton1.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existByRuc(String ruc);
}
