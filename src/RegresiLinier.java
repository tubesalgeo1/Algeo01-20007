import java.io.IOException;
import java.util.Arrays;

public class RegresiLinier {
    public static void interpolasiPolinom() throws IOException {
        float[][] matriks = ReadDisplayArray.readInputPoint();
        ReadDisplayArray.displayOutput(matriks);
        int rows = matriks.length;
        int cols = matriks[0].length;
        int swap = 0;
        /*
         * gauss_gauss_jordan.elimination_before(matriks, rows, cols, swap);
         * gauss_gauss_jordan.gauss(matriks, rows, cols);
         * gauss_gauss_jordan.gauss_jordan(matriks, rows, cols);
         */
        ReadDisplayArray.displayOutput(matriks);
    }

    public static void regresiLinier() throws IOException {
        float[][] main_matrix = ReadDisplayArray.readInputRegressionPoints();
        int n = main_matrix[0].length - 1;
        int k = main_matrix.length;
        ReadDisplayArray.displayOutput(main_matrix);
        System.out.println("n : " + n + "  k: " + k);
        float[] array_sum_xy = new float[n + 1];
        for (int j = 0; j < n + 1; j++) {
            array_sum_xy[j] = 0;
            for (int i = 0; i < k; i++) {
                array_sum_xy[j] += main_matrix[i][j];
            }
        }
        System.out.println(Arrays.toString(array_sum_xy));

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
        ReadDisplayArray.displayOutput(matrix2);

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
        ReadDisplayArray.displayOutput(matrix_final);
        int swap = 0;
        gauss_gauss_jordan.elimination_before(matrix_final, n + 1, n + 2, swap);
        gauss_gauss_jordan.gauss(matrix_final, n + 1, n + 2);

        ReadDisplayArray.displayOutput(matrix_final);
    }

    public static void main(String[] args) throws IOException {
        // interpolasiPolinom();
        regresiLinier();
        // int h = 8 % 3;
        // System.out.println(h);
        System.out.println("HAHAH");
    }
}