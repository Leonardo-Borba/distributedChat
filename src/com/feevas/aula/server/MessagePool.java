package com.feevas.aula.server;

import java.util.concurrent.LinkedBlockingQueue;

public class MessagePool {

    private static LinkedBlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();


    public static void queue(Message msg) {
        messageQueue.add(msg);
    }

    public static Message getMessage(){
        return messageQueue.remove();
    }

    public static boolean isEmpty(){
        return messageQueue.isEmpty();
    }
}
