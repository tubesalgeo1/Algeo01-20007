import java.io.IOException;

public class gauss_gauss_jordan{
    /* Note buat kawan2 jadi ini pertama harus lewat 
        1. panggil fungsi elimination_before terlebih dahulu
        (ini untuk menghandle swap baris)
        NOTE: JANGAN LUPA UNTUK INISIALISASI VARIABEL BUAT SWAP DALAM BENTUK ARRAY PANJANGNYA 1 DIGIT YAK KARENA BAKAL KEPAKE BANGET DI DETERMINAN LAGI 
        2. panggil fungsi gauss()
        3. panggil fungsi gauss_jordan() apabila berniat untuk mengubah ke bentuk matriks baris tereduksi -> opsional
        4. Selesai */

    public static String gauss_jordan_main(float[][] matriks, int rows, int cols, String resultString) throws IOException {
        // Daftar huruf untuk variabel parametrik
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w'};
        int Count;
        boolean NoSolution = false;
        for (int i = 0; i < rows; i++) {
            Count = 0;
            for (int j = 0; j < cols; j++) {
                if (matriks[i][j] != 0) {
                    Count += 1;
                }
            }
            if (Count == 1 && matriks[i][cols-1] != 0) {
                NoSolution = true;
                break;
            }
        }
        if (NoSolution) {
            resultString += "SPL tidak memiliki solusi\n";
        } else {
            // Mengeliminasi baris dengan elemen 0 semua 
            int count;
            int count1 = 0;
            for (int i = 0; i < rows; i++) {
                count = 0;
                for (int j = 0; j < cols; j++) {
                    if (matriks[i][j] != 0) {
                        count += 1;
                    }
                }
                if (count > 0) {
                    count1 += 1;
                }
            }
            // Menghapus baris yang elemennya 0 semua dari matriks
            int newRows = count1;
            float [][] newMatriks = new float[newRows][cols];
            for (int i = 0; i < newRows; i++) {
                newMatriks[i] = matriks[i];
            }

            String solution2[] = new String[cols-1];    // variabel penampung solusi

            /* Mengeliminasi kolom dengan elemen 0 semua */
            int count2;
            int count3 = 0;
            int l = 0;
            boolean[] idx0 = new boolean[cols];
            for (int j = 0; j < cols-1; j++) {
                count2 = 0;
                for (int i = 0; i < rows; i++) {
                    if (matriks[i][j] != 0) {
                        count2 += 1;
                    }
                }
                if (count2 == 0) {
                    solution2[j] = alphabet[l] + "";
                    idx0[j] = true;
                    count3 += 1;
                    l += 1;
                }
            }
            // Menghapus kolom yang elemennya 0 semua dari matriks
            int newCols = cols - count3;
            float [][] newMatriks1 = new float[newRows][newCols];
            int a = 0;
            for (int i = 0; i < newRows; i++) {
                int b = 0;
                for (int j = 0; j < cols; j++) {
                    if (idx0[j]) {
                        continue;
                    }
                    newMatriks1[a][b] = newMatriks[i][j];
                    b += 1;
                }
                a += 1;
            }
            // eliminasi gauss jordan untuk kolom bukan 0
            int swap1[] = new int[1];
                swap1[0] = 0; 
            gauss_jordan(newMatriks1, newRows, newCols);
            // Mengisi matriks dengan kolom 0 kembali
            float [][] newMatriks2 = new float[newRows][cols];
            int m = 0;
            for (int i = 0; i < newRows; i++) {
                int n = 0;
                for (int j = 0; j < cols; j++) {
                    if (idx0[j]) {
                        newMatriks2[i][j] = 0;
                        continue;
                    }
                    newMatriks2[i][j] = newMatriks1[m][n];
                    n += 1;
                }
                m += 1;
            }

            // Menghitung solusi x0 ... xn
            if (newRows == newCols) {
                int c = 0;
                for (int i = 0; i < cols-1; i++) {
                    if (idx0[i]) {
                        continue;
                    }
                    solution2[i] += newMatriks2[c][cols-1] + "";
                    c += 1;
                }
                for (int i = 0; i < cols-1; i++) {
                    resultString += "x" + i + " = " + solution2[i] + "\n";
                }
            } else {
                int k = count3;
                for (int i = newRows + count3; i < cols-1; i++) {
                    solution2[i] = alphabet[k] + ""; 
                    k += 1;
                }
                int h = 0;
                for (int i = 0; i < newRows + count3; i++) {
                    if (idx0[i]) {
                        continue;
                    }
                    solution2[i] = newMatriks2[h][cols-1] + ""; 
                    for (int j = cols-2; j >= newRows + count3; j--) {
                        solution2[i] += " + (" + (-newMatriks2[h][j]) + solution2[j] + ")";
                    }
                    h += 1;
                }
                
                for (int i = 0; i < cols-1; i++) {
                    resultString += "x" + i + " = " + solution2[i] + "\n";
                }
            }
        }
        
        
        return resultString;
    }

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
                    }else if(m[i][j] == 0 && m[i-1][j] != 0){
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
        int i = 0, j = 0, k;
        int count;
        if(neffrow > neffcols){
            for(i = 1; i < neffrow; i++){
                if(i >= neffcols){
                    count = neffcols;
                }else{
                    count = i;
                }
                for(j = 0; j < count; j++){
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
                        m[i][j] = 0;
                    }
                }
                check_gauss(m, neffrow, neffcols, swap_counter);
            }
        }else{
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
                        m[i][j] = 0;
                    }
                }
            }
        }
    }

    public static boolean is_singular(float m[][], int j, int i, int neffcols){
        boolean flag;
        flag = true;
        for(int k = 0; k < neffcols; k++){
            if(m[i][k] != m[j][k]){
                if(m[i][k] == 0 && m[j][k] != 0){
                    flag = true;
                    break;
                }
            }else if(m[i][k] != 0 && m[j][k] == 0){
                flag = false;
                break;
            }
        }
        return flag;
    }
    public static void swap_confirm(float m[][], int neffrow, int neffcols, int swap_counter[]){
        int idx = 0, idx2 = 0;
        float divider = 0;
        for(int i = 0; i < neffrow - 1; i++){
            for(int k = 0; k < neffcols; k++){
                if(m[i][k] != 0){
                    if(m[i+1][k] != 0){
                        idx2 = k;
                        idx = i;
                        divider = m[idx+1][idx2]/m[idx][idx2];
                        for(int z = idx2; z < neffcols; z++){
                            m[idx+1][z] -= m[idx][z]*divider; 
                        }
                        m[idx+1][idx2] = 0;
                        i = 0;
                        elimination_before(m, neffrow, neffcols, swap_counter);
                        break;
                    }else{
                        break;
                    }
                }
            }
        }

    }
}
