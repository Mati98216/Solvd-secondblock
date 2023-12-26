package com.solvd.laba.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private int connectionId;
    private Connection connection;

    public DatabaseConnection(int connectionId, Connection connection) {
        this.connectionId = connectionId;
        this.connection = connection;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public Connection getSqlConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
