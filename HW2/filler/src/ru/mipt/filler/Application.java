package ru.mipt.filler;

import ru.mipt.model.User;
import ru.mipt.service.CsvReaderService;
import ru.mipt.service.DbInitService;

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
        CsvReaderService csvReaderService = new CsvReaderService();
        List<User> users = csvReaderService.readUsersFromCsvFile();

        try (DbInitService dbInitService = new DbInitService(DB_URL, DB_USER, DB_PASSWORD)) {
            dbInitService.createTableIfNotExists();
            dbInitService.insertUsersIntoTable(users);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
