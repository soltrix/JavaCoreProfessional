package ru.solodkov.javacoreprofessional;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        // task 1
        fileStream();
        // task 2
        sequenceStream();
        // task 3
        readBook();
    }

    private static void fileStream() {
        File file = new File("task1hw3.txt");
        try (FileInputStream into = new FileInputStream(file)) {
            byte[] bw = new byte[50];
            into.read(bw);
            System.out.println(Arrays.toString(bw));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sequenceStream() {
        try (FileInputStream in1 = new FileInputStream("task2hw3_1.txt");
             FileInputStream in2 = new FileInputStream("task2hw3_2.txt");
             FileInputStream in3 = new FileInputStream("task2hw3_3.txt");
             FileInputStream in4 = new FileInputStream("task2hw3_4.txt");
             FileInputStream in5 = new FileInputStream("task2hw3_5.txt");
             FileOutputStream out = new FileOutputStream("task2hw3_out.txt")) {

            ArrayList<InputStream> al = new ArrayList<>();
            al.add(in1);
            al.add(in2);
            al.add(in3);
            al.add(in4);
            al.add(in5);

            Enumeration<InputStream> e = Collections.enumeration(al);
            SequenceInputStream seq = new SequenceInputStream(e);

            int rb;
            while ((rb = seq.read()) != -1) {
                out.write(rb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readBook() {
        Scanner sc = new Scanner(System.in);
        File file = new File("Thinking in Java 4th Edition.txt");
        int pageLength  = 1800;
        int pages = (int) file.length()/pageLength;
        byte[] buffer = new byte[pageLength];
        System.out.println("В книге " + pages + " страниц");
        System.out.println("Введите номер страницы, которую Вы хотите прочитать:");
        int page = 0;
        do {
            System.out.print(pages);
            page = sc.nextInt();
            if (page > 0)
                try (RandomAccessFile acces = new RandomAccessFile(file, "r")) {
                    acces.seek((page - 1) * pageLength);
                    acces.read(buffer);
                    System.out.print(new String(buffer));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        } while (page != 0);
    }
}
