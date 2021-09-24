/* INFO: NANTI DIGANTI KE JAVA
    SEMENTARA DALAM BENTUK C DULU OKEiii */


#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

void swap(float m[5][5], int i, int j){
    float temp;
    int k;
    for(k = 0; k < 5; k++){
        temp = m[j][k];
        m[j][k] = m[i][k]; 
        m[i][k] = temp;
    }
}

int swapcount(){

}

void check_gauss(float m[5][5]){
    int h, i, j;
    int n;
    for(h = 0; h < 5; h++){
        for(i = 1; i < 5; i++){
            for(j = 0; j < 5; j++){
                if(m[i][j] != 0 && (m[i-1][j] == 0)){
                    n = i - 1;
                    swap(m, i, n);
                    break;
                }
            }
        }
    }
}


void gauss(float m[5][5]){
    int i, j, k;
    float divider;
    for(i = 0; i < 5; i++){
        for(j = 0; j < 5; j++){
            if(m[i][j] > 0.000001 || m[i][j] < -0.000001){
                divider = 1/m[i][j];
                for(k = j; k < 5; k++){
                    m[i][k] = m[i][k] * divider;
                }
                break;
            }
        }
    }
}

void gauss_jordan(float m[5][5]){
    
}

void elimination_before(float m[5][5]){
    check_gauss(m);
    float divider;
    int i, j, k;
    for(i = 1; i < 5; i++){
        for(j = 0; j < i; j++){
            if(m[j][j] == 0){
                check_gauss(m);
                i = 1;
                break;
            }
            divider = m[i][j]/m[j][j];
            for(k = 0; k < 5; k++){
                m[i][k] -= divider*m[j][k];
            }
        }
    }
}

int main(){
    float m[5][5];
    int h, i, j, k;
    float temp;
    float x;
    for(i = 0; i < 5; i++){
        for(j = 0; j < 5; j++){
            scanf("%f", &x);
            m[i][j] = x;
        }
    }
    float divider;
    check_gauss(m);
    printf("\n");
    for(i = 0; i < 5; i++){
        for(j = 0; j < 5; j++){
            printf("%0.2f ", m[i][j]);
        }
        printf("\n");
    }
    check_gauss(m);
    for(i = 1; i < 5; i++){
        for(j = 0; j < i; j++){
            if(m[j][j] == 0){
                check_gauss(m);
                i = 1;
                break;
            }
            divider = m[i][j]/m[j][j];
            for(k = 0; k < 5; k++){
                m[i][k] -= divider*m[j][k];
            }
        }
    }
    for(i = 0; i < 5; i++){
        for(j = 0; j < 5; j++){
            printf("%0.10f ", m[i][j]);
        }
        printf("\n");
    }
    printf("\n");
    gauss(m);
    for(i = 0; i < 5; i++){
        for(j = 0; j < 5; j++){
            printf("%0.2f ", m[i][j]);
        }
        printf("\n");
    }
}
