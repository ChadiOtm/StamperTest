package com.example.StamperTest;

import com.pdftron.pdf.*;
import com.pdftron.sdf.SDFDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class StamperTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(StamperTestApplication.class, args);

	}
}
