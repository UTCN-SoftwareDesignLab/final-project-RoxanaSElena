package com.example.demo.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.demo.UrlMapping.*;

@RestController
@RequestMapping(EXPORT_REPORT)
@RequiredArgsConstructor
public class ReportController {

    private final  PdfReportService reportServiceFactory;

    @GetMapping()
    public @ResponseBody byte[] export() throws IOException {
        System.out.println("export");
        String file = reportServiceFactory.export();
        return Files.readAllBytes(Paths.get(file));
    }
}
