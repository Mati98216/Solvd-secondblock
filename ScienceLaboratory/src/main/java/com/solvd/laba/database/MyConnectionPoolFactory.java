package com.solvd.laba.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyConnectionPoolFactory implements ConnectionPoolFactory {
    @Override
    public ConnectionPool createConnectionPool(String configFileName) {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error reading database configuration", e);
        }

        String jdbcUrl = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        int poolSize = Integer.parseInt(props.getProperty("jdbc.poolSize", "10"));

        return new ConnectionPool(poolSize, jdbcUrl, username, password);
    }
}
