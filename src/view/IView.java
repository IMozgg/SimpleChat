package view;

import presenter.ClientPresenter;

public interface IView {
    void setPresenter(ClientPresenter clientPresenter);

    ClientPresenter getPresenter();
}
