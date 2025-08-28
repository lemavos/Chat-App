package com.lemavos.chatapp.auth.services;

import javax.swing.*;

public class ActivateClient {

    public static boolean activateClient(
        String correctCode,
        JFrame parentFrame
    ) {
        String codeInsert = JOptionPane.showInputDialog(
            parentFrame,
            "A validation code was sent to your email.\nPlease enter the code:"
        );

        if (codeInsert == null || codeInsert.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                parentFrame,
                "Validation cancelled or empty."
            );
            return false;
        }

        if (!codeInsert.equals(correctCode)) {
            JOptionPane.showMessageDialog(
                parentFrame,
                "Invalid validation code."
            );
            return false;
        }

        return true;
    }
}
