package client.view;

import client.presenter.Controller;
import client.model.Operable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientForm extends JFrame implements FormOperable {
    private Operable ctrl;
    private JTextField msg;
    private JTextArea historyMessage;
    private JButton send;
    private JList<String> listClients;
    private DefaultListModel<String> clients;
    private JPanel panelSend;

    public ClientForm() {
        this.setTitle("Чат мурзилка v1.0");
        clients = new DefaultListModel<>();
        init();
    }

    private void init() {
        this.setSize(500, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        historyMessage = new JTextArea();
        historyMessage.setEditable(false);
        msg = new JTextField();
        msg.addActionListener((e) -> setActionButton(e));
        send = new JButton("отправить");
        send.addActionListener((e) -> setActionButton(e));
        listClients = new JList<>(clients);
        panelSend = new JPanel();
        listClients.add(new JScrollBar());
        Border etched = BorderFactory.createEtchedBorder();
        listClients.setBorder(etched);
        historyMessage.setBorder(etched);
        listClients.setPrototypeCellValue("                       ");

        this.add(historyMessage, BorderLayout.CENTER);
        this.add(listClients, BorderLayout.EAST);

        panelSend.setLayout(new BorderLayout());
        panelSend.add(msg, BorderLayout.CENTER);
        panelSend.add(send, BorderLayout.EAST);

        this.add(panelSend, BorderLayout.SOUTH);

        msg.requestFocus();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                getCtrl().closeConnetion();
            }
        });
    }

    private void setActionButton(ActionEvent e) {
        ctrl.send(msg.getText());
        msg.setText("");
    }

    @Override
    public void showForm() {
        this.setVisible(true);
    }

    @Override
    public void setController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public Operable getCtrl() {
        return this.ctrl;
    }

    @Override
    public JTextArea getTextArea() {
        return historyMessage;
    }

    @Override
    public DefaultListModel<String> getCurListClients() {
        return clients;
    }
}
