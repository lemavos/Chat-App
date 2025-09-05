package com.lemavos.chatapp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.lemavos.chatapp.auth.gui.Login;
import com.lemavos.chatapp.constants.CommonConstants;
import com.lemavos.chatapp.server.Server;

public class ChatAppApplication {
    public static void main(String[] args) {
        int axisX = CommonConstants.AXIS_X;
        int axisY = CommonConstants.AXIS_Y;
        int commonHeight = CommonConstants.COMMON_HEIGHT;
        int especialWidth = CommonConstants.ESPECIAL_WIDTH;
        int currentY = CommonConstants.START_Y;
        int padY = CommonConstants.PADY;

        JFrame root = new JFrame("Chat");
        root.setSize(axisX, axisY);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLayout(null);
        root.setResizable(false);

        // STATUS LABEL
        JLabel labelStatus = new JLabel("");
        labelStatus.setBounds((axisX - (especialWidth + 30)) / 2, currentY, (especialWidth + 30), commonHeight);
        root.add(labelStatus);
        currentY += padY;

        boolean serverStarted = Server.connectServer();

        if (!serverStarted) {
            // Update Status
            labelStatus.setText("Server Connection Failed!");

            // Retry BUTTON
            JButton retryButton = new JButton("Retry Connect");
            retryButton.setBounds((axisX - especialWidth) / 2, currentY, especialWidth, commonHeight);
            retryButton.addActionListener(e -> {
                if (Server.connectServer()) {
                    labelStatus.setText("Server Connected!");
                    retryButton.setEnabled(false);
                    Login.login();
                    root.dispose();
                } else {
                    labelStatus.setText("Retry Failed!");
                }
            });
            root.add(retryButton);
        } else {
            Login.login();
            root.dispose();
            
        }

        //root.setVisible(true);
    }
}


