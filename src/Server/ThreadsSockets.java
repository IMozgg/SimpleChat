package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadsSockets implements Runnable {
    public static int countConnections = 0;
    private final Socket socket;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private static List<ThreadsSockets> listClients = Collections.synchronizedList(new ArrayList<>());
    private String login;

    public ThreadsSockets(Socket socket) {
        this.socket = socket;
        try {
            dataInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutput = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            ThreadsSockets.countConnections++;
            login = socket.getLocalAddress().toString();
        } catch (IOException e) {
            System.err.println("Невозможно открыть поток ввода/вывода");
        }
    }

    @Override
    public void run() {
        ThreadsSockets.listClients.add(this);
        String getText;

        while (true) {
            try {
                getText = dataInput.readUTF();
                if (getText.startsWith("setName@1122:")) {
                    login = getText.substring("setName@1122:".length());
                    sendBroadcast("getName@1122:" + login);
                } else {
                    sendBroadcast(login + ":\t" + getText);
                }
            } catch (IOException e) {
                //Выйдем из цикла чтобы закрыть поток
                break;
            }
        }

        System.err.println("Клиент вышел из сети");
        ThreadsSockets.listClients.remove(this);
        ThreadsSockets.countConnections--;
    }

    private void sendBroadcast(String msg) {
        for (ThreadsSockets listClient : listClients) {
            try {
                listClient.dataOutput.writeUTF(msg);
                //listClient.dataOutput.flush();
            } catch (IOException e) {
                System.err.println("Ошибка отправки сообщения клиенту");
            }
        }
    }
}
