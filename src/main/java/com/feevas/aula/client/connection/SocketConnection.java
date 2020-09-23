/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package com.feevas.aula.client.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection {

    private final String HOST = "localhost";
    private final int PORT = 3000;
    PrintWriter out = null;
    InboundMessageListener in = null;
    Socket socket;
    private boolean isConnected = true;
    private NameObserver nameObserver;

    public SocketConnection() {
        run();
    }

    public void run(){

        try {
            socket = new Socket(HOST, PORT);
            in = new InboundMessageListener(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            nameObserver = new NameObserver(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg) {

        out.println(msg);
        out.flush();
    }


    public void sendMessage(String msg, String recipient){

        String res = recipient != null ? recipient : "";
        String finalMsg = "!!MSG "+res+" "+msg;
        sendMessage(finalMsg.replace("  ", " "));
    }


    public void setName(String showPanel) {
        this.sendMessage("!!NAME "+ showPanel);
    }

    public void sendFile(String name, String filecontent, String recipient) {

        sendMessage("!!FILE "+recipient+ " "+name+ " "+ filecontent);

    }
}
