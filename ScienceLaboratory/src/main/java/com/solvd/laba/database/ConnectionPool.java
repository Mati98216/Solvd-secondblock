package com.solvd.laba.database;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private final int poolSize;
    private final BlockingQueue<DatabaseConnection> connections;
    private final AtomicInteger currentConnections = new AtomicInteger(0);
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private String jdbcUrl;
    private String username;
    private String password;

    public ConnectionPool(int poolSize, String jdbcUrl, String username, String password) {
        this.poolSize = poolSize;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.connections = new LinkedBlockingQueue<>(poolSize);
        initializeConnections();
    }

    private void initializeConnections() {
        for (int i = 0; i < poolSize; i++) {
            connections.offer(createConnection());
        }
    }

    private DatabaseConnection createConnection() {
        try {
            Connection sqlConnection = DriverManager.getConnection(jdbcUrl, username, password);
            return new DatabaseConnection(currentConnections.incrementAndGet(), sqlConnection);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create database connection", e);
        }
    }

    public DatabaseConnection getConnection() throws InterruptedException {
        DatabaseConnection connection = connections.take(); // Blocking call until a connection is available
        logger.info("Acquired Connection ID: " + connection.getConnectionId());
        return connection;
    }

    public void releaseConnection(DatabaseConnection connection) {
        try {
            if (!connection.getSqlConnection().isClosed()) {
                connections.offer(connection);
                logger.info("Released Connection ID: " + connection.getConnectionId());
            }
        } catch (SQLException e) {
            logger.severe("Failed to release connection: " + e.getMessage());
        }
    }
    public void closeAllConnections() throws SQLException {
        for (DatabaseConnection dbConnection : connections) {
            dbConnection.close();
        }
    }
    public int getCurrentConnections() {
        return currentConnections.get();
    }
}

