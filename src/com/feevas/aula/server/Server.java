package com.feevas.aula.server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Optional;

public class Server extends Thread {

    private ServerSocket socket = null;
    private Integer PORT = 3000;
    private List<Connection> connections = new ArrayList<>();
    private ConnectionCatcher connectionCatcher;


    public Server() throws IOException{

        try {
            socket = new ServerSocket(this.PORT);
            this.connectionCatcher = new ConnectionCatcher(connections, socket);
        }
        catch (Exception e){}
    }

    @Override
    public void run() {

        while (true) {
            if (!MessagePool.isEmpty()){
                Message msg = MessagePool.getMessage();
                handle(msg);
            }
        }
    }


    private void handle(Message message){

        System.out.println(message.getContent());
        if(message.getRecipient() != null){
            sendPrivate(message);
        }else {
            broadcast(message);
        }
    }

    private void broadcast(Message message){
        connections.forEach( con -> con.sendMessage(message));
    }

    private void sendPrivate(Message message){
        Optional<Connection> recipient = connections.stream().filter(con -> con.getUsername().equals(message.getRecipient())).findFirst();
        recipient.ifPresent(connection -> connection.sendMessage(message));
    }

    private void getActiveUsers() {
        StringBuilder builder = new StringBuilder();
        connections.forEach(
                connection -> builder.append(connection.getUsername() + ' ')
        );
        String activeUsers = builder.toString();
        broadcast( MessageFactory.create("!!allUsers " + activeUsers, "server"));
    }
}

