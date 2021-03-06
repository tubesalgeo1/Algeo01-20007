import java.util.Scanner;

public class Utils {
    // Fungsi untuk validasi pilihan opsi dari input pengguna
    public static int chooseOptionValidation(int numStart, int numEnd) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan nomor opsi yang diinginkan = ");
        int choice = sc.nextInt();
        while ((choice < numStart) || (choice > numEnd)) {
            System.out.println(
                    "Nomor opsi yang bisa dipilih hanya antara " + numStart + " dan " + numEnd + ". Coba lagi!");
            System.out.print("Masukkan nomor opsi yang diinginkan = ");
            choice = sc.nextInt();
        }
        System.out.println("");
        return choice;
    }

    // Prosedur untuk menampilkan menu
    public static void displayMenu() {
        System.out.println("\nMENU");
        System.out.println(
                "1. Sistem Persamaan Linier\n2. Determinan\n3. Matriks Balikan\n4. Interpolasi Polinom\n5. Regresi Linier Berganda\n6. Keluar\n");
    }

    // Fungsi untuk menentukan apakah user ingin kembali ke menu awal atau keluar
    public static boolean goToMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Apakah masih ingin melanjutkan perhitungan (Y/N)? ");
        String string = sc.nextLine();
        while ((string.equals("Y") == false) && (string.equals("N") == false)) {
            System.out.println("Input hanya antara Y/N. Coba lagi!");
            System.out.print("Apakah masih ingin melanjutkan perhitungan (Y/N)? ");
            string = sc.nextLine();
        }

        if (string.equals("N")) {
            System.out.println("Terima kasih! Sampai bertemu lagi..");
        }
        return string.equals("N");
    }

    public static float power(float x, int p) {
        float result = 1;
        for (int i = 0; i < p; i++) {
            result *= x;
        }
        return result;
    }

    public static float[][] multiplyMatrix(float[][] matrix1, float[][] matrix2) {
        float[][] result = new float[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}
