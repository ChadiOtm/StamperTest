package com.example.StamperTest.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AdvisoryManagementService", url = "http://localhost:8080") // Adjust the URL
public interface TradarClient {

    @GetMapping("/api/download")
    byte[] downloadFile(@RequestParam("advisoryDocumentId") Long advisoryDocumentId);
}

