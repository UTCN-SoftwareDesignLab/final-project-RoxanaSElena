package com.example.demo.donation;

import com.example.demo.donation.mapper.DonationMapper;
import com.example.demo.donation.model.Donation;
import com.example.demo.donation.model.dto.DonationDTO;
import com.example.demo.institution.InstitutionRepository;
import com.example.demo.institution.model.Institution;
import com.example.demo.user.UserRepository;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationMapper donationMapper;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;

    public List<DonationDTO> findAll()
    {
        return donationRepository.findAll().stream()
                .map(donationMapper::toDto)
                .collect(Collectors.toList());
    }

    private Donation findById(Long id)
    {
        return donationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Donation not found: " + id));
    }


    public DonationDTO create(DonationDTO donationDTO)
    {
      Donation donation = donationMapper.fromDto(donationDTO);
        User donor = userRepository.findById(donationDTO.getDonorId()).orElseThrow(() -> new EntityNotFoundException("donor not found"));
        Institution institution = institutionRepository.findById(donationDTO.getInstitutionId()).orElseThrow(() -> new EntityNotFoundException("institution not found"));
        donation.setDonor(donor);
        donation.setInstitution(institution);
        Donation save = donationRepository.save(donation);
        return donationMapper.toDto(save);
    }

    public DonationDTO edit(DonationDTO donationDTO)
    {
        Donation donation = donationRepository.findById(donationDTO.getId()).orElseThrow(()-> new EntityNotFoundException("No donation with the id required"));
        donation.setNeedTransportation(donationDTO.isNeedTransportation());
        return donationMapper.toDto(donationRepository.save(donation));
    }

    public void delete(Long id) {
        donationRepository.deleteById(id);
    }
}
