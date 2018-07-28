package client.presenter;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;

public class DeamonGetMessage implements Runnable {
    private DataInputStream dataInputStream;
    private JTextArea textArea;
    private DefaultListModel<String> clientList;

    public DeamonGetMessage(DataInputStream dataInputStream, JTextArea textArea, DefaultListModel<String> clientList) {
        this.dataInputStream = dataInputStream;
        this.textArea = textArea;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        String getMsg;
        while (true) {
            try {
               getMsg = dataInputStream.readUTF();
                if (getMsg.length() > 0) {
                    if (getMsg.startsWith("getName@1122")) {
                        clientList.addElement(getMsg.substring("getName@1122:".length()));
                    } else {
                        textArea.append(getMsg + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
