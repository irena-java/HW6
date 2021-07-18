package com.ivv;

import java.util.Arrays;
import java.util.Scanner;

public class HW6_01 {
    public static void main(String[] args) {
        final int lengthArray = 15;
        int[] startArray = createStartArray(lengthArray);
        System.out.println("Стартовый массив - " + Arrays.toString(startArray));
        Integer[] finishArray = new Integer[lengthArray];
        try {
            finishArray = fillArray(startArray, finishArray);
        } catch (LoopedException e) {
            System.out.println("Зациклилось - " + e.getMessage());
        } finally {
            System.out.println("Финишный массив - " + Arrays.toString(finishArray));
        }
    }

    private static Integer[] fillArray(int[] startArray, Integer[] finishArray) throws LoopedException {
        int startIndex;
        int index;
        for (int i = 0; i < startArray.length; i++) {
            System.out.println("Обрабатываем число " + startArray[i]);
            index = startArray[i] % startArray.length;
            startIndex = index;
            while (finishArray[index] != null){
                index += 3;
                if (index == startIndex) {
                    throw new LoopedException("На такой комбинации зациклилось.");
                }
                if (index > finishArray.length - 1) {
                    index -= finishArray.length;
                   }
                System.out.println("startIndex-" + startIndex + ", index - " + index);
            }
            finishArray[index] = startIndex;
            System.out.println(Arrays.toString(finishArray));
        }
        return finishArray;
    }

    private static int[] createStartArray(int lengthArray) {
        Scanner scanner = new Scanner(System.in);
        int[] startArray = new int[lengthArray];
        int tmp;
        System.out.println("Введите через Enter 15 чисел >15");
        for (int i = 0; i < startArray.length; i++) {
            while (true) {
                tmp = scanner.nextInt();
                if (tmp > 15) {
                    System.out.println(i + 1 + " - ОК, следующее..");
                    break;
                }
                System.out.println("Число должно быть >15");
            }
            startArray[i] = tmp;
        }
        return startArray;
    }
}