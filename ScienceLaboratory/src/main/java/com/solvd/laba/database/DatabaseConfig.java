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
        ConnectionPoolFactory factory = new MyConnectionPoolFactory();
        connectionPool = factory.createConnectionPool(PROPERTIES_FILE);
    }
}
