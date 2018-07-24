package presenter;

import model.Client;
import view.Deamon;
import view.EventEnum;
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
        Thread deamon = new Thread(new Deamon(this));
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

    @Override
    public synchronized void updateFormToModel() {

    }

    @Override
    public void updateModelToFrom(EventEnum e) {
        //Обновление формы если при событии onCreate
        if (e == EventEnum.ON_CREATE) {
            getView().getTextArea().setText(getHistory());
        } else {
            getView().getTextArea().setText(getView().getTextArea().getText() + getLastMessage());
        }
        getModel().addLocalHistory(getLastMessage().toString());
    }
}
