package client.presenter;

import client.model.Model;
import client.model.Operable;
import client.view.ClientForm;
import client.view.FormOperable;

public interface Configurable {
    void setModel(Model model);
    Operable getModel();
    void setView(ClientForm view);
    FormOperable getView();
}
