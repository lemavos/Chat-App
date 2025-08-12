package com.lemavos.chatapp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        Socket socket = serverSocket.accept();
        System.out.println("Client conected!");

        InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());

        PrintStream saida = new PrintStream(socket.getOutputStream());


        BufferedReader reader = new BufferedReader(inputReader);
        String menssage;
        while((menssage = reader.readLine()) != null){
            saida.println("Server: " + menssage);
        }

    }
}
