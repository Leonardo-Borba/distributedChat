package com.feevas.aula.server;

public class Message {

    private String recipient;
    private String content;
    private String sender;
    private String filename;
    private boolean whisper;

    public Message(){}

    public Message(String msg, String sender) {
        content = msg;
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean getWhisper() {
        return whisper;
    }

    public void setWhisper(boolean whisper) {
        this.whisper = whisper;
    }
}
