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
                    System.out.println("Matrix setelah Eliminasi Gauss: \n");
                    ReadDisplayArray.displayOutput(matrix1);
                
                } else if (choiceSubMenu == 2) {
                    gauss_gauss_jordan.elimination_before(matrix1, rows, cols, arr);
                    gauss_gauss_jordan.gauss(matrix1, rows, cols);
                    gauss_gauss_jordan.gauss_jordan(matrix1, rows, cols);
                    System.out.println("Matrix setelah Eliminasi Gauss-Jordan: \n");
                    ReadDisplayArray.displayOutput(matrix1);

                } else if (choiceSubMenu == 3) {
                    // Membagi matriks menjadi matriks koefisisien dan matriks konstanta
                    float[][] mKoef = new float[rows][cols-1];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols-1; j++) {
                            mKoef[i][j] = matrix1[i][j];
                        }
                    }
                    float[][] constant = new float[rows][1];
                    for (int i = 0; i < rows; i++) {
                        constant[i][0] = matrix1[i][cols-1];
                    } 
                    
                    // Mengecek bentuk matriks
                    if (rows == cols-1 && Determinan.detKofaktor(mKoef) != 0) {
                        float[][] InversMatrix = invers.invers_mat_kofaktor(mKoef);
                        // Mengalikan invers matriks koefisien dengan matriks konstanta
                        float [][] solution3 = Utils.multiplyMatrix(InversMatrix, constant);
                    
                        for (int i = 0; i < cols-1; i++) {
                            resultString += "x" + i + "=" + solution3[i][0] + " ";
                        }
                    } else {
                        resultString += "Solusi tidak dapat dihitung dengan metode ini karena bukan matriks persegi atau tidak memiliki invers";
                    }
                    
                } else if (choiceSubMenu == 4) {
                    if (rows == cols-1) {
                        float[] solution4 = Cramer.cramerSol(matrix1);
                        for (int i = 0; i < cols-1; i++) {
                            resultString += "x" + i + "=" + solution4[i] + " ";
                        }
                    } else {
                        resultString += "Solusi tidak dapat dihitung dengan metode ini karena bukan matriks persegi";
                    }

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
                float matrix[][] = ReadDisplayArray.readInput(true);
                matrix = invers.invers_mat_kofaktor(matrix);
                System.out.println();
                ReadDisplayArray.displayOutput(matrix);
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
