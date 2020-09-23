package com.feevas.aula.client;

import com.feevas.aula.client.connection.SocketConnection;
import com.feevas.aula.client.message.MessageAreaMonitor;
import com.feevas.aula.client.message.MessagePool;
import com.feevas.aula.client.services.UserListService;
import com.feevas.aula.client.services.UsernameService;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;

public class ChatClient {
    private JPanel panel1;
    private JButton sendFileButton;
    private JTextField messageField;
    private JButton sendMessageButton;
    private JList userList;
    private JRadioButton everyoneRB;
    private JRadioButton sendPrivateRB;
    private JTextArea messageArea;
    private SocketConnection connection;
    private MessageAreaMonitor messageAreaMonitor;

    public ChatClient() {

        sendMessageButton.addActionListener(
                action -> {
                    String recipient = null;
                    if(sendPrivateRB.isSelected())
                        recipient = sendPrivateRB.getText();

                    this.getConnection().sendMessage(DatatypeConverter.printBase64Binary( this.messageField.getText().getBytes()), recipient);
                    this.messageAreaMonitor.WriteSentMessage(this.messageField.getText(), recipient);
                }
        );

        sendFileButton.addActionListener(
                action -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showDialog(this.panel1, "Select");
                    File file = fileChooser.getSelectedFile();
                    String filecontent;
                    try {
                        String recipient = null;
                        if(sendPrivateRB.isSelected())
                            recipient = sendPrivateRB.getText();
                        filecontent = DatatypeConverter.printBase64Binary(FileUtils.readFileToByteArray(file));
                        this.getConnection().sendFile(file.getName(), filecontent, recipient);
                        this.messageAreaMonitor.writeSentFile(recipient);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        userList.addListSelectionListener(
                action -> {
                    if (!action.getValueIsAdjusting() && userList.getSelectedValue() != null) {
                        sendPrivateRB.setText(userList.getSelectedValue().toString());
                    }
                }
        );

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame");
        frame.setSize(800, 600);
        ChatClient chatClient = new ChatClient();

        frame.setContentPane(chatClient.panel1);

        chatClient.setConnection(new SocketConnection());
        UsernameService.setUsername(frame, chatClient.getConnection());
        UserListService.setUserListComponent(chatClient.userList);
        chatClient.messageAreaMonitor = new MessageAreaMonitor(chatClient.messageArea);
//        JScrollPane jScrollPane = new JScrollPane(chatClient.messageArea);
//        chatClient.panel1.add(jScrollPane);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setConnection(SocketConnection connection) {
        this.connection = connection;
    }

    public SocketConnection getConnection() {
        return connection;
    }
}
