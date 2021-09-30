import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class invers{
    
    public static void InversMain() throws IOException{
        float matrix[][] = ReadDisplayArray.readInput(true);
        String resultStringInv = "";
        for (float[] row : matrix) {
            resultStringInv += Arrays.toString(row) + "\n";
        }
        resultStringInv += "\nMatriks Balikan: \n";

        System.out.println("\nSUB-MENU MATRIKS BALIKAN");
        System.out.println("1. Metode Kofaktor\n2. Metode Reduksi Baris\n");
        int choiceSubMenuInv = Utils.chooseOptionValidation(1, 4);
        if (choiceSubMenuInv == 1) {
            matrix = invers.invers_mat_kofaktor(matrix);
            for (float[] row : matrix) {
                resultStringInv += Arrays.toString(row) + "\n";
            }
        } else if (choiceSubMenuInv == 2) {
            matrix = invers.invers_mat_reduc(matrix);
            for (float[] row : matrix) {
                resultStringInv += Arrays.toString(row) + "\n";
            }
        }
        
        System.out.println("Jenis output apa yang ingin diberikan: ");
        System.out.print("1. Keyboard\n2. File\n");
        int chooseInput = Utils.chooseOptionValidation(1, 2);
        if (chooseInput == 2) {
            Random randomNum = new Random();
            FileWriter InvFile = new FileWriter("../test/Invers" + randomNum.nextInt(100) + ".txt");
            InvFile.write(resultStringInv);
            InvFile.close();
        } else {
            System.out.println(resultStringInv);
        }
    }

    public static String SPLInvers(float[][] matriks, int rows, int cols, String resultString) {
        // Membagi matriks menjadi matriks koefisisien dan matriks konstanta
        float[][] mKoef = new float[rows][cols-1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                mKoef[i][j] = matriks[i][j];
            }
        }
        float[][] constant = new float[rows][1];
        for (int i = 0; i < rows; i++) {
            constant[i][0] = matriks[i][cols-1];
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
        return resultString;
    }
    
    /* mencari matrix balikan dengan
    1/det*adjoint */
    public static float[][] invers_mat_kofaktor(float a[][]){
        float determinan;
        int size = a.length;
        float adjoint[][] = Determinan.adjoin(a);
        float array[][] = new float[size][size];
        /* isi dulu */
        determinan = Determinan.detKofaktor(a);
        array = multiply_matrix_kofaktor(adjoint, 1/determinan);
        return array;
    }

    public static float[][] multiply_matrix_kofaktor(float a[][], float var){
        int size = a.length;
        float array[][] = new float[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                array[i][j] = a[i][j] * var;
            }
        }

        return array;
    }

    public static float[][] invers_mat_reduc(float a[][]){
        float determinan;
        int size = a.length;
        float adjoint[][] = Determinan.adjoin(a);
        float array[][] = new float[size][size];
        /* isi dulu */
        determinan = Determinan.detReduksiBaris(a);
        array = multiply_matrix_reduc(adjoint, 1/determinan);
        return array;
    }

    public static float[][] multiply_matrix_reduc(float a[][], float var){
        int size = a.length;
        float array[][] = new float[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                array[i][j] = a[i][j] * var;
            }
        }

        return array;
    }
    
}

