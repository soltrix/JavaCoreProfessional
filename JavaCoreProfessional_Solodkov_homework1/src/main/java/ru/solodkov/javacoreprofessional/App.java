package ru.solodkov.javacoreprofessional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("===============");
        System.out.println("task 1 String:");
        System.out.println("===============");
        String[] arraystrings = {"One", "Two", "Free", "Four", "Five"};
        for (String string : arraystrings) System.out.println(string);
        System.out.println("---------------");
        swapElements(0, 2, arraystrings);
        for (String string : arraystrings) System.out.println(string);

        System.out.println("===============");
        System.out.println("task 1 Integer:");
        System.out.println("===============");
        Integer[] arrayinteger = {1, 2, 3, 4, 5};
        for (Integer integer : arrayinteger) System.out.println(integer);
        System.out.println("---------------");
        swapElements(0, 2, arrayinteger);
        for (Integer integer : arrayinteger) System.out.println(integer);

        System.out.println("===============");
        System.out.println("task 2 (example 1):");
        System.out.println("===============");
        Integer[] array = {5, 6, 3, 1, 4, 7};
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        convertArrayToArrayList1(array, arrayList);
        for (Integer i : arrayList) System.out.println(i);

        System.out.println("===============");
        System.out.println("task 2 (example 2):");
        System.out.println("===============");
        System.out.println(convertArrayToArrayList2(array));

        System.out.println("===============");
        System.out.println("task 3:");
        System.out.println("===============");
        boxes();
    }

    private static <T> void swapElements(int element1, int element2, T[] array) {
        T tmp = array[element1];
        array[element1] = array[element2];
        array[element2] = tmp;
    }

    private static <T> void convertArrayToArrayList1(T[] array, ArrayList<T> arrayList) {
        for (T i : array) arrayList.add(i);
    }

    private static <T> List<T> convertArrayToArrayList2(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    private static void boxes() {
        Apple apple = new Apple();
        Orange orange = new Orange();
        Box<Apple> applebox = new Box<Apple>(apple);
        Box<Orange> orangebox = new Box<Orange>(orange);
        Box<Orange> orangebox1 = new Box<Orange>(orange);
        applebox.add(15);
        orangebox.add(10);
        orangebox1.add(5);
        System.out.println(applebox.getWeight());
        System.out.println(orangebox.getWeight());
        System.out.println(orangebox1.getWeight());
        System.out.println(orangebox.compare(applebox));
        System.out.println(orangebox.emptyIn(applebox));
        System.out.println(orangebox.emptyIn(orangebox1));
        System.out.println(orangebox.getWeight());
        System.out.println(orangebox1.getWeight());
    }
}
