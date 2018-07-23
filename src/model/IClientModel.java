package model;

public interface IClientModel {
    boolean sendMessage(String msg);

    String getMessage();

    void setFileSystem(FileSystem fs);

    FileSystem getFileSystem();
}
