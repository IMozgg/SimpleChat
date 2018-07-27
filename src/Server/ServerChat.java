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
        } catch (IOException e) {
            System.err.println("Невозможно создать серверный сокет (возможно сервер уже запущен)");
        }

        while (true) {
            try {
                System.out.println("Сервер запущен...");
                clients = serverSocket.accept();
                new Thread(new ThreadsSockets(clients)).start();
            } catch (IOException e) {
                System.err.println("Невозможно создать новый канал сокета");
            }
        }
    }
}