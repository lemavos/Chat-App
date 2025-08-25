package com.lemavos.chatapp.inapp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lemavos.chatapp.constants.CommonConstants;
import com.lemavos.chatapp.inapp.chat.Chat;
import com.lemavos.chatapp.inapp.chat.SendMenssage;

public class Lobby {
    public static void lobby() {

        int axisX = CommonConstants.AXIS_X;
        int axisY = CommonConstants.AXIS_Y;
        int commonWidth = CommonConstants.COMMON_WIDTH;
        int commonHeight = CommonConstants.COMMON_HEIGHT;
        int fieldWidth = CommonConstants.FIELD_WIDTH;
        int especialWidth = CommonConstants.ESPECIAL_WIDTH;
        int padY = CommonConstants.PADY;
        int currentY = CommonConstants.START_Y;

        // Define UI dimensions
        JFrame root = new JFrame("Lobby");
        root.setSize(axisX, axisY);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLayout(null);
        root.setResizable(false);

    // Chat BUTTON
        JButton openChatButton = new JButton("Chat");
        openChatButton.setBounds((axisX - especialWidth) / 2, currentY, especialWidth, commonHeight);
        root.add(openChatButton);
        currentY += padY;

    // Chat Button Action
        openChatButton.addActionListener(e -> {
            try {
           SendMenssage client = SendMenssage.startClient();
            Chat chat = new Chat(client);
            chat.ChatUI();
            root.dispose();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    });

    root.setVisible(true);

    } 
}
