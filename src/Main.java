import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("*********************WELCOME*********************");
        boolean isFinish = false;

        while (isFinish == false) {
            Utils.displayMenu();
            int choiceMenu = Utils.chooseOptionValidation(1, 6);

            // Sistem Persamaan Linier
            if (choiceMenu == 1) {
                System.out.println("******************** SISTEM PERSAMAAN LINIER ********************");
                float matrix1[][] = ReadDisplayArray.readInput(false);
                int rows = matrix1.length;
                int cols = matrix1[0].length;
                int arr[] = new int[1];
                arr[0] = 0;
                String resultString = "";
                for (float[] row : matrix1) {
                    resultString += Arrays.toString(row) + "\n";
                }
                resultString += "Solusi SPL: \n";

                System.out.println("\nSUB-MENU SPL");
                System.out.println("1. Metode Eliminasi Gauss\n2. Metode Eliminasi Gauss-Jordan\n3. Metode Matriks Balikan\n4. Kaidah Cramer\n");
                int choiceSubMenu = Utils.chooseOptionValidation(1, 4);
                if (choiceSubMenu == 1) {
                    gauss_gauss_jordan.elimination_before(matrix1, rows, cols, arr);
                    gauss_gauss_jordan.gauss(matrix1, rows, cols);
                    System.out.println("Matrix setelah Eliminasi Gauss: ");
                    ReadDisplayArray.displayOutput(matrix1);
                    gauss_gauss_jordan.gauss_jordan(matrix1, rows, cols);
                    resultString = gauss_gauss_jordan.gauss_jordan_main(matrix1, rows, cols, resultString);
                
                } else if (choiceSubMenu == 2) {
                    gauss_gauss_jordan.elimination_before(matrix1, rows, cols, arr);
                    gauss_gauss_jordan.gauss(matrix1, rows, cols);
                    gauss_gauss_jordan.gauss_jordan(matrix1, rows, cols);
                    System.out.println("Matrix setelah Eliminasi Gauss-Jordan: ");
                    ReadDisplayArray.displayOutput(matrix1);
                    resultString = gauss_gauss_jordan.gauss_jordan_main(matrix1, rows, cols, resultString);

                } else if (choiceSubMenu == 3) {
                    resultString = invers.SPLInvers(matrix1, rows, cols, resultString); 
                    
                } else if (choiceSubMenu == 4) {
                    resultString = Cramer.CramerMain(matrix1, rows, cols, resultString);

                }
                ReadDisplayArray.displayOutputSPL(resultString);
            }

            // Determinan
            else if (choiceMenu == 2) {
                System.out.println("******************** DETERMINAN ********************");
                Determinan.determinan();
            }

            // Matriks Balikan
            else if (choiceMenu == 3) {
                System.out.println("******************** MATRIKS BALIKAN ********************");
                invers.InversMain();
            }

            // Interpolasi Polinom
            else if (choiceMenu == 4) {
                System.out.println("******************** INTERPOLASI POLINOM ********************");
                RegresiLinier.interpolasiPolinom();
            }

            // Regresi Linier
            else if (choiceMenu == 5) {
                System.out.println("******************** REGRESI LINIER ********************");
                RegresiLinier.regresiLinier();
            }

            // Keluar
            else if (choiceMenu == 6) {
                isFinish = true;
                System.out.println("Terima kasih!! Sampai jumpa lagiiiii");
            }

            if (isFinish == false) {
                isFinish = Utils.goToMenu();
            }
        }
    }
}
