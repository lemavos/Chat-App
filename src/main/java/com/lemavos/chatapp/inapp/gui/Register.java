package com.lemavos.chatapp.inapp.gui;

import javax.swing.*;

import com.lemavos.chatapp.authservices.EmailSender;
import com.lemavos.chatapp.authservices.Utils;
import com.lemavos.chatapp.authservices.ValCodeGen;
import com.lemavos.chatapp.constants.CommonConstants;
import com.lemavos.chatapp.entity.Client;
import com.lemavos.chatapp.repository.ClientDB;
public class Register {
    public static void register(){
        int axisX = CommonConstants.AXIS_X;
        int axisY = CommonConstants.AXIS_Y;
        int commonWidth = CommonConstants.COMMON_WIDTH;
        int commonHeight = CommonConstants.COMMON_HEIGHT;
        int fieldWidth = CommonConstants.FIELD_WIDTH;
        int especialWidth = CommonConstants.ESPECIAL_WIDTH;
        int padY = CommonConstants.PADY;
        int currentY = CommonConstants.START_Y;

        // Define UI dimensions
        JFrame root = new JFrame("Register");
        root.setSize(axisX, axisY);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLayout(null);
        root.setResizable(false);

    // USERNAME
        // Username Filed
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds((axisX - commonWidth) / 2, currentY, commonWidth, commonHeight);
        root.add(usernameLabel);
        currentY += padY;

        // Username Input
        JTextField usernameField = new JTextField();
        usernameField.setBounds((axisX - fieldWidth) / 2, currentY, fieldWidth, commonHeight);
        root.add(usernameField);
        currentY += padY;
    
    // EMAIL
        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds((axisX - commonWidth) / 2, currentY, commonWidth, commonHeight);
        root.add(emailLabel);
        currentY += padY;

        // Email Input
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

        // Password Input
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds((axisX - fieldWidth) / 2, currentY, fieldWidth, commonHeight);
        root.add(passwordField);
        currentY += padY;

    // PHONE
        // Phone Label
        JLabel phoneLabel = new JLabel("Phone (optional):");
        phoneLabel.setBounds((axisX - commonWidth) / 2, currentY, commonWidth, commonHeight);
        root.add(phoneLabel);
        currentY += padY;

        // Phone Input
        JTextField phoneField = new JTextField();
        phoneField.setBounds((axisX - fieldWidth) / 2, currentY, fieldWidth, commonHeight);
        root.add(phoneField);
        currentY += padY * 2;

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds((axisX - especialWidth) / 2, currentY, especialWidth, commonHeight);
        root.add(registerButton);
        currentY += padY;

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds((axisX - (especialWidth + 90)) / 2, currentY, (especialWidth + 90), commonHeight);
        root.add(statusLabel);
        currentY += padY * 2;

        // Login Button
        JButton loginButton = new JButton("Already have an account? Login");
        loginButton.setBounds((axisX - (especialWidth + 100)) / 2, currentY, (especialWidth + 100), commonHeight);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(false);
        root.add(loginButton);
        loginButton.addActionListener(e -> {
            root.dispose();
            Login.login();
        });

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String phone = phoneField.getText().trim();

            // Validações
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                statusLabel.setText(
                    "All fields are required. Please try again."
                );
                return;
            }

            if (!Utils.isValidEmail(email)) {
                statusLabel.setText("Invalid email format. Please try again.");
                return;
            }

            if (!phone.isEmpty() && !Utils.isValidPhone(phone)) {
                statusLabel.setText(
                    "Invalid phone number format. Please try again."
                );
                return;
            }

            if (!Utils.isValidPassword(password)) {
                statusLabel.setText(
                    "Invalid password format. Please try again."
                );
                return;
            }

            statusLabel.setText(""); // limpa status

            // Criação do client
            Client client = new Client(username, email, phone, password);
            client.setValidateCode(ValCodeGen.generateCode());

            // Envia o código por email
            EmailSender.sendValEmail(
                client.getEmail(),
                client.getValidateCode()
            );

            // Pede código por JOptionPane
            String codeInsert = JOptionPane.showInputDialog(
                root,
                "A validation code was sent to your email.\nPlease enter the code:"
            );

            if (codeInsert == null || codeInsert.trim().isEmpty()) {
                statusLabel.setText("Validation cancelled or empty.");
                return;
            }

            if (!codeInsert.equals(client.getValidateCode())) {
                statusLabel.setText("Invalid code. Registration failed.");
                return;
            }

            // Salva no banco
            ClientDB db = new ClientDB();
            db.createTableIfNotExists();
            db.createDBClient(client);

            statusLabel.setText("Client registered successfully!");
        });


        root.setVisible(true);
    }
}
