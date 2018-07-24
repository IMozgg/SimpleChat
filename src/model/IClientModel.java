package model;

public interface IClientModel {
    boolean sendMessage(String msg);

    StringBuilder getHistory();

    StringBuilder getLastMessage();

    void addLocalHistory(String msg);
}
