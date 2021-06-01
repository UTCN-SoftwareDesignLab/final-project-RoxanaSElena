package com.example.demo.donation;
import com.example.demo.donation.model.dto.DonationDTO;
import com.example.demo.email.EmailService;
import com.example.demo.user.RoleRepository;
import com.example.demo.user.UserRepository;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.model.ERole;
import com.example.demo.user.model.Role;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.UrlMapping.DONATION;
import static com.example.demo.UrlMapping.ENTITY;
import static com.example.demo.user.model.ERole.VOLUNTEER;

import java.util.List;

@RestController
@RequestMapping(DONATION)
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    @GetMapping
    public List<DonationDTO> allDonations() {return donationService.findAll();}

    @PostMapping
    public DonationDTO create(@RequestBody DonationDTO donationDTO)
    {
        emailService.thankYouMail(donationDTO.getDonorEmail());
        List<User> volunteers = userRepository.findAllByRoles(roleRepository.findByName(VOLUNTEER).get());
        for (User u: volunteers) {
             emailService.donationAdded(u.getEmail());
        }
        return donationService.create(donationDTO);
    }

    @PutMapping()
    public DonationDTO edit(@RequestBody DonationDTO donationDTO)
    {
        return donationService.edit(donationDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id)
    {
        donationService.delete(id);
    }

}
