package view;

import presenter.ClientPresenter;

import javax.swing.*;

public interface IView {
    void setPresenter(ClientPresenter clientPresenter);

    JTextArea getTextArea();

    void onCreate();

    ClientPresenter getPresenter();

    void open();

    void close();
}
