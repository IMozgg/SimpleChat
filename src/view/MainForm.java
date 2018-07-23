package view;

import presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame implements IView{
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

        this.add(messageField);
        this.add(message, BorderLayout.SOUTH);

        this.setVisible(true);
        message.requestFocus();
    }

    @Override
    public void setPresenter(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
    }

    @Override
    public ClientPresenter getPresenter() {
        return this.clientPresenter;
    }
}
