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

import com.feevas.aula.client.services.MessageTranslationService;
import com.feevas.aula.server.Message;
import com.feevas.aula.server.MessageType;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class MessageAreaMonitor extends Thread {

    private JTextArea messagePanel;

    public MessageAreaMonitor(JTextArea pane) {
        this.messagePanel = pane;
        this.start();
    }

    @Override
    public void run() {


        while(true) {

            if(!MessagePool.isEmpty()){
                Message message = MessagePool.getMessage();
                String stringMessage = createStringForMessage(message);
                messagePanel.append(stringMessage);
            }
        }

    }

    private String createStringForMessage(Message message) {

        if(message.getType().equals(MessageType.FILE))
            return createFileMessage(message);
        else
            return createTextMessage(message);
    }

    private String createFileMessage(Message message) {
        StringBuilder builder = new StringBuilder();
        builder.append(message.getSender());
        builder.append(" sent you a file ");
        builder.append(message.getFilename());
        return builder.toString();
    }

    private String createTextMessage(Message message) {
        StringBuilder builder = new StringBuilder();
        builder.append(message.getSender());
        builder.append(" says ");
        if(message.getWhisper()){
            builder.append("privately ");
        }
        builder.append(": ");
        builder.append(MessageTranslationService.translate(message.getContent()));
        builder.append("\n");
        return builder.toString();
    }

    public void WriteSentMessage(String text, String recipient) {
        StringBuilder builder = new StringBuilder();
        builder.append("You said ");
        if(recipient != null){
            builder.append("privately to ");
            builder.append(recipient);
        }
        builder.append(": ");
        builder.append(text);
        builder.append("\n");
        messagePanel.append(builder.toString());
    }

    public void writeSentFile(String recipient){
        messagePanel.append("you sent a file to "+recipient);
    }
}
