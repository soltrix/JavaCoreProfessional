package ru.solodkov.javacoreprofessional;

import java.util.Arrays;

public class App {
    public static void main(String[] args) throws RuntimeException {
        System.out.println(Arrays.toString(task1(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
        System.out.println(Arrays.toString(task1(new int[]{7, 5, 1, 2, 4, 1, 3, 5})));
        System.out.println(task2(new int[]{4, 2, 9}));
        System.out.println(task2(new int[]{2, 9, 4}));
    }

    private static int[] task1(int[] array) throws RuntimeException {
        for (int i = array.length - 1; i >= 0; i--)
            if (array[i] == 4) {
                int[] result = new int[array.length - (i + 1)];
                System.arraycopy(array, (i + 1), result, 0, result.length);
                return result;
            }
        throw new RuntimeException("Входной массив должен содержать хотя бы одну четверку");
    }

    private static boolean task2(int[] array) {
        boolean one = false;
        boolean four = false;
        for (int i : array)
            if (i == 1)
                one = true;
            else if (i == 4)
                four = true;
            else
                return false;
        return one && four;
    }
}
