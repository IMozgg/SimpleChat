import model.Client;
import presenter.ClientPresenter;
import view.MainForm;

public class SimpleChat {
    public static void main(String[] args) {
        Client client = new Client("Петя");
        ClientPresenter clientPresenter = new ClientPresenter();
        clientPresenter.setModel(client);
        MainForm mf = new MainForm();
        clientPresenter.setView(mf);
        clientPresenter.run();
        clientPresenter.getView().open();
    }
}
