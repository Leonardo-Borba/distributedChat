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

import java.io.PrintWriter;

public class NameObserver extends Thread {
    private final String MSG = "!!ULIST";

    private SocketConnection connection;

    public NameObserver(SocketConnection conn){
        this.connection = conn;
        this.start();
    }

    @Override
    public void run() {
        sendMessage();
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendMessage();


        }
    }

    private void sendMessage(){
        connection.sendMessage(MSG);
    }
}
