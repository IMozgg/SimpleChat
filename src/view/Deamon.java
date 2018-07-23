package view;

public class Deamon implements Runnable {
    private MainForm view;

    public Deamon(MainForm view) {
        this.view = view;
    }

    @Override
    public void run() {
        while (MainForm.FLAG_ON_DEAMON) {
            if (view.getPresenter().getLastMessage().length() > 0) {
                view.updateFormFromModel();

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
