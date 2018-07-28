package client.presenter;

import Server.ThreadsSockets;
import client.tags.TagsMsg;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;

public class ManagerMessages implements Runnable {
    private DataInputStream dataInputStream;
    private JTextArea textArea;
    private DefaultListModel<String> clientList;
    private TagsMsg tags;

    public ManagerMessages(DataInputStream dataInputStream, JTextArea textArea, DefaultListModel<String> clientList) {
        this.dataInputStream = dataInputStream;
        this.textArea = textArea;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        String getMsg;
        String[] parse;

        while (true) {
            try {
                getMsg = dataInputStream.readUTF();
                if (getMsg.length() > 0) {
                    if (getMsg.startsWith(tags.TAG_BRC_LIST_CLIENTS.toString())) {
                        parse = parseOut(getMsg.substring(tags.TAG_BRC_LIST_CLIENTS.toString().length()));
                        clientList.removeAllElements();
                        for (int i = 0; i < parse.length; i++) {
                            clientList.addElement(parse[i]);
                        }
                    } else {
                        textArea.append(getMsg + "\n");
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getStackTrace());
            }
        }
    }

    private String[] parseOut(String msg) {
        String temp = msg;
        String[] result = temp.split("/");

        return result;
    }
}
