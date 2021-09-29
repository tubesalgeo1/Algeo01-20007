public class invers{
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

