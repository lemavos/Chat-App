package frontend;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.lemavos.chatapp.constants.CommonConstants;

import frontend.chat.Chat;
import frontend.chat.SendMessage;

public class Lobby {
    public static void lobby() {

        int axisX = CommonConstants.AXIS_X;
        int axisY = CommonConstants.AXIS_Y;
        int commonHeight = CommonConstants.COMMON_HEIGHT;
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
           SendMessage client = SendMessage.startClient();
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
