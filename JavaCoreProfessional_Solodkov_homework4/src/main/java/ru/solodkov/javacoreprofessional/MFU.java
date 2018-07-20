package ru.solodkov.javacoreprofessional;

class MFU {
    private Object printer = new Object();
    private Object scanner = new Object();

    private void doJob(Object device, int pages) {
        synchronized (device) {
            for (int i = 0; i < pages; i++)
                try {
                    System.out.printf("%s page %d:%d\n",
                            (device.equals(printer)? "Отпечатано" : "Отсканировано"),
                            i + 1, pages);
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }

    void print(int i) {
        doJob(printer, i);
    }

    void scan(int i) {
        doJob(scanner, i);
    }
}
