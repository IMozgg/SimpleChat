package model;

import java.io.Serializable;

public class Client implements Serializable, IClientModel {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public boolean sendMessage(String msg) {
        return false;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
