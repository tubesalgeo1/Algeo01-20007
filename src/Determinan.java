//import java.io.IOException;
import java.lang.Math;

public class Determinan {
    /* // Test 
    public static void main(String[] args) throws IOException {
        // Prosedur main untuk mengetes fungsi pada class Determinan
        float[][] matriks = ReadDisplayArray.readInput(true);
        ReadDisplayArray.displayOutput(matriks);
        System.out.println("Determinan = " + Determinan.detKofaktor(matriks));
        System.out.println("Determinan = " + Determinan.detReduksiBaris(matriks));
    }*/

    public static float[][] minor (float[][] matriks, int i, int j) {
        // Fungsi minor menghasilkan matriks minor (Mij) dari suatu matriks
        // Deklarasi matriks result
        int size = matriks.length-1;
        float[][] result = new float[size][size];
        int c = 0; 
        // Looping untuk menghasilkan matriks minor
        for (int a = 0; a < matriks.length; a++) {
            if (a == i) {   // Skip baris i
                continue;
            }
            int d = 0;
            for (int b = 0; b < matriks.length; b++) {
                if (b == j) {   // Skip kolom j
                    continue;
                }
                result[c][d] = matriks[a][b];
                d++;  
            }
            c++;
        }
        return result;
    }
    
    public static float detKofaktor (float[][] matriks) {
        // FUngsi detKofaktor menghasilkan determian matriks dengan metode ekspansi kofaktor
        int size = matriks[0].length;
        float result = 0;
        if (size == 1) {            // matriks dengan ukuran 1x1
            return matriks[0][0];
        } else if (size == 2) {     // matriks dengan ukuran 2x2
            return (matriks[0][0] * matriks[1][1]) - (matriks[1][0] * matriks[0][1]);
        } else {                    // matriks dengan ukuran > 2x2
            int j = 0;
            for (int i = 0; i < size; i++) {
                result += matriks[i][j] * detKofaktor(minor(matriks, i, j)) * Math.pow(-1, i+j);
            }
            return result;
        }
    }

    public static float detReduksiBaris (float[][] matriks) {
        // FUngsi detKofaktor menghasilkan determian matriks dengan metode reduksi baris
        int swap = 0;
        int size = matriks.length;
        if (size == 1) {        // matriks dengan ukuran 1x1
            return matriks[0][0];
        } else {                // matriks dengan ukuran > 1x1
            float det = 1;
            gauss_gauss_jordan.elimination_before(matriks, size, size, swap);   // menerapkan OBE untuk membentuk matriks eselon
            for (int i = 0; i < size; i++) {    // Mengalikan semua elemen pada diagonal utama
                det = det * matriks[i][i];
            }
            return det * (float) Math.pow(-1, swap);
        }
    }

}