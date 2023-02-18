/*
2 . Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
*/
package com.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class task02 {
    public static void main(String[] args) throws SecurityException, IOException {
        Logger task02Logger = Logger.getLogger(task02.class.getName());
        ArrayList<String> filePathDirs = new ArrayList<String>();
        filePathDirs.add("ext");
        filePathDirs.add("task02log.txt");
        String logFilePath = getFilePath(filePathDirs).toString();
        FileHandler taskFileHandler = new FileHandler(logFilePath, true);
        task02Logger.addHandler(taskFileHandler);
        SimpleFormatter task02SimpleFormatter = new SimpleFormatter();
        taskFileHandler.setFormatter(task02SimpleFormatter);
        int[] intArray = new int[10];
        intArrayRandomFilling(intArray);
        bubleSort(intArray, task02Logger);
    }

    public static void intArrayRandomFilling(int[] arr) {
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(100);
        }
    }

    public static void bubleSort(int[] arr, Logger localLogger) {
        int iterCount = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                localLogger.info(String.format("Итерация %d, состояние массива: %s\n", iterCount++,
                        Arrays.toString(arr)));
            }
        }
    }

    public static StringBuilder getFilePath(ArrayList<String> dirs) {
        String separator = System.getProperty("file.separator");
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < dirs.size() - 1; i++) {

            path.append(dirs.get(i));
            path.append(separator);
        }
        path.append(dirs.get(dirs.size() - 1));
        return path;
    }
}
