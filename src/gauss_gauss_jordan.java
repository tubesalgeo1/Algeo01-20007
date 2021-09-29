public class gauss_gauss_jordan{
    /* Note buat kawan2 jadi ini pertama harus lewat 
        1. panggil fungsi elimination_before terlebih dahulu
        (ini untuk menghandle swap baris)
        NOTE: JANGAN LUPA UNTUK INISIALISASI VARIABEL BUAT SWAP DALAM BENTUK ARRAY PANJANGNYA 1 DIGIT YAK KARENA BAKAL KEPAKE BANGET DI DETERMINAN LAGI 
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

    public static void check_gauss(float m[][], int neffrow, int neffcols, int swap_counter[]){
        int h, i, j;
        int n;

        for(h = 0; h < neffrow; h++){
            for(i = 1; i < neffrow; i++){
                for(j = 0; j < neffcols; j++){
                    if(m[i][j] != 0 && (m[i-1][j] == 0)){
                        n = i - 1;
                        swap(m, i, n, neffrow, neffcols);
                        swap_counter[0] += 1;
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
        int index_row = -999;
        int arr[] = new int[neffcols];
        int count = 0;
        for(int i = 0; i < neffcols; i++){
            arr[i] = -9999;
        }
        float divider ,idx;
        for(int i = 0; i < neffrow-1; i++){
            for(int j = 0; j < neffcols; j++){
                index_row = search_row(m, j, neffrow, i);
                if(index_row != -999){
                    if(check_availability(arr, index_row, j)){
                        arr[j] = index_row;
                        divider = m[i][j]/ m[index_row][j];
                        for(int k = 0; k < neffcols; k++){
                            idx = m[index_row][k];
                            m[i][k] -= divider*idx;
                        }
                    }else{
                        count = index_row;
                        while(check_availability(arr, index_row, j) == false && count < neffrow){
                            index_row = search_row(m, j, neffrow, index_row);
                            count ++;
                        }
                    }
                }
            }
            for(int z = 0; z < neffcols; z++){
                arr[z] = -9999;
            }
        }
    }

    public static boolean check_availability(int a[], int val, int j){
        boolean flag;
        flag = true;
        for(int k = 0; k < j; k++){
            if(a[k] == val){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static int search_row(float m[][], int j, int neffrow, int i){
        int index = -999;
        for(int k = i+1; k < neffrow; k++){
            if(m[k][j] == 1){
                index = k;
                break;
            }
        }
        return index;
    }

    public static void elimination_before(float m[][], int neffrow, int neffcols, int swap_counter[]){
        check_gauss(m, neffrow, neffcols, swap_counter);
        float idx1, idx2;
        int i, j, k;
        for(i = 1; i < neffrow; i++){
            for(j = 0; j < i; j++){
                if(m[j][j] == 0){
                    if(is_singular(m, j, i, neffcols)){
                        /* do nothing */
                    }else{
                        check_gauss(m, neffrow, neffcols, swap_counter);
                        i = 1;
                        break;
                    }
                }else{
                    idx1 = m[i][j];
                    idx2 = m[j][j];
                    for(k = 0; k < neffcols; k++){
                        m[i][k] -= (idx1*m[j][k])/idx2;
                    }
                }
            }
        }
    }

    public static boolean is_singular(float m[][], int j, int i, int neffcols){
        boolean flag;
        flag = true;
        for(int k = 0; k < neffcols; k++){
            if(m[i][k] != 0 || m[j][k] != 0){
                flag = false;
            }
        }
        return flag;
    }
}
