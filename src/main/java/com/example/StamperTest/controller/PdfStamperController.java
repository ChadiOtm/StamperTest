package com.example.StamperTest.controller;

import com.example.StamperTest.service.PdfStamperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/stamper")
public class PdfStamperController {

    @Autowired
    private final PdfStamperService pdfStamperService;

    @Autowired
    public PdfStamperController(PdfStamperService pdfStamperService) {
        this.pdfStamperService = pdfStamperService;
    }

    @PostMapping("/stamp")
    public ResponseEntity<byte[]> stampPdf(
            @RequestBody byte[] fileData,
            @RequestParam("text") String text
    ) {
        try {
            // Call the PdfStamperService to stamp the PDF
            byte[] stampedPdf = pdfStamperService.stampPdf(fileData, text);

            if (stampedPdf != null) {
                // Create a response with the stamped PDF as the body
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "stamped.pdf");
                headers.setContentLength(stampedPdf.length);

                return new ResponseEntity<>(stampedPdf, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error stamping PDF.".getBytes());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error stamping PDF.".getBytes());
        }

    }
}
