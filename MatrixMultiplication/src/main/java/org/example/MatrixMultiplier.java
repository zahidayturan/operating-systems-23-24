package org.example;

class MatrixMultiplier implements Runnable {
    private final int[][] matA;
    private final int[][] matB;
    private final int row;
    private final int[][] result;

    public MatrixMultiplier(int[][] matA, int[][] matB, int row, int[][] result) {
        this.matA = matA;
        this.matB = matB;
        this.row = row;
        this.result = result;
    }

    @Override
    public void run() {
        for (int col = 0; col < matB[0].length; col++) {
            int sum = 0;
            for (int i = 0; i < matA[0].length; i++) {
                sum += matA[row][i] * matB[i][col];
            }
            result[row][col] = sum;
        }
    }
}
