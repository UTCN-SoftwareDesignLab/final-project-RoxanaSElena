package com.example.demo.donation.model;

import com.example.demo.institution.model.Institution;
import com.example.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User donor;
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
    private Long quantity;
    private Date expirationDate;
    private boolean needTransportation;
}
