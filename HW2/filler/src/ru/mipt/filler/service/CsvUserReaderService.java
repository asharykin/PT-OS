package ru.mipt.filler.service;

import ru.mipt.filler.mapper.UserMapper;
import ru.mipt.filler.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUserReaderService {

    private final String filePath = "data.csv";
    private final UserMapper userMapper;

    public CsvUserReaderService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> readUsersFromCsvFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // пропустим первую строку с заголовком
            String line;
            while ((line = br.readLine()) != null) {
                users.add(userMapper.mapLineToUser(line));
            }
            return users;
        } catch (IOException ex) {
            throw new RuntimeException(ex); // выбросим исключение, будет ненулевой код возврата
        }
    }
}
