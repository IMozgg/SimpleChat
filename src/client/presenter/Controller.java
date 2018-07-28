package client.presenter;

import client.model.Model;
import client.model.Operable;
import client.view.ClientForm;

import java.io.DataInputStream;

public class Controller implements Operable {
    private Operable model;
    private ClientForm view;
    private Thread deamonGetMessage;


    public Controller() {

    }

    @Override
    public void send(String msg) {
        model.send(msg);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(ClientForm view) {
        this.view = view;
    }

    public void run() {
        view.setController(this);
        view.showForm();
        deamonGetMessage = new Thread(new DeamonGetMessage(model.getInputStream(), view.getTextArea(), view.getCurListClients()));
        deamonGetMessage.setDaemon(true);
        deamonGetMessage.start();
        model.send("setName@1122:" + model.getName());
    }

    @Override
    public void closeConnetion() {
        this.model.closeConnetion();
    }

    @Override
    public DataInputStream getInputStream() {
        return this.model.getInputStream();
    }

    @Override
    public String getName() {
        return model.getName();
    }
}
