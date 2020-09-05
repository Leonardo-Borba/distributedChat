package com.feevas.aula.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class ConnectionCatcher extends Thread {

    private List<Connection> connections;
    private ServerSocket server;

    public ConnectionCatcher(List<Connection> cons, ServerSocket ss) {
        this.connections = cons;
        this.server = ss;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("connection Catcher online");
        while (true){
            Connection con = null;
            try {
                con = new Connection(server.accept());
                System.out.println("New com.feevas.aula.server.Connection registered");
                connections.add(con);
                con.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
