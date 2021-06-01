package com.example.demo.institution.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InstitutionDTO {

    private Long id;
    private String name;
    private String address;
    private boolean needDonation;
}
