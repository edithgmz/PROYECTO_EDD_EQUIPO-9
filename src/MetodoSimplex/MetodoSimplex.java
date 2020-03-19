/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoSimplex;

import static MetodoSimplex.AproximarNumero.valorAproximado;
import static MetodoSimplex.MetodoSimplex.aux;
import static Interfaz.MSimplex.jtaOp;
import javax.swing.table.DefaultTableModel;
import static MetodoSimplex.MetodoSimplex.contieneM;

/**
 *
 * @author Equipo
 */
public class MetodoSimplex {
    
    //atributos de la clase MSimplex
    public static int columnaMayor, filaMenor, numeroTablones = 1;
    public static double pivote;
    public static int posicionSubStringAnteM; //posicion del numero SubString que esta delante de la M e
    public static boolean todosMenor=false;
    
    //metodo para calcular  Zj
    public static void calcularZj(DefaultTableModel modeloSolucion){//DefaultTableModel es una clase que implementa TableModel que contiene todos los métodos necesarios para modificar datos en su interior: añadir filas o columnas y darle a cada columna el valor que se desee.
        //atributos del metodo calcular Z
        int fila = modeloSolucion.getRowCount();//Devuelve el número de filas en esta tabla de datos.
        int columna = modeloSolucion.getColumnCount();//Devuelve el número de columnas en la tabla de datos
        double sumModSolu=0.0, sumaNumeros=0.0;
        double xb,x;
        String valor;
        
        //ciclo
        for (int col = 1; col < columna-1; col++) {
            if(col!=columna-3){
                sumModSolu = 0.0;
                sumaNumeros =0.0;
                for (int fil = 2; fil <fila-2; fil++) {
                    valor = String.valueOf(modeloSolucion.getValueAt(fil, 0));
                    if(contieneM(valor)){
                        sumModSolu = sumModSolu+Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(fil, col)));
                    }
                    else{
                       xb = Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(fil, 0)));
                       x = Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(fil, col)));
                       sumaNumeros = sumaNumeros+(xb*x); 
                    }
                }
                if(sumaNumeros==0 && sumModSolu==0)
                    modeloSolucion.setValueAt("0", fila-2, col);
                else if(sumaNumeros==0)
                    modeloSolucion.setValueAt(""+sumModSolu+"M", fila-2, col);
                else if(sumModSolu==0)
                    modeloSolucion.setValueAt(sumaNumeros, fila-2, col);
                else{
                    if(sumaNumeros>0)
                       modeloSolucion.setValueAt(""+sumModSolu+"M+"+sumaNumeros, fila-2, col);                     
                    else modeloSolucion.setValueAt(""+sumModSolu+"M"+sumaNumeros, fila-2, col);
                }
            }
        }
    }
    //--------------------------------------------------------------------------
    //metodo Cj_Zj
    public static void calcularCj_Zj(DefaultTableModel modeloSolucion){
        int fila = modeloSolucion.getRowCount();
        int colum = modeloSolucion.getColumnCount();
        String aux1,aux2;
        double zj,cj;
        for (int col = 1; col < colum-3; col++) {
            aux1=String.valueOf(modeloSolucion.getValueAt(0, col));
            aux2=String.valueOf(modeloSolucion.getValueAt(fila-2, col));
            double auxiliar;
            if(!contieneM(aux1) && !contieneM(aux2)){
                cj = Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(0, col)));
                zj = Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(fila-2, col)));
                if((cj-zj)==0)modeloSolucion.setValueAt(0, fila-1, col);
                else modeloSolucion.setValueAt((cj-zj), fila-1, col);
            }            
            else if(!contieneM(aux1) && contieneM(aux2)){
               cj = Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(0, col)));
               cj = cj+(-1.0D*subNumero(aux2));
               
               auxiliar=(-1.0D*subNumeroAnteM(aux2));
               if(cj==0) modeloSolucion.setValueAt(""+auxiliar+"M", fila-1, col);
               else if(auxiliar>0) modeloSolucion.setValueAt(""+cj+"+"+auxiliar+"M", fila-1, col); 
               else modeloSolucion.setValueAt(""+cj+""+auxiliar+"M", fila-1, col); 
            }
            else if(contieneM(aux1) && !contieneM(aux2)){
                zj = Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(fila-2, col)))*-1.0D;
                if(zj==0)modeloSolucion.setValueAt(aux1, fila-1, col);
                else if(subNumeroAnteM(aux1)>0)modeloSolucion.setValueAt(+zj+"+"+aux1, fila-1, col);
                else modeloSolucion.setValueAt(""+zj+""+aux1, fila-1, col);
            }
            else if(contieneM(aux1) && contieneM(aux2)){
                double multiM = 1-(subNumeroAnteM(aux2));
                double num = -1.0D*subNumero(aux2);
                if(multiM == 0 && num == 0)modeloSolucion.setValueAt(0, fila-1, col);
                else if(multiM == 0) modeloSolucion.setValueAt(num, fila-1, col);
                else if(num == 0) modeloSolucion.setValueAt(multiM+"M", fila-1, col);                
                else if(multiM > 0) modeloSolucion.setValueAt(""+num+"+"+multiM+"M", fila-1, col);
                else modeloSolucion.setValueAt(""+num+""+multiM+"M", fila-1, col);
            }
        }
    }
    //--------------------------------------------------------------------------
    //Metodo que nos dice si esta contenida M (ture/false)
    public static boolean contieneM(String sVal){
        String x;
        for (int i = 0; i < sVal.length(); i++) {
            x=String.valueOf(sVal.charAt(i));
            if(x.equalsIgnoreCase("M")){
                return true;
            }
        }
        return false;
    }
    //--------------------------------------------------------------------------
    //Metodo que nos da el subNumero
    private static double subNumero(String sVal){
        String sub = null;
        for (int i = 0; i < sVal.length(); i++) {
            if(sVal.charAt(i)=='M'){
                sub = sVal.substring(i+1, sVal.length());
                if(sub.length()>0){
                    return ((Double.parseDouble(sub)));
                }
            }
        }
        return 0;
    }
    //--------------------------------------------------------------------------
    //Metodo que devuelve el subnumero
    private static double subNumeroAnteM(String sVal){
        String sTemp;
        for (int i = 0; i < sVal.length(); i++) {
            if(sVal.charAt(i)=='M'){
                sTemp = sVal.substring(0, i);
                if(sTemp.length()>0)
                    return Double.parseDouble(sTemp);
            }
        }
        return 1;
    }
    //--------------------------------------------------------------------------
    //metodo auxiliar
    public static double aux(String sVal){
        if(sVal.substring(posicionSubStringAnteM, sVal.length()-1).equalsIgnoreCase("+")){
            return 1;
        }
        else if(sVal.substring(posicionSubStringAnteM, sVal.length()-1).equalsIgnoreCase("-")){
            return -1;
        } 
        else if(sVal.substring(posicionSubStringAnteM, sVal.length()-1).length() == 0){
            return 1;
        }
        return Double.parseDouble(String.valueOf(sVal.substring(posicionSubStringAnteM, sVal.length()-1)));
    }
    //--------------------------------------------------------------------------
     public static void Gauss(DefaultTableModel  modeloSolucion){
         int fila =  modeloSolucion.getRowCount();
         int columna =  modeloSolucion.getColumnCount(); 
         double f1,valor, f2 ;
         for (int f = 2; f < fila-2; f++) {
                valor  = Double.parseDouble(String.valueOf(modeloSolucion.getValueAt(f, columnaMayor)));                 
                if(f!=filaMenor){
                    if(valor==1){
                        jtaOp.append("> F"+(f-1)+"-F"+(filaMenor-1)+"\n");
                    }
                    else if(valor!=0){
                        jtaOp.append("> F"+(f-1)+"-("+valorAproximado(String.valueOf(valor))+")F"+(filaMenor-1)+"\n");
                    }
                      for (int col = 1; col < columna-1; col++) {                    
                            if(col!=columna-3){                       
                                f1  = Double.parseDouble(String.valueOf( modeloSolucion.getValueAt(filaMenor, col)));
                                f2  = Double.parseDouble(String.valueOf( modeloSolucion.getValueAt(f, col)));
                                double g = f1*valor;                        
                                 modeloSolucion.setValueAt((f2)-g, f,col);
                            }
                      }
                 }
         }
    }
     //-------------------------------------------------------------------------
     public static  void Convertir0Pivote(DefaultTableModel modeloDeSolucion){
        String x,s,xb;
        int columna = modeloDeSolucion.getColumnCount();
        double dAux;
        x = String.valueOf(modeloDeSolucion.getValueAt(1, columnaMayor));
        s = String.valueOf(modeloDeSolucion.getValueAt(filaMenor, columna-3));
        xb = String.valueOf(modeloDeSolucion.getValueAt(0, columnaMayor));
        modeloDeSolucion.setValueAt(s, 1, columnaMayor);        
        modeloDeSolucion.setValueAt(x, filaMenor, columna-3);
        modeloDeSolucion.setValueAt(xb, filaMenor, 0);
        pivote= Double.parseDouble(String.valueOf(modeloDeSolucion.getValueAt(filaMenor, columnaMayor)));
        jtaOp.append("\n  --Tablon "+numeroTablones+"--\n\n");
        numeroTablones++;
        jtaOp.append("> (1/"+valorAproximado(String.valueOf(pivote))+")F"+(filaMenor-1+"\n"));
        for (int co = 1; co < columna-1; co++) {
            if(co!=columna-3){
              dAux = Double.parseDouble(String.valueOf(modeloDeSolucion.getValueAt(filaMenor, co)));
              dAux = dAux/pivote;
              modeloDeSolucion.setValueAt(dAux, filaMenor, co);
            }
        }
    }
     //-------------------------------------------------------------------------
     private static double numero(String sVal){
        String x=null;
        for (int i = 1; i < sVal.length(); i++) {            
           if(sVal.charAt(i)=='-'||sVal.charAt(i)=='+' && (sVal.charAt(i-1)!='E')){ 
               if(sVal.charAt(i-1)!='E'){
                   posicionSubStringAnteM=i;
                   x = sVal.substring(0, i); 
                   return Double.parseDouble(x);
               }
           }
        }
        posicionSubStringAnteM=00;
        return 0;        
    }
     //-------------------------------------------------------------------------
     public static void menor(DefaultTableModel modeloDeSolucion){
       int fila = modeloDeSolucion.getRowCount();
       int columna = modeloDeSolucion.getColumnCount(); 
       double menor = Double.MAX_VALUE, tem=0;
       filaMenor=0;
       for (int i = 2; i < fila-2; i++) {                          
            if(!String.valueOf(modeloDeSolucion.getValueAt(i, columna-1)).equalsIgnoreCase("∞")){
               tem = Double.parseDouble(String.valueOf(modeloDeSolucion.getValueAt(i, columna-1)));                
               if(tem>0D){ 
                   if(menor>tem){ 
                       menor = tem;                   
                       filaMenor = i;
                   }
               }
            } 
        }
    }
     //-------------------------------------------------------------------------
     public static void CalcularXn_Mayor(DefaultTableModel modeloDeSolucion){
       int fila = modeloDeSolucion.getRowCount();
       int columna = modeloDeSolucion.getColumnCount(); 
       double  xn, x;
            for (int i = 2; i < fila-2; i++) {
                xn = Double.parseDouble(String.valueOf(modeloDeSolucion.getValueAt(i, columna-2)));
                x = Double.parseDouble(String.valueOf(modeloDeSolucion.getValueAt(i, columnaMayor)));
                if(x==0){
                  modeloDeSolucion.setValueAt("∞", i, columna-1);  
                }
                else{
                 modeloDeSolucion.setValueAt((xn/x), i, columna-1);    
                }
            }
    }
     //-------------------------------------------------------------------------
     public static void menorFilaCj_Zj(DefaultTableModel modeloDeSolucion){
       int fila = modeloDeSolucion.getRowCount();
       int colum = modeloDeSolucion.getColumnCount();       
       String tem;       
       double menor = Double.MAX_VALUE,M = 11000670008.9D,aux1,aux2,y = 0;
        posicionSubStringAnteM=0; 
        todosMenor =true;
        for (int f = 1; f < colum-3; f++) {           
               tem = String.valueOf(modeloDeSolucion.getValueAt(fila-1,f)); 
               if(contieneM(tem)){
                  aux1 = numero(tem);
                  aux2 = aux(tem);
                  y = aux1+(aux2*M);                  
               }
               else{
                   y = Double.parseDouble(tem);
               }
               if(y <  menor){
                   menor = y;
                   columnaMayor = f;
               }
               if(y < 0) todosMenor = false;
            }
    }
     //-------------------------------------------------------------------------
     public static void mayorFilaCj_Zj(DefaultTableModel modeloDeSolucion){
       int fila = modeloDeSolucion.getRowCount();
       int colum = modeloDeSolucion.getColumnCount();
       todosMenor = true;
       String tem;       
       double mayor =-100,M=11000000455D,aux1,aux2,y = 0;
        posicionSubStringAnteM = 0;
        for (int f = 1; f < colum-3; f++) {           
               tem = String.valueOf(modeloDeSolucion.getValueAt(fila-1,f)); 
               if(contieneM(tem)){
                  aux1 = numero(tem);
                  aux2 = aux(tem);
                  y = aux1+(aux2*M);                  
               }
               else{
                   y = Double.parseDouble(tem);
               }
               if(y > mayor){
                   mayor = y;
                   columnaMayor = f;
               }
        if(y>0) todosMenor = false;// se comprueba si todos los numeros no son menores que cero
        }
    }
     //-------------------------------------------------------------------------
}
