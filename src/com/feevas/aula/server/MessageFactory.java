package com.feevas.aula.server;

import java.util.Arrays;
import java.util.List;

public class MessageFactory {

    public static Message create(String str, String sender){
        Message message = new Message();

            List<String> messagePieces = MessageFactory.splitMessage(str);
            message.setSender(sender);
            message.setContent(messagePieces.get(messagePieces.size() -1));
            if(messagePieces.size() == 3){
                message.setRecipient(messagePieces.get(1));
            }

        return message;
    }

    public static Message createFileMessage(String message, String username) {

        Message msg = MessageFactory.create(message, username);
        msg.setFilename(MessageFactory.splitMessage(message).get(2));
        return msg;
    }


    private static List<String> splitMessage(String str) {
        return Arrays.asList( str.split("\\s+"));
    }

    public static Message createUserListMessage(String message, String sender) {
        Message msg = MessageFactory.create(message, sender);
        msg.setRecipient(msg.getSender());
        return msg;
    }
}
