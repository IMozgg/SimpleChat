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
        updateFormFromModel();
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
    public synchronized void updateFormFromModel() {
        messageField.setText(getPresenter().getHistory());
    }

    @Override
    public void onCreate() {
        updateFormFromModel();
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
}
