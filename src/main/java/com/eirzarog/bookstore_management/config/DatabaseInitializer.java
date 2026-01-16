package com.eirzarog.bookstore_management.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final DataSource dataSource;

    public DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
        log.info(">>> DatabaseInitializer START");
    }



    @Override
    public void run(String... args) throws Exception {

        Resource resource = new ClassPathResource("db/data.sql");
        String sql = new String(resource.getInputStream().readAllBytes());

        try (Connection connection = dataSource.getConnection()) {
            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    connection.prepareStatement(statement).execute();
                }
            }
        }
    }
}
