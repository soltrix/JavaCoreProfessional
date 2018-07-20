package ru.solodkov.javacoreprofessional;

import java.io.*;
import java.util.Scanner;

public class FileWriterThread extends Thread {
    private final File file = new File("homework4task2.txt");

    public void run() {
        synchronized (file) {
            for (int i = 0; i < 10; i++)
                try (FileWriter fileWriter = new FileWriter(file, true)) {
                    fileWriter.write(
                            Thread.currentThread().getName() + ": " + i + System.lineSeparator());
                    Thread.sleep(20);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }
}
