package Server;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port = 3310;
        String server = "localhost";
        DataInputStream dataInput = null;
        DataOutputStream dataOutput = null;
        Socket client = null;
        String readLine = "";

        try {
            //  Создаем соккет для соединения с сервером
            client = new Socket(server, port);

            // Получаем потоки ввода/вывода сокета
            dataInput = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            dataOutput = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

            // Выполняем пока все хорошо
            while (true) {
                while (dataInput.available() > 0) {
                    readLine = dataInput.readUTF();
                    System.out.println("Клиент: получил сообщение " + readLine);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода. Нет подключения к серверу");
        } finally {
            // Закроем все потоки
            // Начнем с сокета, поток потоки ввода/вывода
            try {
                if (client != null) {
                    client.close();
                    //  Закрываем потоки ввода/вывода
                    if (dataInput != null) {
                        dataInput.close();
                    }
                    if (dataOutput != null) {
                        dataOutput.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

