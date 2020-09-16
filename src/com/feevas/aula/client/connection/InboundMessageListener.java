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

import com.feevas.aula.client.message.MessagePool;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InboundMessageListener extends Thread {

    private Scanner scanner;

    public InboundMessageListener(InputStream stream) {

        scanner = new Scanner(stream);
        this.start();
    }

    @Override
    public void run() {
        String line;
        while(true){
            try{
                line = scanner.nextLine();
            }catch(NoSuchElementException e){
                break;
            }
            MessagePool.queue(line);
        }
        System.exit(0);
    }
}

