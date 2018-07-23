package view;

import presenter.ClientPresenter;

public interface IView {
    void setPresenter(ClientPresenter clientPresenter);

    void updateFormFromModel();

    void onCreate();

    ClientPresenter getPresenter();

    void open();

    void close();
}
