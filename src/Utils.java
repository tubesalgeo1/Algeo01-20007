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
}
