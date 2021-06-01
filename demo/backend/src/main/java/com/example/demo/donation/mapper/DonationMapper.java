package com.example.demo.donation.mapper;

import com.example.demo.donation.model.Donation;
import com.example.demo.donation.model.dto.DonationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DonationMapper {
    @Mappings({
            @Mapping(target = "donorId", source = "donor.id"),
            @Mapping(target = "donorEmail", source = "donor.email"),
            @Mapping(target = "donorAddress", source = "donor.address"),
            @Mapping(target = "institutionId", source = "institution.id"),
    })
    DonationDTO toDto(Donation donation);
    @Mappings({
            @Mapping(target = "donor.id", source = "donorId"),
            @Mapping(target = "donor.email", source = "donorEmail"),
            @Mapping(target = "donor.address", source = "donorAddress"),
            @Mapping(target = "institution.id", source = "institutionId"),

    })

    Donation fromDto(DonationDTO donationDTO);
}
