package Server;

import java.io.*;
import java.net.Socket;

public class ThreadsSockets implements Runnable {
    public static int countConnections = 0;
    private final Socket socket;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;


    public ThreadsSockets(Socket socket) {
        this.socket = socket;
        try  {
            dataInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutput = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            ThreadsSockets.countConnections++;
        } catch (IOException e) {
            System.err.println("Невозможно открыть поток ввода/вывода");
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                dataOutput.writeUTF("Hello new client" + ThreadsSockets.countConnections);
                dataOutput.flush();
                Thread.sleep(1000);
            } catch (IOException e) {
                System.err.println("Клиент вышел из сети");
                break;
            } catch (InterruptedException e) {
                System.err.println("Прерывание потока...");
            }
        }

        ThreadsSockets.countConnections--;
    }
}
