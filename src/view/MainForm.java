package view;

import presenter.ClientPresenter;

public class MainForm implements IView{
    private ClientPresenter clientPresenter;

    @Override
    public void setPresenter(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
    }

    @Override
    public ClientPresenter getPresenter() {
        return this.clientPresenter;
    }
}
