package com.lemavos.chatapp.inapp.chat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.lemavos.chatapp.constants.CommonConstants;
import javax.swing.*;

public class Chat {
    private final SendMenssage client;

    public Chat(SendMenssage client) {
        this.client = client;
    }

    public void ChatUI() {
        int axisX = CommonConstants.AXIS_X;
        int axisY = CommonConstants.AXIS_Y;
        int fieldWidth = CommonConstants.FIELD_WIDTH;
        int commonHeight = CommonConstants.COMMON_HEIGHT;
        int padY = CommonConstants.PADY;
        int currentY = CommonConstants.START_Y;

        JFrame root = new JFrame("Chat");
        root.setSize(axisX, axisY);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLayout(null);
        root.setResizable(false);

        // MSG Area
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(10, 10, axisX - 20, axisY - 100);
        root.add(scrollPane);

        currentY = axisY - 80;

        // MSG Field
        JTextField msgField = new JTextField();
        msgField.setBounds(10, currentY, axisX - 120, commonHeight);
        root.add(msgField);

        // Send Button
        JButton sendButton = new JButton("Send");
        sendButton.setBounds(axisX - 100, currentY, 90, commonHeight);
        root.add(sendButton);

        sendButton.addActionListener(e -> {
            String msg = msgField.getText();
            if (!msg.isEmpty()) {
                client.send(msg);
                chatArea.append("You: " + msg + "\n");
                msgField.setText("");
            }
        });

        client.setMessageListener(message -> {
            SwingUtilities.invokeLater(() -> chatArea.append("Server: " + message + "\n"));
        });

        root.setVisible(true);
    }
}
