package com.example.demo.report;

import com.example.demo.institution.InstitutionRepository;
import com.example.demo.institution.model.Institution;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.TIMES_ROMAN;

@Service
@AllArgsConstructor
public class PdfReportService{

    private final InstitutionRepository institutionRepository;

    public String export() {

        List<Institution> institutions = new ArrayList<>(institutionRepository.findAll());
        String filename = "InstitutionC.pdf";
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try{
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(TIMES_ROMAN, 14);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(50,700);
            contentStream.showText("Institutions");
            contentStream.newLine();
            for (Institution institution: institutions)
            {
                contentStream.showText("Institution's Name =" + institution.getName());
                contentStream.newLine();
                contentStream.showText("Institution's Address ="+ institution.getAddress());
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
            document.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

}