package model;

import java.io.*;

public class FileSystem implements IFileSystem {
    final File dataBase;
    final File dir = new File("src\\dataBase");
    ;
    final File textFile = new File("src\\dataBase\\Chat.txt");

    final String pathBD;
    private BufferedReader buffR;
    private BufferedWriter buffW;

    public FileSystem() {
        pathBD = "src\\dataBase\\Chat.txt";
        dataBase = new File(pathBD);
        init();
    }

    private void init() {
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!textFile.exists()) {
            try {
                textFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода. Невозможно создать файл");
            }
        }
    }

    private boolean openWriteStream() {
        try {
            buffW = new BufferedWriter(new FileWriter(dataBase, true));
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Не найден файл ( " + dataBase.getPath() + " )");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean openReadStream() {
        try {
            buffR = new BufferedReader(new FileReader(dataBase));
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Не найден файл ( " + dataBase.getPath() + " )");
            return false;
        }
    }

    public StringBuilder getText() {
        StringBuilder tempData = new StringBuilder();
        try {
            openReadStream();
            while (buffR.ready()) {
                tempData.append(buffR.readLine()).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка Ввода/Вывода (при чтении)");
        } finally {
            try {
                if (buffR != null) {
                    buffR.close();
                }
            } catch (IOException e) {
                System.err.println("Невозможно закрыть поток чтения");
            }
        }

        return tempData;
    }

    public void setAppendText(String msg) {
        try {
            openWriteStream();
            buffW.write(msg + "\n");
        } catch (IOException e) {
            System.err.println("Ошибка Ввода/Вывода");
        } finally {
            if (buffW != null) {
                try {
                    buffW.close();
                } catch (IOException e) {
                    System.err.println("Невозможно закрыть поток записи");
                }
            }
        }
    }
}
