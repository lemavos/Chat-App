package com.lemavos.chatapp.auth.authservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class LocalDataManager {

    private static final String localDataPath = "data/localData.json";

    public static void saveUsername(String username) {
        File file = new File(localDataPath);
        File parent = file.getParentFile();

        if (!parent.exists()) {
            parent.mkdirs();
        }

        try (Writer writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Map<String, String> data = new HashMap<>();
            data.put("username", username);

            gson.toJson(data, writer);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
