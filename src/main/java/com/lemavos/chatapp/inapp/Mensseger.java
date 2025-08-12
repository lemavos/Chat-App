package com.lemavos.chatapp.inapp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Mensseger {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4000);
        Scanner scanner = new Scanner(System.in);

        PrintStream saida = new PrintStream(socket.getOutputStream());
        saida.println(scanner.nextLine());


    }
}
