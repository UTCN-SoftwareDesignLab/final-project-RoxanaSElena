package com.example.demo.institution;

import com.example.demo.institution.mapper.InstitutionMapper;
import com.example.demo.institution.model.Institution;
import com.example.demo.institution.model.dto.InstitutionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionMapper institutionMapper;
    private final InstitutionRepository institutionRepository;

    private Institution findById(Long id)
    {
        return institutionRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Institution not found: " + id));
    }

    public List<InstitutionDTO> findAll()
    {
        return institutionRepository.findAll().stream()
                .map(institutionMapper::toDto)
                .collect(Collectors.toList());
    }

    public InstitutionDTO create(InstitutionDTO institution)
    {
        return institutionMapper.toDto(institutionRepository.save(institutionMapper.fromDto(institution)));
    }

    public InstitutionDTO edit(InstitutionDTO institutionDTO)
    {
        Institution institution = findById(institutionDTO.getId());
        institution.setNeedDonation(institutionDTO.isNeedDonation());
        return institutionMapper.toDto(institutionRepository.save(institution));
    }

}
