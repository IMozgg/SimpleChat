package presenter;

import model.Client;
import view.MainForm;

public interface IClientPresenter {
    void setModel(Client client);

    Client getModel();

    void setView(MainForm form);

    MainForm getView();
}
