package client;

import client.view.ClientForm;
import client.presenter.Controller;
import client.model.Model;

public class Client {
    public static void main(String[] args) {
        Model model = new Model(3310, "localhost", "Васян");
        ClientForm view = new ClientForm();
        Controller ctrl = new Controller();
        ctrl.setModel(model);
        ctrl.setView(view);

        ctrl.run();
    }
}