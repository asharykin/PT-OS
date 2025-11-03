package ru.mipt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.web.dto.HealthcheckResponseDto;
import ru.mipt.web.service.HealthcheckService;

@RestController
public class HealthcheckController {

    private final HealthcheckService healthcheckService;

    @Autowired
    public HealthcheckController(HealthcheckService healthcheckService) {
        this.healthcheckService = healthcheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthcheckResponseDto> healthcheck() {
        HealthcheckResponseDto responseDto = healthcheckService.performHealthcheck();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
