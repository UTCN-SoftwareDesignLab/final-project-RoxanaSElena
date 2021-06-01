package com.example.demo.donation.model.dto;

import com.example.demo.institution.model.Institution;
import com.example.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DonationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String donorEmail;
    private String donorAddress;
    private Long donorId;
    private Long institutionId;
    private Long quantity;
    private Date expirationDate;
    private boolean needTransportation;
}
