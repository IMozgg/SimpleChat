package presenter;

import model.Client;
import view.Deamon;
import view.MainForm;

public class ClientPresenter implements IClientPresenter{
    private Client clientModel;
    private MainForm viewModel;

    public ClientPresenter() {

    }

    @Override
    public void setModel(Client client) {
        this.clientModel = client;
    }

    @Override
    public Client getModel() {
        return this.clientModel;
    }

    @Override
    public void setView(MainForm form) {
        this.viewModel = form;
    }

    @Override
    public MainForm getView() {
        return this.viewModel;
    }

    @Override
    public String getHistory() {
        return getModel().getHistory().toString();
    }

    @Override
    public void run() {
        getView().setPresenter(this);
        Thread deamon = new Thread(new Deamon(getView()));
        deamon.setDaemon(true);
        deamon.start();
    }

    @Override
    public void sendMessage(String msg) {
        getModel().sendMessage(msg);
    }

    @Override
    public StringBuilder getLastMessage() {
        return getModel().getLastMessage();
    }
}
