package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {
    public static void main(String[] args) {
        int serverPort = 3310;
        String name = "This is chat server";
        Socket clients; //  Сокеты клиентов

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Сервер запущен...");
        } catch (IOException e) {
            System.err.println("Невозможно создать серверный сокет (возможно сервер уже запущен)");
        }

        while (true) {
            try {
                clients = serverSocket.accept();
                System.out.println("Новый клиент");
                new Thread(new ThreadsSockets(clients)).start();
            } catch (IOException e) {
                System.err.println("Невозможно создать новый канал сокета");
            }
        }
    }
}