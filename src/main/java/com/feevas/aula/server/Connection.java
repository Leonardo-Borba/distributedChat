package com.feevas.aula.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

public class Connection extends Thread {

    private Socket socket;
    private Server server;
    private String username;
    private boolean isRunning = true;
    private BufferedReader input; // coisas que o cliente esta enviando
    private PrintWriter output; // coisas para mandar para o cliente

    public Connection(Socket socket) throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream());
        this.socket = socket;
        this.username = createRandomUserName();
    }


    @Override
    public void run() {
        try {
            while (isRunning){
                String message = input.readLine();
                if(message != null) {
                    System.out.println(message);
                    if (message.startsWith("!!quit")) {
                        isRunning = false;
                    } else
                        handleMessage(message);
                }
            }
        } catch (Exception e){}
    }

    private void handleMessage(String message) {
        if(message != null) {
            if(message.startsWith(MessageType.NAME.getName())){
                setUsername(message);
            }else if(message.startsWith(MessageType.MESSAGE.getName())) {
                MessagePool.queue(MessageFactory.create(message, this.username));
            }else if(message.startsWith(MessageType.FILE.getName())){
                MessagePool.queue(MessageFactory.createFileMessage(message, this.username));
            }else if(message.startsWith(MessageType.USERLIST.getName()))
            {
                MessagePool.queue(MessageFactory.createUserListMessage(message, this.username));
            }
        }
    }

    public void sendMessage(Message message) {

        if(!message.getSender().equals(this.getUsername())) {
            StringBuilder builder = new StringBuilder();

            builder.append(message.getSender());
            builder.append(" ");
            if(message.getFilename() != null){
                builder.append(message.getFilename());
                builder.append(" ");
            }
            builder.append(message.getContent());
            output.println(getMessagePrefix(message) + builder.toString());
            output.flush();
        }
    }

    private String getMessagePrefix(Message message) {
        String retrn = "";
        switch (message.getType()) {
            case MESSAGE:
                retrn = message.getWhisper() ? MessageType.WHISPER.getName() : MessageType.MESSAGE.getName();
                break;
            default:
                retrn =  message.getType().getName();
                break;
        }
        return retrn+ " ";
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String message){
        String[] mPieces = message.split("\\s+");
        this.username = mPieces[1];

    }

    private String createRandomUserName() {
        String randomString = "";

        final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890".toCharArray();
        final Random random = new Random();
        for (int i = 0; i < 15; i++) {
            randomString = randomString + chars[random.nextInt(chars.length)];
        }

        return randomString;
    }
}
