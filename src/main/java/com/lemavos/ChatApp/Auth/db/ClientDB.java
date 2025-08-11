package com.lemavos.ChatApp.Auth.db;

import java.sql.*;

import com.lemavos.ChatApp.Auth.models.Client;

public class ClientDB {

    // function to connect to the database
    private Connection connect() {
        String url = "jdbc:sqlite:clients.db";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("[!] Error connecting to DB: " + e.getMessage());
            return null;
        }
    }

    // function to authenticate a client
    public boolean authenticateClient(String email, String password) {
        String sql = "SELECT password FROM clients WHERE email = ?";

        try (
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return password.equals(storedPassword);
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(
                "[!] Error during authentication: " + e.getMessage()
            );
            return false;
        }
    }

    public void createTableIfNotExists() {
        String sql =
            "CREATE TABLE IF NOT EXISTS clients (" +
            "id TEXT PRIMARY KEY," +
            "status INTEGER," +
            "name TEXT," +
            "email TEXT," +
            "phone TEXT," +
            "password TEXT" +
            ");";

        try (
            Connection conn = connect();
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
            System.out.println("Table 'clients' ready.");
        } catch (SQLException e) {
            System.out.println("[!] Error creating table: " + e.getMessage());
        }
    }

    public void createDBClient(Client client) {
        String sql =
            "INSERT INTO clients(id, status, name, email, phone, password) VALUES (?, ?, ?, ?, ?, ?) " +
            "ON CONFLICT(id) DO UPDATE SET " +
            "status=excluded.status, " +
            "name=excluded.name, " +
            "email=excluded.email, " +
            "phone=excluded.phone, " +
            "password=excluded.password";

        try (
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, client.getId());
            pstmt.setInt(2, client.getStatus() ? 1 : 0);
            pstmt.setString(3, client.getName());
            pstmt.setString(4, client.getEmail());
            pstmt.setString(5, client.getPhone());
            pstmt.setString(6, client.getPassword());

            pstmt.executeUpdate();
            System.out.println("Client saved: " + client.getName());
        } catch (SQLException e) {
            System.out.println("[1] Error saving client: " + e.getMessage());
        }
    }
}
