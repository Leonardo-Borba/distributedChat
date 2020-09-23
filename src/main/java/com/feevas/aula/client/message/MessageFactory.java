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
import org.apache.commons.io.FileUtils;

import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MessageFactory {

    public static Message createMessage(String content) {
        Message msg = new Message();
        System.out.println(content);
        String[] splitMessage = MessageTranslationService.splitMessage(content);
        msg.setType(getTypeForMessage(splitMessage[0]));
        msg.setWhisper(MessageType.WHISPER.equals(msg.getType()));
        msg.setContent(splitMessage[splitMessage.length-1]);
        msg.setSender(splitMessage[1]);
        return msg;
    }

    private static MessageType getTypeForMessage(String s) {

        return Arrays.asList(MessageType.values()).stream().filter( item -> item.getName().equals(s)).findFirst().get();
    }

    public static Message createFileMessage(String msg) {

        String fileName = MessageTranslationService.splitMessage(msg)[2];
        Message message = createMessage(msg);
        message.setFilename(fileName);
        File file = new File(FileUtils.getTempDirectoryPath() + message.getFilename());
        try {
            FileUtils.writeByteArrayToFile(file, DatatypeConverter.parseBase64Binary(message.getContent()));
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
