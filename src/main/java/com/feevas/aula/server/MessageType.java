package com.feevas.aula.server;

public enum MessageType {

    NAME("!!NAME"),
    MESSAGE("!!MSG"),
    FILE("!!FILE"),
    WHISPER("!!WHISPER"),
    USERLIST("!!ULIST");
    private final String name;

    /**
     * @param name
     */
    private MessageType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
