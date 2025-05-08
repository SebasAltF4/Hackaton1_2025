package com.example.hackaton1.restriction.domain;

import com.example.hackaton1.company.domain.Company;
import com.example.hackaton1.company.domain.CompanyService;
import com.example.hackaton1.company.infrastructure.CompanyRepository;
import com.example.hackaton1.exceptions.ResourceNotFoundException;
import com.example.hackaton1.restriction.dto.CreateRestrictionRequest;
import com.example.hackaton1.restriction.dto.RestrictionDto;
import com.example.hackaton1.restriction.infrastructure.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestrictionService {
    private final RestrictionRepository restrictionRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public RestrictionDto createRestrictionForCompany(Long companyId, CreateRestrictionRequest createRestrictionRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + companyId));
        Restriction newRestriction = modelMapper.map(createRestrictionRequest, Restriction.class);
        newRestriction.setCompany(company);
        return modelMapper.map(restrictionRepository.save(newRestriction), RestrictionDto.class);
    }

    public List<RestrictionDto> getAllRestrictionsForCompany(Long companyId){
        if (companyRepository.existsById(companyId)){
            return restrictionRepository.findAllByCompanyId(companyId).stream().map(restriction -> modelMapper.map(restriction, RestrictionDto.class)).toList();
        }
        throw new ResourceNotFoundException("Empresa no encontrada con ID: " + companyId);
    }

    public RestrictionDto updateRestrictionForCompany(Long companyId, Long restrictionId, CreateRestrictionRequest createRestrictionRequest) {
        if (companyRepository.existsById(companyId)){
            Restriction restrictionToUpdate = restrictionRepository.findById(restrictionId).orElseThrow(() -> new ResourceNotFoundException("Restriccion no encontrada con ID: " + restrictionId));
            restrictionToUpdate.setModelName(createRestrictionRequest.getModelName());
            restrictionToUpdate.setMaxTokens(createRestrictionRequest.getMaxTokens());
            restrictionToUpdate.setMaxRequests(createRestrictionRequest.getMaxRequests());
            restrictionToUpdate.setTimeWindow(createRestrictionRequest.getTimeWindow());
            return modelMapper.map(restrictionRepository.save(restrictionToUpdate), RestrictionDto.class);
        }
        throw new ResourceNotFoundException("Empresa no encontrada con ID: " + companyId);
    }

    public void deleteRestrictionForCompany(Long companyId, Long id) {
        if (companyRepository.existsById(companyId)){
            Restriction restrictionToDelete = restrictionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restriccion no encontrada con ID: " + id));
            restrictionRepository.delete(restrictionToDelete);
        }
        throw new ResourceNotFoundException("Empresa no encontrada con ID: " + companyId);
    }
}
