package org.example;

class MatrixMultiplier implements Runnable {
    private final int[][] matA;
    private final int[][] matB;
    private final int[][] result;
    private final int startRow;
    private final int endRow;
    private final long[] threadTimes;
    private final int threadIndex;

    public MatrixMultiplier(int[][] matA, int[][] matB, int[][] result, int startRow, int endRow, long[] threadTimes, int threadIndex) {
        this.matA = matA;
        this.matB = matB;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
        this.threadTimes = threadTimes;
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();

        for (int row = startRow; row < endRow; row++) {
            for (int col = 0; col < matB[0].length; col++) {
                int sum = 0;
                for (int i = 0; i < matA[0].length; i++) {
                    sum += matA[row][i] * matB[i][col];
                }
                result[row][col] = sum;
            }
        }

        long endTime = System.nanoTime();
        threadTimes[threadIndex] = endTime - startTime;
    }
}
