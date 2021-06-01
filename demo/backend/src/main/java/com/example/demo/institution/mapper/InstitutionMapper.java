package com.example.demo.institution.mapper;

import com.example.demo.institution.model.Institution;
import com.example.demo.institution.model.dto.InstitutionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    InstitutionDTO toDto(Institution institution);
    Institution fromDto (InstitutionDTO institutionDTO);
}
