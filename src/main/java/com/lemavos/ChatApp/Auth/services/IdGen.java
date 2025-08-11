// generate id's to clients

package com.lemavos.ChatApp.Auth.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class IdGen {

    public String generateId() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ssSSS");

        // Format the current date and time
        String formattedNow = now.format(formatter);

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder id = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            int index = rand.nextInt(characters.length());
            id.append(characters.charAt(index));
        }
        id.append(formattedNow); // Append the formatted date and time to the ID to prevent duplicates
        return id.toString();
    }
}
