package com.satc.satcdisciplinabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SatcDisciplinaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatcDisciplinaBackendApplication.class, args);
    }

}
