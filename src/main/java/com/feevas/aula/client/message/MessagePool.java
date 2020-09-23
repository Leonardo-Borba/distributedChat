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

package com.feevas.aula.client.message;

import com.feevas.aula.client.services.UserListService;
import com.feevas.aula.server.Message;
import com.feevas.aula.server.MessageType;

import java.util.concurrent.LinkedBlockingQueue;

public class MessagePool {

    private static LinkedBlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();


    public static void queue(String msg) {

        if(msg.startsWith(MessageType.USERLIST.getName())){
            UserListService.updateUserList(msg);
        }
        else if(msg.startsWith(MessageType.FILE.getName())){
            messageQueue.add(MessageFactory.createFileMessage(msg));
        }

        else
            messageQueue.add(MessageFactory.createMessage(msg));
    }

    public static Message getMessage(){
        return messageQueue.remove();
    }

    public static boolean isEmpty(){
        return messageQueue.isEmpty();
    }
}
