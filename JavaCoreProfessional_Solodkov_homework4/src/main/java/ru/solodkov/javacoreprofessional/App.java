package ru.solodkov.javacoreprofessional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    private static final Object monitor = new Object();
    private static char char1 = 'A';

    public static void main(String[] args) throws InterruptedException {
        // task 1
        task1();

        // task 2
        task2();

        // task 3
        task3();
    }

    private static void task1() {
        System.out.println("task 1");
        new Thread(new Runnable() {
            public void run() {
                sortChar('A', 'B');
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                sortChar('B', 'C');
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                sortChar('C', 'A');
            }
        }).start();
    }

    private static void sortChar(char char2, char char3) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (char1 != char2)
                        monitor.wait();
                    System.out.print(char2);
                    char1 = char3;
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void task2() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println();
        System.out.println("task 2");
        FileWriterThread t1 = new FileWriterThread();
        FileWriterThread t2 = new FileWriterThread();
        FileWriterThread t3 = new FileWriterThread();
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);

        try {
            Scanner scanner = new Scanner(new FileInputStream("homework4task2.txt"));
            int lineNumber = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("line " + lineNumber + " :" + line);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void task3() throws InterruptedException {
        System.out.println("task 3");
        final MFU mfu = new MFU();
        new Thread(new Runnable() {
            public void run() {
                mfu.print(5);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                mfu.print(3);
            }
        }).start();

        Thread.sleep(1000);
        System.out.println();

        new Thread(new Runnable() {
            public void run() {
                mfu.scan(7);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                mfu.scan(10);
            }
        }).start();

        Thread.sleep(1000);
        System.out.println();

        new Thread(new Runnable() {
            public void run() {
                mfu.print(4);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                mfu.scan(6);
            }
        }).start();
    }
}
