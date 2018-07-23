package model;

import java.io.Serializable;

public class Client implements Serializable, IClientModel {
    private String name;
    private FileSystem fs;

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

    @Override
    public void setFileSystem(FileSystem fs) {
        this.fs = fs;
    }

    @Override
    public FileSystem getFileSystem() {
        return this.fs;
    }
}
