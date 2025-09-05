package com.lemavos.chatapp.server;

import java.net.InetSocketAddress;
import org.java_websocket.server.WebSocketServer;

import com.lemavos.chatapp.constants.CommonConstants;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

public class Server extends WebSocketServer {

    public Server(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New Connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Connection Closed: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Menssage Received: " + message);
        conn.send("Eco: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server WebSocket Init!");
    }

    public static boolean connectServer() {
        try {
            String IP = CommonConstants.IP;
            int PORT = CommonConstants.PORT;

            Server server = new Server(new InetSocketAddress(IP, PORT));
            server.start();
            System.out.println("Servidor iniciado em " + IP + ":" + PORT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}