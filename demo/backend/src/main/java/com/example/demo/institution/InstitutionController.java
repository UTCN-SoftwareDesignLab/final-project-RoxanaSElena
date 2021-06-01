package com.example.demo.institution;

import com.example.demo.institution.model.dto.InstitutionDTO;
import com.example.demo.report.ReportServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.UrlMapping.*;

@RestController
@RequestMapping(INSTITUTION)
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<InstitutionDTO> allInstitutions() {
        return institutionService.findAll();
    }

    @PostMapping
    public InstitutionDTO create(@RequestBody InstitutionDTO institutionDTO) {
        return institutionService.create(institutionDTO);
    }

    @PutMapping()
    public InstitutionDTO edit(@RequestBody InstitutionDTO institutionDTO)
    {
        return  institutionService.edit(institutionDTO);
    }


}
