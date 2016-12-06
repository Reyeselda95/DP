/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AA;

/**
 *
 * @author ara65
 */
public class P083 {
    int[] M;
    int L=0,N=0;
    int [][] almacen;

    public static void main(String[] args) {
        // TODO code application logic here
        P083 p= new P083();
        p.best("3 2 1 2 4 7 3");
        p.best("2 5 11 2 12 6 19 10 13 2 16 1");
        p.best("3 6 8 5 9 9 1 1 2 9 0 3 6 5 0 12 345 75 23 6 12 34 87 12 12 74 3 7 7 12 2");
        //{15,21,28}{345+87+12}
    }
    
    public int best(String data){
        String[] datos=data.split(" ");
        M= new int[datos.length-2];  
        N=Integer.parseInt(datos[0]);
        almacen= new int[M.length+1][N+1];
        L=Integer.parseInt(datos[1]);
        for(int i=2; i<datos.length;++i){
            M[i-2]=Integer.parseInt(datos[i]);
        }
       // System.out.println( recursivoAlmacen(M.length, N));
        return recursivoAlmacen(M.length, N);
    }
    
    public int recursivoValor(int m, int n){
        if(n==0 || m<1)
            return 0;
        else{
            return Integer.max(recursivoValor(m-1,n),    //Si no lo cojo
                    M[m-1]+recursivoValor(m-L,n-1));       //Si lo cojo*/
        }
    }
    
    public int recursivoAlmacen(int m, int n){
        int a,b;
        if(n==0 || m<1)//Si no hay mas valores
            return 0;
        else{
            if(almacen[m][n]<1){//Si el valor no ha sido rellenado
                //lo rellenamos
                a=recursivoAlmacen(m-1,n);
                b=M[m-1]+recursivoAlmacen(m-L,n-1);
                almacen[m][n]= Integer.max(a, b);
            }
            else{
                //lo devolvemos
                return almacen[m][n];
            }
        }
        return almacen[m][n];
    }
    
}
