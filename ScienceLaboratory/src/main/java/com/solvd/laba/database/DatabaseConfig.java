package com.solvd.laba.database;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final String PROPERTIES_FILE = "config.properties";
    @Getter
    private static ConnectionPool connectionPool;

    static {
        Properties props = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Unable to find " + PROPERTIES_FILE);
            }
            props.load(input);

            String jdbcUrl = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            int poolSize = Integer.parseInt(props.getProperty("jdbc.poolSize", "10"));

            connectionPool = new ConnectionPool(poolSize, jdbcUrl, username, password);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing database configuration", e);
        }
    }

}
