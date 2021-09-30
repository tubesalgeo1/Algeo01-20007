
public class Cramer {

    public static String CramerMain(float[][] matriks, int rows, int cols, String resultString){
        // Copy koefisien variabel x1, x2, ..., xn (ruas kiri SPL) ke matriks baru (mKoef)
        float[][] mKoef = new float[rows][cols-1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                mKoef[i][j] = matriks[i][j];
            }
        } 
        if (rows == cols-1 && Determinan.detKofaktor(mKoef) != 0) {
            float[] solution4 = Cramer.cramerSol(matriks);
            for (int i = 0; i < cols-1; i++) {
                resultString += "x" + i + "=" + solution4[i] + " ";
            }
        } else {
            resultString += "Solusi tidak dapat dihitung dengan metode ini karena jumlah persamaan != jumlah variabel atau determinannya bernilai 0";
        }
        return resultString;
    }

    private static float[][] insertConst(float[][] matriks, int j, float[] constant) {
        // Fungsi insertConst mengganti elemen pada kolom j dengan nilai array constant (ruas kanan SPL)
        // Copy matriks ke matriks result
        int rows = matriks.length;
        int cols = matriks[0].length;
        float [][] result = new float[rows][cols];
        for (int l = 0; l < rows; l++) {
            for (int k = 0; k < cols; k++) {
                result[l][k] = matriks[l][k];
            }
        }
        // Mengganti kolom j dengan nilai pada array constant
        for (int i = 0; i < rows; i++) {
            result[i][j] = constant[i];
        }
        return result; 
    }
    
    public static float[] cramerSol(float[][] mAugmented) {
        // Fungsi cramerSol menghasilkan solusi SPL dengan menggunakan kaidah Cramer
        // Dimensi matriks Augmented (mAugmented)
        int rows = mAugmented.length;
        int cols = mAugmented[0].length;

        // Copy koefisien variabel x1, x2, ..., xn (ruas kiri SPL) ke matriks baru (mKoef)
        float[][] mKoef = new float[rows][cols-1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                mKoef[i][j] = mAugmented[i][j];
            }
        } 
        // Menghitung determinan matriks koefisien
        float detKoef = Determinan.detKofaktor(mKoef);
        
        // Copy konstanta (ruas kanan SPL) ke array baru (constant)
        float[] constant = new float[rows];
        for (int i = 0; i < rows; i++) {
            constant[i] = mAugmented[i][cols-1];
        } 

        // Deklarasi array untuk menampung nilai dari solusi (x1, x2, ..., xn)
        float[] solution = new float[rows];
        // Menghitung solusi dan memasukkan nilainya ke array solusi (solution)
        for (int j = 0; j < cols-1; j++) {
            float[][] temp = mKoef;
            solution[j] = (Determinan.detKofaktor(insertConst(temp, j, constant))) / detKoef;
        }
        return solution;
    }
}