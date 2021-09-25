public class gauss_gauss_jordan{

    /* Note buat kawan2 jadi ini pertama harus lewat 
        1. panggil fungsi elimination_before terlebih dahulu
        (ini untuk menghandle swap baris)
            NOTE: JANGAN LUPA UNTUK INISIALISASI VARIABEL BUAT SWAP YAK KARENA BAKAL KEPAKE BANGET DI DETERMINAN LAGI 
        2. panggil fungsi gauss()
        3. panggil fungsi gauss_jordan() apabila berniat untuk mengubah ke bentuk matriks baris tereduksi -> opsional
        4. Selesai */

    public static void swap(float m[][], int i, int j, int neffrow, int neffcols){
        float temp;
        int k;
        for(k = 0; k < neffcols; k++){
            temp = m[j][k];
            m[j][k] = m[i][k]; 
            m[i][k] = temp;
        }
    }

    public static void check_gauss(float m[][], int neffrow, int neffcols, int swap_counter){
        int h, i, j;
        int n;
        for(h = 0; h < neffrow; h++){
            for(i = 1; i < neffrow; i++){
                for(j = 0; j < neffcols; j++){
                    if(m[i][j] != 0 && (m[i-1][j] == 0)){
                        n = i - 1;
                        swap(m, i, n, neffrow, neffcols);
                        swap_counter ++;
                        break;
                    }
                }
            }
        }
    }

    public static void gauss(float m[][], int neffrow, int neffcols){
        int i, j, k;
        float divider;
        for(i = 0; i < neffrow; i++){
            for(j = 0; j < neffcols; j++){
                if(m[i][j] != 0){
                    divider = m[i][j];
                    for(k = j; k < neffcols; k++){
                        m[i][k] = m[i][k] / divider;
                    }
                    break;
                }
            }
        }
    }

    public static void gauss_jordan(float m[][], int neffrow, int neffcols){
        int i, j, k;
        float divider;
        for(i = 0; i < neffrow-1; i++){
            for(j = i+1; j < neffcols; j++){
                divider = m[i][j]/ m[j][j];
                for(k = i+1; k < neffcols; k++){
                    m[i][k] -= divider*m[j][k];
                }
            }
        }
    }

    public static void elimination_before(float m[][], int neffrow, int neffcols, int swap_counter){
        check_gauss(m, neffrow, neffcols, swap_counter);
        float idx1, idx2;
        int i, j, k;
        for(i = 1; i < neffrow; i++){
            for(j = 0; j < i; j++){
                if(m[j][j] == 0){
                    check_gauss(m, neffrow, neffcols, swap_counter);
                    i = 1;
                    break;
                }
                idx1 = m[i][j];
                idx2 = m[j][j];
                for(k = 0; k < neffcols; k++){
                    m[i][k] -= (idx1*m[j][k])/idx2;
                }
            }
        }
    }
}
