package ru.solodkov.javacoreprofessional;

public class App {
    private static final Object monitor = new Object();
    private static char char1 = 'A';

    public static void main(String[] args) {
        // task 1
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

        // task 2
        FileWriterThread t1 = new FileWriterThread();
        FileWriterThread t2 = new FileWriterThread();
        FileWriterThread t3 = new FileWriterThread();
        t1.start();
        t2.start();
        t3.start();

        // task 3
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
}
