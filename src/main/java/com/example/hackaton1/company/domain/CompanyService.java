package com.example.hackaton1.company.domain;

import com.example.hackaton1.company.dto.CompanyDto;
import com.example.hackaton1.company.dto.CreateCompanyRequest;
import com.example.hackaton1.company.infrastructure.CompanyRepository;
import com.example.hackaton1.exceptions.ResourceConflictException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyDto createCompany(CreateCompanyRequest createCompanyRequest) {
        if(companyRepository.existByRuc(createCompanyRequest.getRuc())){
            throw new ResourceConflictException("La empresa con RUC " + createCompanyRequest.getRuc() + " ya existe");
        }
        Company company = modelMapper.map(createCompanyRequest, Company.class);
        companyRepository.save(company);
        return modelMapper.map(company, CompanyDto.class);
    }

    public List<CompanyDto> findAllCompanies(){
       return companyRepository.findAll().stream().map(company -> modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
    }

    public CompanyDto findCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
        return modelMapper.map(company, CompanyDto.class);
    }

    public CompanyDto updateCompany(Long id, CreateCompanyRequest request) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
        company.setName(request.getName());
        company.setRuc(request.getRuc());
        Company updatedCompany = companyRepository.save(company);
        return modelMapper.map(updatedCompany, CompanyDto.class);
    }

    public void toggleCompanyStatus(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
        company.setActive(!company.getActive());
    }



}
