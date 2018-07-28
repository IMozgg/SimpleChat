package client;

import client.view.ClientForm;
import client.presenter.Controller;
import client.view.LoginForm;

public class Client {
    public static void main(String[] args) {
        Controller ctrl = new Controller();
        LoginForm loginForm = new LoginForm(ctrl);

        //  Нужно дождаться создания модели, прежде чем запустить программу
        while (loginForm != null) {
            if (!loginForm.isRun()) {
                break;
            }
        }
        ClientForm view = new ClientForm();
        ctrl.setView(view);

        ctrl.run();
    }
}