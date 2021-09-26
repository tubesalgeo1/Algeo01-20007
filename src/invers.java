public class invers{
    /* mencari matrix balikan dengan
    1/det*adjoint */
    public static float[][] invers_mat(float a[][]){
        float determinan;
        int size = a.length;
        float adjoint[][] = Determinan.adjoin(a);
        float array[][] = new float[size][size];
        /* isi dulu */
        determinan = Determinan.detKofaktor(a);
        array = multiply_matrix(adjoint, 1/determinan);
        return array;
    }

    public static float[][] multiply_matrix(float a[][], float var){
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

