package com.lemavos.chatapp.server;

import java.net.InetSocketAddress;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

public class Server extends WebSocketServer {

    public Server(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Nova conexão: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Conexão fechada: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Mensagem recebida: " + message);
        conn.send("Eco: " + message); // responde pro cliente
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server WebSocket iniciado!");
    }

    // main só pra rodar o Server
    public static void main(String[] args) {
        Server server = new Server(new InetSocketAddress("localhost", 8080));
        server.start();
    }
}
