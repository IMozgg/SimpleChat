package client.model;

import java.io.DataInputStream;

public interface Operable {
    void send(String msg);

    void closeConnetion();

    //  Костыль
    DataInputStream getInputStream();

    String getName();
}
