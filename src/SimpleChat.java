import model.Client;
import model.FileSystem;

public class SimpleChat {
    public static void main(String[] args) {
        Client client = new Client("Vasya");
        client.setFileSystem(new FileSystem());

        System.out.println(client.getFileSystem().getText());
        client.getFileSystem().setAppendText("test 3");
    }
}
