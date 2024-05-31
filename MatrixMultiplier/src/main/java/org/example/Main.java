package org.example;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        long startTime = System.nanoTime();
        final int MAX = 5;
        final int BOUND = 10;
        int[][] matA = new int[MAX][MAX];
        int[][] matB = new int[MAX][MAX];
        int[][] result = new int[MAX][MAX];

        generateMatrix(matA, MAX, BOUND);
        generateMatrix(matB, MAX, BOUND);

        System.out.println("Matrix A:");
        showMatrix(matA);

        System.out.println("Matrix B:");
        showMatrix(matB);

        Thread[] threads = new Thread[MAX];

        for (int i = 0; i < MAX; i++) {
            threads[i] = new Thread(new MatrixMultiplier(matA, matB, i, result));
            threads[i].start();
            System.out.println("Thread ["+i+"] State : "+threads[i].getState());
            System.out.println("Thread ["+i+"] has started.");
            showMatrix(result);
        }

        for (int i = 0; i < MAX; i++) {
            try {
                threads[i].join();
                System.out.println("Thread ["+i+"] State : "+threads[i].getState());
                System.out.println("Thread["+i+"] has finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Multiplication Matrix:");
        showMatrix(result);

        long endTime = System.nanoTime();
        long differenceTimeNano = endTime - startTime;
        double differenceTimeSecond = (double) differenceTimeNano / 1_000_000_000.0;
        System.out.println("Processing time: " + differenceTimeSecond + "second");
    }

    public static void showMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void generateMatrix(int[][] matrix, int size, int bound) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(bound);
            }
        }
    }
}