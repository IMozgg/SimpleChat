package client.view;

import client.presenter.Controller;

import javax.swing.*;

public interface FormOperable {
    void setController(Controller ctrl);

    void showForm();

    //  Костыль
    JTextArea getTextArea();

    // Еще один
    DefaultListModel<String> getCurListClients();
}
