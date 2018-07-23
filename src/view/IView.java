package view;

import presenter.ClientPresenter;

public interface IView {
    void setPresenter(ClientPresenter clientPresenter);

    void updateFormFromModel();

    ClientPresenter getPresenter();
}
