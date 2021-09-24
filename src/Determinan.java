// import java.io.IOException;
import java.lang.Math;

public class Determinan {
    // Test
    /*
    public static void main(String[] args) throws IOException {
        float[][] matriks = ReadDisplayArray.readInput(true);
        ReadDisplayArray.displayOutput(matriks);
        ReadDisplayArray.displayOutput(Determinan.minor(matriks, 1, 1));
        float det = Determinan.detKofaktor(matriks);
        System.out.println(det);
    }*/

    public static float[][] minor (float[][] matriks, int i, int j) {
        int size = matriks.length-1;
        float[][] result = new float[size][size];
        int c = 0; 
        for (int a = 0; a < matriks.length; a++) {
            if (a == i) {
                continue;
            }
            int d = 0;
            for (int b = 0; b < matriks.length; b++) {
                if (b == j) {
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
        int size = matriks.length;
        float result = 0;
        if (size == 1) {
            return matriks[0][0];
        } else if (size == 2) {
            return (matriks[0][0] * matriks[1][1]) - (matriks[1][0] * matriks[0][1]);
        } else {
            int j = 0;
            for (int i = 0; i < size; i++) {
                result += matriks[i][j] * detKofaktor(minor(matriks, i, j)) * Math.pow(-1, i+j);
            }
            return result;
        }
    }
}