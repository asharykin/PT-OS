package ru.mipt.web.mapper;

import org.springframework.stereotype.Component;
import ru.mipt.web.dto.UserResponseDto;
import ru.mipt.web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDtoMapper {

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setName(user.getName());
        responseDto.setAge(user.getAge());
        return responseDto;
    }

    public List<UserResponseDto> toResponseDtoList(List<User> users) {
        List<UserResponseDto> responseDtos = new ArrayList<>();
        for (User user : users) {
            responseDtos.add(toResponseDto(user));
        }
        return responseDtos;
    }
}
