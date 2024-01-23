package com.solvd.laba.database;

public interface ConnectionPoolFactory {
    ConnectionPool createConnectionPool(String configFileName);
}
