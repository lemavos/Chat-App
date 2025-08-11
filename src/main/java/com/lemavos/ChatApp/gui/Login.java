package com.lemavos.ChatApp.gui;

import com.lemavos.ChatApp.constants.CommonConstants;
import com.lemavos.ChatApp.db.ClientDB;
import javax.swing.*;

public class Login {
    public static void login() {
        ClientDB db = new ClientDB();

        int axisX = CommonConstants.AXIS_X;
        int axisY = CommonConstants.AXIS_Y;
        int commonWidth = CommonConstants.COMMON_WIDTH;
        int commonHeight = CommonConstants.COMMON_HEIGHT;
        int fieldWidth = CommonConstants.FIELD_WIDTH;
        int especialWidth = CommonConstants.ESPECIAL_WIDTH;
        int padY = CommonConstants.PADY;
        int currentY = CommonConstants.START_Y;

        // Define UI dimensions
        JFrame root = new JFrame("Login");
        root.setSize(axisX, axisY);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLayout(null);
        root.setResizable(false);

    // EMAIL
        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds((axisX - commonWidth) / 2, currentY, commonWidth, commonHeight);
        root.add(emailLabel);
        currentY += padY;

        // Email Field
        JTextField emailField = new JTextField();
        emailField.setBounds((axisX - fieldWidth) / 2, currentY, fieldWidth, commonHeight);
        root.add(emailField);
        currentY += padY;

    // PASSWORD
        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds((axisX - commonWidth) / 2, currentY, commonWidth, commonHeight);
        root.add(passwordLabel);
        currentY += padY;

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds((axisX - fieldWidth) / 2, currentY, fieldWidth, commonHeight);
        root.add(passwordField);
        root.setVisible(true);
        currentY += padY * 2;

    // LOGIN BUTTON
        JButton loginButton = new JButton("Login");
        loginButton.setBounds((axisX - especialWidth) / 2, currentY, especialWidth, commonHeight);
        root.add(loginButton);
        currentY += padY;

    // STATUS LABEL
        JLabel labelStatus = new JLabel("");
        labelStatus.setBounds((axisX - (especialWidth + 30)) / 2, currentY, (especialWidth + 30), commonHeight);
        root.add(labelStatus);
        currentY += padY * 2;

    // Register Button
        JButton registerButton = new JButton("Do not have an account? Register");
        registerButton.setBounds((axisX - (especialWidth + 100)) / 2, currentY, (especialWidth + 100), commonHeight);
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setOpaque(false);
        root.add(registerButton);
        registerButton.addActionListener(e -> {
            root.dispose();
            Register.register();
            
        });

    // Login Button Action
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            boolean logged = db.authenticateClient(email, password);

            if (logged) {
                labelStatus.setText("Logged in successfully!");
            } else {
                labelStatus.setText("[ ! ] Email or password incorrect.");
            }
        });
    } 
}
