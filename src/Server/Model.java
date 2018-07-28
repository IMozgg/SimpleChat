package Server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Model implements Operable {
    private final int port;
    private final String host;
    private DataInputStream dataInput = null;
    private DataOutputStream dataOutput = null;
    private Socket client = null;
    private String name;

    public Model(int port, String host, String name) {
        this.port = port;
        this.host = host;
        this.name = name;
        init();
    }

    private void init() {
        try {
            //  Создаем соккет для соединения с сервером
            client = new Socket(host, port);

            // Получаем потоки ввода/вывода сокета
            dataInput = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            dataOutput = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Хост не существует");
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода. Нет подключения к серверу");
            closeConnetion();
        }
    }

    @Override
    public void send(String msg) {
        try {
            dataOutput.writeUTF(msg);
            dataOutput.flush();
        } catch (IOException e) {
            System.err.println("Ошибка передачи сообщения");
        }
    }

    @Override
    public void closeConnetion() {
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

    @Override
    public DataInputStream getInputStream() {
        return this.dataInput;
    }

    @Override
    public String getName() {
        return name;
    }
}
