public class gauss_gauss_jordan{

    static void swap(float m[][], int i, int j, int neffrow, int neffcols){
        float temp;
        int k;
        for(k = 0; k < neffcols; k++){
            temp = m[j][k];
            m[j][k] = m[i][k]; 
            m[i][k] = temp;
        }
    }

    static void check_gauss(float m[][], int neffrow, int neffcols, int swap_counter){
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

    static void gauss(float m[][], int neffrow, int neffcols){
        int i, j, k;
        float divider;
        for(i = 0; i < neffrow; i++){
            for(j = 0; j < neffcols; j++){
                if(m[i][j] > 0.000001 || m[i][j] < -0.000001){
                    divider = 1/m[i][j];
                    for(k = j; k < neffcols; k++){
                        m[i][k] = m[i][k] * divider;
                    }
                    break;
                }
            }
        }
    }

    static void gauss_jordan(float m[][], int neffrow, int neffcols){
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

    static void elimination_before(float m[][], int neffrow, int neffcols, int swap_counter){
        check_gauss(m, neffrow, neffcols, swap_counter);
        float divider;
        int i, j, k;
        for(i = 1; i < neffrow; i++){
            for(j = 0; j < i; j++){
                if(m[j][j] == 0){
                    check_gauss(m, neffrow, neffcols, swap_counter);
                    i = 1;
                    break;
                }
                divider = m[i][j]/m[j][j];
                for(k = 0; k < neffcols; k++){
                    m[i][k] -= divider*m[j][k];
                }
            }
        }
    }
}
