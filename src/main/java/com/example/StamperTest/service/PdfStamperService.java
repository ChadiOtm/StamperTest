package com.example.StamperTest.service;

import com.pdftron.pdf.*;
import com.pdftron.sdf.SDFDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.io.ByteArrayInputStream;

@Service
public class PdfStamperService {
    private final Logger logger = LoggerFactory.getLogger(PdfStamperService.class);

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] stampPdf(byte[] fileData, String output_path, String input_filename, String text) {
        PDFNet.initialize("demo:1694443745717:7c0d7a5c0300000000b523400fba39969ad0bb34bfc8973ab3647fceeb");

        try (PDFDoc doc = new PDFDoc(new ByteArrayInputStream(fileData))) {
            doc.initSecurityHandler();

            Stamper s = new Stamper(Stamper.e_relative_scale, 0.5, -0.5);
            s.setAlignment(Stamper.e_horizontal_center, Stamper.e_vertical_bottom);
            s.setFont(Font.create(doc, Font.e_courier_bold_oblique, true));
            ColorPt red = new ColorPt(1, 0, 0, 0);
            s.setFontColor(red); // set color to red
            s.setAsBackground(true); // set text stamp as background
            PageSet ps = new PageSet(1, 2);
            s.stampText(doc, text, ps);
            s.setAsBackground(false); // set image stamp as foreground

            byte[] stampedPdf = doc.save(SDFDoc.SaveMode.LINEARIZED, null);

            PDFNet.terminate();

            return stampedPdf;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or return an appropriate value here
            return new byte[0]; // Returning an empty byte array as an example
        }
    }

}

