import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import java.io.FileWriter;
import java.util.Random;

public class ReadDisplayArray {

    // Fungsi untuk meminta input dan mengembalikan dalam bentuk matriks
    // isSquare buat nentuin nanti matriks yang diinput mau persegi atau bukan
    // PERHATIAN YA GAISSS, kalo inputnya nanti dari File, kalo dia isinya coma
    // matrix m x n matrix keluarannya tetep
    // tapi kalo matrix m x n, terus nanti ada input yang dicari (kayak persoalan
    // SPL), nanti input yang dicari ada di baris terakhir, terus elemennya tambah
    // 0, jadi ati2 yak
    public static float[][] readInput(boolean isSquare) throws IOException {
        System.out.println("Jenis input apa yang ingin diberikan: ");
        System.out.print("1. Keyboard\n2. File\n");
        int chooseInput = Utils.chooseOptionValidation(1, 2);
        float matrix[][];
        if (chooseInput == 2) {
            matrix = ReadDisplayArray.readFiletoMatrix();
        } else {
            if (isSquare == true) {
                matrix = readSquareMatrix();
            } else {
                matrix = readMatrix();
            }
        }
        return matrix;
    }

    public static float[][] readInputPoint() throws IOException {
        System.out.println("Jenis input apa yang ingin diberikan: ");
        System.out.print("1. Keyboard\n2. File\n");
        int chooseInput = Utils.chooseOptionValidation(1, 2);
        float matrix[][];
        if (chooseInput == 1) {
            matrix = readPointMatrix();
        } else {
            matrix = readFiletoMatrix();
        }
        return matrix;
    }

    // Fungsi untuk menampilkan/menyimpan hasil operasi matriks
    public static void displayOutput(float[][] matrix) throws IOException {
        System.out.println("\nJenis output apa yang ingin diberikan: ");
        System.out.print("1. Keyboard\n2. File\n");
        int chooseOutput = Utils.chooseOptionValidation(1, 2);
        if (chooseOutput == 1) {
            displayMatrix(matrix);
        } else {
            createFileFromMatrix(matrix);
            System.out.println("File matriks sudah berhasil tersimpan :)");
        }
    }

    // Untuk memberikan output berupa string, baik keyboard ataupun ke file dalam
    public static void displayOutputSPL(String resultString) throws IOException {
        System.out.println("Jenis output apa yang ingin diberikan: ");
        System.out.print("1. Keyboard\n2. File\n");
        int chooseInput = Utils.chooseOptionValidation(1, 2);
        if (chooseInput == 2) {
            Random randomNum = new Random();
            FileWriter SPLFile = new FileWriter("../test/SPL" + randomNum.nextInt(100) + ".txt");
            SPLFile.write(resultString);
            SPLFile.close();
        } else {
            System.out.println(resultString);
        }
    }

    public static float[][] readPointMatrix() {
        System.out.println("Masukkan jumlah titik (n)");
        Scanner sc = new Scanner(System.in);
        System.out.print("n = ");
        int n = sc.nextInt();

        float[][] matrix = new float[n][2];

        System.out.println("Masukkan input dengan format x y");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = sc.nextFloat();
            }
        }

        return matrix;
    }

    public static float[][] readRegressionPoints() {
        System.out.println("Masukkan jumlah titik x (n)");
        Scanner sc = new Scanner(System.in);
        System.out.print("n = ");
        int n = sc.nextInt();

        System.out.println("Masukkan jumlah data (k)");
        Scanner sc2 = new Scanner(System.in);
        System.out.print("k = ");
        int k = sc2.nextInt();

        float[][] matrix = new float[k][n + 1];

        System.out.println("Masukkan input dengan format x1 x2 .. xn y sebanyak k data");
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] = sc.nextFloat();
            }
        }

        return matrix;
    }

    // Fungsi untuk membaca sebuah matriks persegi dari input user dan mengembalikan
    // dalam bentuk matriks
    private static float[][] readSquareMatrix() {
        System.out.println("Masukkan jumlah baris dan kolom (n) matrix");
        Scanner sc = new Scanner(System.in);
        System.out.print("n = ");
        int n = sc.nextInt();

        float[][] matrix = new float[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextFloat();
            }
        }

        return matrix;
    }

    // Fungsi untuk membaca sebuah file dan mengembalikan isinya berupa matriks
    // Fungsi untuk membaca sebuah file dan mengembalikan isinya berupa matriks
    static float[][] readFiletoMatrix() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Path File (Contoh: C:/Users/Asus/Documents/matrix.txt)");
        String path = s.nextLine();
        File file = new File(path);
        Scanner sc = new Scanner(file);
        Scanner sc2 = new Scanner(file);
        Scanner sc3 = new Scanner(file);
        int TOTAL_INT = 0;
        int ROWS = 0;

        while (sc.hasNextFloat()) {
            TOTAL_INT++;
            sc.nextFloat();
        }

        while (sc2.hasNextLine()) {
            ROWS++;
            sc2.nextLine();
        }
        float[] tempArr = new float[TOTAL_INT + 1];

        for (int i = 0; i < TOTAL_INT; i++) {
            tempArr[i] = sc3.nextFloat();
        }
        tempArr[TOTAL_INT] = 0;

        int COLS;
        if (TOTAL_INT % ROWS != 0) {
            COLS = (TOTAL_INT + 1) / ROWS;
        } else {
            COLS = (TOTAL_INT / ROWS);
        }

        float[][] matrix = new float[ROWS][COLS];
        int k = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = tempArr[k];
                k++;
            }
        }

        return matrix;
    }

    // Prosedur untuk menampilkan matriks
    private static void displayMatrix(float[][] matrix) {
        for (float[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Fungsi untuk membaca sebuah matriks dari input user dan mengembalikan dalam
    // bentuk matriks
    private static float[][] readMatrix() {
        System.out.println("Masukkan jumlah baris (m) dan kolom (n) matrix");
        Scanner sc = new Scanner(System.in);
        System.out.print("m = ");
        int m = sc.nextInt();
        System.out.print("n = ");
        int n = sc.nextInt();

        float[][] matrix = new float[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextFloat();
            }
        }
        return matrix;
    }

    private static void createFileFromMatrix(float[][] matrix) throws IOException {
        Random randomNum = new Random();
        FileWriter matrixFile = new FileWriter("../test/matrix" + randomNum.nextInt(100) + ".txt");

        for (float[] row : matrix) {
            String string = "";
            for (float num : row) {
                string += Float.toString(num);
                string += " ";
            }
            matrixFile.write(string);
            matrixFile.write("\n");
        }
        matrixFile.close();
    }
}