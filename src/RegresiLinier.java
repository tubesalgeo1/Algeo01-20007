import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RegresiLinier {
    public static void interpolasiPolinom() throws IOException {
        float x = 0;
        float[][] matriks;

        // Baca Input
        System.out.println("Jenis input apa yang ingin diberikan: ");
        System.out.print("1. Keyboard\n2. File\n");
        int chooseInput = Utils.chooseOptionValidation(1, 2);
        if (chooseInput == 1) {
            matriks = ReadDisplayArray.readPointMatrix();
            Scanner s = new Scanner(System.in);
            System.out.print("Nilai x yang akan ditaksir = ");
            x = s.nextFloat();
        } else {
            float[][] tempMatrix = ReadDisplayArray.readFiletoMatrix();
            matriks = new float[tempMatrix.length - 1][tempMatrix[0].length];
            int m = 0;
            int n;
            for (int i = 0; i < tempMatrix.length - 1; i++) {
                n = 0;
                for (int j = 0; j < tempMatrix[0].length; j++) {
                    matriks[i][j] = tempMatrix[m][n];
                    n++;
                }
                m++;
            }
            x = tempMatrix[tempMatrix.length - 1][0];
        }

        int rows = matriks.length;
        int cols = rows + 1;

        float[][] matrix_final = new float[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            int p = 0;
            for (int j = 0; j < cols; j++) {
                if (j == rows) {
                    matrix_final[i][j] = matriks[k][1];
                } else {
                    matrix_final[i][j] = Utils.power(matriks[k][0], p);
                }
                p++;
            }
            k++;
        }

        int swap_counter[] = new int[1];
        rows = matrix_final.length;
        cols = matrix_final[0].length;

        gauss_gauss_jordan.elimination_before(matrix_final, rows, cols, swap_counter);
        gauss_gauss_jordan.gauss(matrix_final, rows, cols);
        gauss_gauss_jordan.gauss_jordan(matrix_final, rows, cols);

        String SPL = SPLInterpolasiRegresi(matrix_final, true);
        float result = 0;
        for (int i = 0; i < rows; i++) {
            result += (matrix_final[i][cols - 1] * (Utils.power(x, i)));
        }
        String resultString = SPL + "\nHasil taksiran nilai untuk x = " + x + " adalah " + result;
        ReadDisplayArray.displayOutputSPL(resultString);
    }

    public static void regresiLinier() throws IOException {
        System.out.println("Jenis input apa yang ingin diberikan: ");
        System.out.print("1. Keyboard\n2. File\n");
        int chooseInput = Utils.chooseOptionValidation(1, 2);
        float main_matriks[][];
        int n, k;
        if (chooseInput == 1) {
            main_matriks = ReadDisplayArray.readRegressionPoints();
            n = main_matriks[0].length - 1;
            k = main_matriks.length;
        } else {
            main_matriks = ReadDisplayArray.readFiletoMatrix();
            n = main_matriks[0].length - 1;
            k = main_matriks.length - 1;
        }

        float main_matrix[][] = new float[k][n + 1];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n + 1; j++) {
                main_matrix[i][j] = main_matriks[i][j];
            }
        }

        float[] xs = new float[n];

        if (chooseInput == 1) {
            Scanner s = new Scanner(System.in);
            System.out.print("Nilai x yang akan ditaksir (masukkan x sejumlah n) = ");
            for (int i = 0; i < n; i++) {
                xs[i] = s.nextFloat();
            }
        } else {
            for (int i = 0; i < n; i++) {
                xs[i] = main_matriks[main_matriks.length - 1][i];
            }
        }
        float[] array_sum_xy = new float[n + 1];
        for (int j = 0; j < n + 1; j++) {
            array_sum_xy[j] = 0;
            for (int i = 0; i < k; i++) {
                array_sum_xy[j] += main_matrix[i][j];
            }
        }

        int row_matrix2 = k;
        int col_matrix2 = n * (n + 1);
        float[][] matrix2 = new float[row_matrix2][col_matrix2];
        for (int i = 0; i < row_matrix2; i++) {
            for (int j = 0; j < col_matrix2; j++) {
                if (j < n) {
                    matrix2[i][j] = main_matrix[i][n] * main_matrix[i][j];
                } else {
                    matrix2[i][j] = main_matrix[i][(j / n) - 1] * main_matrix[i][j % n];
                }
            }
        }

        float[] array_sum = new float[col_matrix2];
        for (int j = 0; j < col_matrix2; j++) {
            array_sum[j] = 0;
            for (int i = 0; i < row_matrix2; i++) {
                array_sum[j] += matrix2[i][j];
            }
        }

        float[][] matrix_final = new float[n + 1][n + 2];
        matrix_final[0][0] = (float) k;
        matrix_final[0][n + 1] = array_sum_xy[n];
        for (int j = 1; j < n + 1; j++) {
            matrix_final[0][j] = array_sum_xy[j - 1];
        }
        for (int i = 1; i < n + 1; i++) {
            matrix_final[i][0] = array_sum_xy[i - 1];
            matrix_final[i][n + 1] = array_sum[i - 1];
        }
        int b = n;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                matrix_final[i][j] = array_sum[b];
                b++;
            }
        }

        int swap[] = new int[1];
        gauss_gauss_jordan.elimination_before(matrix_final, matrix_final.length, matrix_final[0].length, swap);
        gauss_gauss_jordan.gauss(matrix_final, matrix_final.length, matrix_final[0].length);
        gauss_gauss_jordan.gauss_jordan(matrix_final, matrix_final.length, matrix_final[0].length);

        String SPL = SPLInterpolasiRegresi(matrix_final, false);
        String resultString;
        if (SPL != "Tidak terdapat solusi persamaan linier yang memenuhi") {
            float result = matrix_final[0][n + 1];
            for (int i = 1; i < n + 1; i++) {
                result += (matrix_final[i][n + 1] * xs[i - 1]);
            }
            resultString = SPL + "\nNilai taksiran untuk x " + Arrays.toString(xs) + " adalah " + result;
        } else {
            resultString = SPL;
        }
        ReadDisplayArray.displayOutputSPL(resultString);
    }

    private static String SPLInterpolasiRegresi(float[][] matrix_final, boolean isInterpolasi) {
        String SPL = "";
        for (int i = 0; i < matrix_final.length; i++) {
            float CC = matrix_final[i][matrix_final[0].length - 1];
            if ((i == 0) && CC != 0) {
                SPL += CC + " ";
            } else if (CC < 0) {
                if (isInterpolasi) {
                    if (i == 1) {
                        SPL += "- " + (CC * -1) + "x ";
                    } else {
                        SPL += "- " + (CC * -1) + "x^" + i + " ";
                    }
                } else {
                    SPL += "- " + (CC * -1) + "x" + i + " ";
                }
            } else if (CC > 0) {
                if (isInterpolasi) {
                    if (i == 1) {
                        SPL += "+ " + CC + "x ";
                    } else {
                        SPL += "+ " + CC + "x^" + i + " ";
                    }
                } else {
                    SPL += "+ " + CC + "x" + i + " ";
                }
            }
            if (matrix_final[i][i] == 0) {
                i = matrix_final.length;
                SPL = "Tidak terdapat solusi persamaan linier yang memenuhi";
            }
        }
        return SPL;
    }
}