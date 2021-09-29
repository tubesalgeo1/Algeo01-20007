import java.io.IOException;

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
                System.out.println();
                gauss_gauss_jordan.elimination_before(matrix1, rows, cols, arr);
                System.out.println("Eliminasi sebelum gauss (matriks segitiga bawah): ");
                for(int i = 0; i < rows; i++){
                    for(int j = 0; j < cols; j++){
                        System.out.print(matrix1[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println("Eliminasi Gauss: ");
                gauss_gauss_jordan.gauss(matrix1, rows, cols);
                for(int i = 0; i < rows; i++){
                    for(int j = 0; j < cols; j++){
                        System.out.print(matrix1[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println("Eliminasi Gauss Jordan: ");
                gauss_gauss_jordan.gauss_jordan(matrix1, rows, cols);
                for(int i = 0; i < rows; i++){
                    for(int j = 0; j < cols; j++){
                        System.out.print(matrix1[i][j] + " ");
                    }
                    System.out.println();
                }
            }

            // Determinan
            else if (choiceMenu == 2) {
                System.out.println("******************** DETERMINAN ********************");
                Determinan.determinan();
            }

            // Matriks Balikan
            else if (choiceMenu == 3) {
                System.out.println("******************** MATRIKS BALIKAN ********************");
                float matrix[][] = ReadDisplayArray.readInput(true);
                matrix = invers.invers_mat_kofaktor(matrix);
                System.out.println();
                for(int i = 0; i < matrix.length; i++){
                    for(int j = 0; j < matrix.length; j++){
                        System.out.print(matrix[i][j] + " ");
                    }
                    System.out.println();
                }
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
