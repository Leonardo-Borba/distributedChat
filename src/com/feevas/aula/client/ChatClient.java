package com.feevas.aula.client;

import com.feevas.aula.client.connection.SocketConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatClient {
    private JPanel panel1;
    private JButton sendFileButton;
    private JTextField messageField;
    private JButton sendMessageButton;
    private JTextPane textPane2;
    private JList list1;
    private SocketConnection connection;


    public ChatClient() {

        sendMessageButton.addActionListener(
                action -> this.getConnection().sendMessage(this.messageField.getText())
        );
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame");
        frame.setSize(800, 600);
        ChatClient chatClient = new ChatClient();

        frame.setContentPane(chatClient.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        chatClient.setConnection(new SocketConnection());
    }

    public void setConnection(SocketConnection connection) {
        this.connection = connection;
    }

    public SocketConnection getConnection() {
        return connection;
    }
}
