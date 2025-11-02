package ru.mipt.filler.service;

import ru.mipt.filler.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderService {

    private final String separator = ",";
    private final String filePath = "data.csv";

    public List<User> readUsersFromCsvFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // пропускаем первую строку с заголовком
            String line;
            while ((line = br.readLine()) != null) {
                User user = parseLineIntoUser(line);
                users.add(user);
            }
            return users;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private User parseLineIntoUser(String line) {
        String[] data = line.split(separator);
        String name = data[0].trim();
        int age = Integer.parseInt(data[1].trim());
        return new User(name, age);
    }
}
