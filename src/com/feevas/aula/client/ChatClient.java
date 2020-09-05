package com.feevas.aula.client;

import javax.swing.*;

public class ChatClient {
    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;
    private JButton button2;
    private JTextPane textPane2;
    private JList list1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame");
        frame.setSize(800, 600);
        frame.setContentPane(new ChatClient().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
