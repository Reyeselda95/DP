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
public class P084 {

    int[][] datos;
    boolean[] supers;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        P084 p= new P084();
        String[] params={"10 4 4 99 4 4", "11 5 5 5 5 5"};
        //36
        System.out.println(p.best(params));
    }
    
    int suma(){
        int res=Integer.MAX_VALUE;
        int aux=0;
        for(int i=0;i<datos.length;++i){//Supermercados
            aux=0;
            for(int j=0;j<datos[i].length;++j){//Productos
                aux+=getProducto(j,i);
                if(aux>res){
                    break;
                }
            }
            res=Integer.min(res,aux);
        }
        return res;
    }
    
    
    /**
     * Devuelve el precio del producto prod en el super sup
     * @param prod Producto a comprobar
     * @param supermercado String del super a comprobar
     * @return 
     */
    public int getProducto(int prod, int supermercado){
        return datos[supermercado][prod];        
    }
    
    public int cota(int suma, boolean[] supers, String[] data, int prod){
        int res=0;
        int actual;
        for(int i=prod;i>0;--i){
            actual=Integer.MAX_VALUE;
            for(int j=supers.length-1;j>=0;--j){//Obtiene el mínimo
                actual=Integer.min(actual,getProducto(i,j));
            }
            res+=actual;
        }
        
        return suma+res;
    }
    
    int best;
    public void almacen(int prod,boolean[] supers, int suma,String[] data){
       //Si podemos mejorar exploramos
        int aux;
        if ( cota(suma,supers,data,prod) < best ) {//Si se puede mejorar
            if ( prod == 0 ) { // no quedan trabajos
                best = Math.min(best, suma);
            } else {
                for(int i=0;i< supers.length;++i){
                    if(!supers[i]){//Si supers[i] estaba a falso
                        aux=suma;//Guardas la suma anterior
                        supers[i]=true;
                        almacen(prod-1, supers, suma+getProducto(prod,i)+ getProducto(0, i), data);
                        supers[i]=false;
                        suma=aux;//Reestableces la suma a su estado anterior;
                    }
                    else{
                        almacen(prod-1, supers, suma+getProducto(prod,i), data);
                    }
                }
            }
        }
        
    }
    
    public void inicializar(String[] data){
        datos= new int [data.length][data[0].split("\\p{Space}+").length];
        String[] aux;
        for(int i=0;i<data.length;++i){
            aux=data[i].split("\\p{Space}+");
            for(int j=0;j<aux.length;++j){
                datos[i][j]=Integer.parseInt(aux[j]);
            }
        }
    }
    
    public int best(String[] data){
        int result=Integer.MAX_VALUE;
        inicializar(data);
        
        //Se intentará minimizar result
        boolean[] supers= new boolean[data.length];
        best=suma();
        almacen(datos[0].length-1,supers,0,data);
       // System.out.println(best);
        return best;
    }
    
}
