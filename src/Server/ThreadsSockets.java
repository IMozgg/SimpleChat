package Server;

import client.tags.TagsMsg;

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
    private TagsMsg tags;
    //private final static String TAG_BRC_LIST_CLIENTS = "<broadcastListClients@1122>";
    //private final static String TAG_REQ_SET_NAME = "<setName@1122>";

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
                if (getText.startsWith(tags.TAG_REQ_SET_NAME.toString())) {
                    //  Проверим что это имя еще не установлено, т.е. проверка эхо сообщения
                    if (!login.equals(getText.substring(tags.TAG_REQ_SET_NAME.toString().length()))) {
                        login = getText.substring(tags.TAG_REQ_SET_NAME.toString().length());
                        //  Сформируем весь список пользователей и отправим клиентам
                        sendBroadcast(makeListClients());
                    }
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
        sendBroadcast(makeListClients());
        ThreadsSockets.countConnections--;
    }

    private String makeListClients() {
        StringBuilder sb =new StringBuilder();
        sb.append(tags.TAG_BRC_LIST_CLIENTS.toString());

        for (ThreadsSockets listClient : listClients) {
            sb.append(listClient.login).append("/");
        }

        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    private void sendBroadcast(String msg) {
        for (ThreadsSockets listClient : listClients) {
            try {
                listClient.dataOutput.writeUTF(msg);
                System.err.println("LOG: " + msg);
                listClient.dataOutput.flush();
            } catch (IOException e) {
                System.err.println("Ошибка отправки сообщения клиенту");
            }
        }
    }
}
