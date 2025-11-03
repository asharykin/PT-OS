package ru.mipt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.web.dto.UserResponseDto;
import ru.mipt.web.mapper.UserDtoMapper;
import ru.mipt.web.model.User;
import ru.mipt.web.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userDtoMapper.toResponseDtoList(users);
    }
}
