public class Cramer {

    public static float[][] insertConst(float[][] matriks, int j, float[] constant) {
        int rows = matriks.length;
        for (int i = 0; i < rows; i++) {
            matriks[i][j] = constant[i];
        }
        return matriks; 
    }
    
    public static float[] crammerSol(float[][] matriks, float[] constant) {
        int rows = matriks.length;
        float[] solution = new float[rows];
        float detKoef = Determinan.detKofaktor(matriks);
        for (int i = 0; i < rows; i++) {
            solution[i] = (Determinan.detKofaktor(insertConst(matriks, i, constant))) / detKoef;
        }
        return solution;
    }
}