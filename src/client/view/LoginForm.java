package client.view;

import client.model.Model;
import client.presenter.Configurable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginForm extends JFrame {
    private JLabel lblLogin;
    private JTextField fldLogin;
    private JLabel lblHost;
    private JTextField fldHost;
    private JPanel panelLogin;
    private JPanel panelHost;
    private JButton btnConnect;
    private Border borderAll;
    private Configurable ctrl;
    private volatile boolean run = true;

    public LoginForm(Configurable ctrl) throws HeadlessException {
        this.setTitle("Добро пожаловать");
        this.ctrl = ctrl;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setLayout(new GridLayout(3, 1, 10,10));

        borderAll = BorderFactory.createLoweredBevelBorder();
        GridLayout layoutGrid = new GridLayout(1, 2, 10, 10);
        panelLogin = new JPanel(layoutGrid);
        panelLogin.setBorder(borderAll);

        panelHost = new JPanel(layoutGrid);
        panelHost.setBorder(borderAll);

        lblLogin = new JLabel("Ваш никнейм:");
        lblHost = new JLabel("Сервер:");

        fldLogin = new JTextField();
        fldHost = new JTextField("localhost");

        btnConnect = new JButton("Подключиться");

        panelLogin.add(lblLogin);
        panelLogin.add(fldLogin);

        panelHost.add(lblHost);
        panelHost.add(fldHost);

        this.add(panelLogin);
        this.add(panelHost);
        this.add(btnConnect);

        fldLogin.requestFocus();
        fldLogin.addActionListener(e -> connection());
        fldHost.addActionListener(e -> connection());
        btnConnect.addActionListener(e -> connection());

        this.setVisible(true);
    }

    public boolean isRun() {
        return run;
    }

    private void connection() {
        if (fldHost.getText().length() > 0 && fldLogin.getText().length() > 0) {
            this.ctrl.setModel(new Model(3310, fldHost.getText(), fldLogin.getText()));
            this.run = false;
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Одно из полей пустое");
        }
    }
}
