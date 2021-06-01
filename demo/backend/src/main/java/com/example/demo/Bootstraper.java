package com.example.demo;

import com.example.demo.donation.DonationRepository;
import com.example.demo.institution.InstitutionRepository;
import com.example.demo.institution.model.Institution;
import com.example.demo.security.AuthService;
import com.example.demo.security.dto.SignupRequest;
import com.example.demo.user.RoleRepository;
import com.example.demo.user.UserRepository;
import com.example.demo.user.model.ERole;
import com.example.demo.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstraper implements ApplicationListener<ApplicationReadyEvent> {

    private final InstitutionRepository institutionRepository;

    private final DonationRepository donationRepository;
    
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;


    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            institutionRepository.deleteAll();
            donationRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("alex@email.com")
                    .username("alex")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex1@gmail.com")
                    .username("alex1")
                    .password("WooHoo1!")
                    .roles(Set.of("DONOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("suster.elenaroxana@gmail.com")
                    .username("alex2")
                    .password("WooHoo1!")
                    .roles(Set.of("VOLUNTEER"))
                    .build());
        }
        institutionRepository.save(Institution.builder().name("Hospice")
                .address("Fagaras")
                .needDonation(true)
                .build());
    }
}


