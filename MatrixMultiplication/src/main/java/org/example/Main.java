package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java <your.package.name>.Main <rows> <cols> <threads> [<file>]");
            return;
        }

        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        int numThreads = Integer.parseInt(args[2]);
        String fileName = (args.length == 4) ? args[3] : null;

        int[][] matA = new int[rows][cols];
        int[][] matB = new int[cols][rows];
        int[][] result = new int[rows][rows];

        if (fileName != null) {
            try {
                readMatrixFromFile(matA, matB, fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        } else {
            generateMatrix(matA, rows, cols);
            generateMatrix(matB, cols, rows);
        }

        System.out.println("Matrix A:");
        showMatrix(matA);

        System.out.println("Matrix B:");
        showMatrix(matB);

        Thread[] threads = new Thread[numThreads];
        long[] threadTimes = new long[numThreads];

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            final int startRow = i * rows / numThreads;
            final int endRow = (i +1) * rows / numThreads;

            threads[i] = new Thread(new MatrixMultiplier(matA, matB, result, startRow, endRow, threadTimes, i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.nanoTime();

        System.out.println("Result Matrix:");
        showMatrix(result);

        System.out.println("Thread execution times (ms):");
        for (int i = 0; i < numThreads; i++) {
            System.out.println("Thread " + (i+1) + ": " + threadTimes[i] / 1_000_000.0 + "ms");
        }

        System.out.println("Total processing time: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    public static void showMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void generateMatrix(int[][] matrix, int rows, int cols) {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10); // Random numbers between 0 and 9
            }
        }
    }

    public static void readMatrixFromFile(int[][] matA, int[][] matB, String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        for (int i = 0; i < matA.length; i++) {
            for (int j = 0; j < matA[0].length; j++) {
                if (scanner.hasNextInt()) {
                    matA[i][j] = scanner.nextInt();
                }
            }
        }
        for (int i = 0; i < matB.length; i++) {
            for (int j = 0; j < matB[0].length; j++) {
                if (scanner.hasNextInt()) {
                    matB[i][j] = scanner.nextInt();
                }
            }
        }
        scanner.close();
    }
}