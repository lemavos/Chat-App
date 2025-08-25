package com.lemavos.chatapp.inapp.chat;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.lemavos.chatapp.constants.CommonConstants;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;

public class SendMenssage extends WebSocketClient {

    private Consumer<String> messageListener;

    public SendMenssage(String serverUri) throws URISyntaxException {
        super(new URI(serverUri));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log("Connect to server!");
    }

    @Override
    public void onMessage(String message) {
        log("Server: " + message);
        if (messageListener != null) messageListener.accept(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log("Connection closed reconnecting in 3 sec");
        try {
            Thread.sleep(3000);
            this.reconnect();
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void onError(Exception ex) {
        log("Connection error: ");
        ex.printStackTrace();
    }

    public void setMessageListener(Consumer<String> listener) {
        this.messageListener = listener;
    }

    private static void log(String msg) {
        System.out.println(msg);
    }

    public static SendMenssage startClient() throws Exception {
        String IP = CommonConstants.IP;
        int PORT = CommonConstants.PORT;

        SendMenssage client = new SendMenssage("ws://" + IP + ":" + PORT);
        client.connectBlocking();
        return client;
    }
}
