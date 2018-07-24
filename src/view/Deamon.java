package view;

import presenter.ClientPresenter;

public class Deamon implements Runnable {
    private ClientPresenter clientPresenter;

    public Deamon(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
    }

    @Override
    public void run() {
        while (MainForm.FLAG_ON_DEAMON) {
            if (clientPresenter.getLastMessage().length() > 0) {
                clientPresenter.updateModelToFrom(EventEnum.LIGHT_UPDATE);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
