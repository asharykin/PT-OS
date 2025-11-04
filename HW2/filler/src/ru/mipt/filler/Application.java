package ru.mipt.filler;

import ru.mipt.filler.mapper.UserMapper;
import ru.mipt.filler.model.User;
import ru.mipt.filler.service.CsvUserReaderService;
import ru.mipt.filler.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class Application {

    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PASSWORD;

    static {
        DB_URL = String.format("jdbc:mariadb://%s:%s/%s",
                System.getenv("DB_HOST"), System.getenv("DB_PORT"), System.getenv("DB_DATABASE"));
        DB_USER = System.getenv("DB_USER");
        DB_PASSWORD = System.getenv("DB_PASSWORD");
    }

    public static void main(String[] args) {
        UserMapper userMapper = new UserMapper();

        CsvUserReaderService csvUserReaderService = new CsvUserReaderService(userMapper);
        List<User> usersFromCsv = csvUserReaderService.readUsersFromCsvFile();

        List<User> usersFromDb;
        try (UserRepository userRepository = new UserRepository(DB_URL, DB_USER, DB_PASSWORD, userMapper)) {
            userRepository.createTableIfNotExists();
            userRepository.insertUsersIntoTable(usersFromCsv);
            usersFromDb = userRepository.selectAllUsersFromTable();
        } catch (SQLException ex) {
            throw new RuntimeException(ex); // выбросим исключение, будет ненулевой код возврата
        }

        assert usersFromCsv.equals(usersFromDb); // с опцией -ea выбросит AssertionError, будет ненулевой код возврата
    }
}
