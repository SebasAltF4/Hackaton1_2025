package com.example.hackaton1.company;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;


    public CompanyDto create(CreateCompanyRequest request) {
        Company company = modelMapper.map(request, Company.class);
        company.setActive(true);
        return modelMapper.map(companyRepository.save(company), CompanyDto.class);
    }

    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CompanyDto.class))
                .toList();
    }

    public CompanyDto findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return modelMapper.map(company, CompanyDto.class);
    }

    public CompanyDto update(Long id, CreateCompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        company.setName(request.getName());
        company.setRuc(request.getRuc());

        return modelMapper.map(companyRepository.save(company), CompanyDto.class);
    }

    public void toggleStatus(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        company.setActive(!company.isActive());
        companyRepository.save(company);
    }

}
