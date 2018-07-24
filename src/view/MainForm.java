package view;

import presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame implements IView{
    public volatile static boolean FLAG_ON_DEAMON = true;

    private ClientPresenter clientPresenter;
    private JTextArea messageField;
    private JTextField message;


    public MainForm() {
        this.setSize(500, 600);
        this.setTitle("Чат через ФС");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        messageField = new JTextArea();
        message = new JTextField();
        messageField.setEnabled(false);

        message.addActionListener(this::addActionForTextBox);

        this.add(messageField);
        this.add(message, BorderLayout.SOUTH);
    }

    private void addActionForTextBox(ActionEvent l) {
        getPresenter().sendMessage(message.getText());
        message.setText("");
        //getPresenter().updateModelToFrom(EventEnum.LIGHT_UPDATE);
    }

    @Override
    public void setPresenter(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
    }

    @Override
    public ClientPresenter getPresenter() {
        return this.clientPresenter;
    }

    @Override
    public void onCreate() {
        getPresenter().updateModelToFrom(EventEnum.ON_CREATE);
    }

    @Override
    public void open() {
        this.onCreate();
        this.setVisible(true);
        message.requestFocus();
    }

    @Override
    public void close() {
        this.setVisible(false);
        MainForm.FLAG_ON_DEAMON = false;
    }

    @Override
    public JTextArea getTextArea() {
        return messageField;
    }
}
