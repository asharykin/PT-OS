package ru.mipt.web.service;

import org.springframework.stereotype.Service;
import ru.mipt.web.dto.HealthcheckResponseDto;

@Service
public class HealthcheckService {

    public HealthcheckResponseDto performHealthcheck() {
        HealthcheckResponseDto responseDto = new HealthcheckResponseDto();
        responseDto.setStatus("OK");
        return responseDto;
    }
}
