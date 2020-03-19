/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoSimplex;

/**
 *
 * @author Pascualon
 */
public class AproximarNumero {
    static  String sTemp;
    public static  String valorAproximado(String sVal){// retorna el valor reducido, despues del . retorna tres sifras+E^n
      String x =null;
      for (int k = 0; k < sVal.length(); k++) {
         char c =sVal.charAt(k);
         if(c=='.'){ 
               x=x+'.'; 
               k++;           
               if(k<sVal.length() && sVal.charAt(k)!='E'){
                  x=x+sVal.charAt(k);
                  k++;
               }
               if(k<sVal.length() && sVal.charAt(k)!='E'){
                  x=x+sVal.charAt(k);
                  k++;
               }
               if(k<sVal.length() && sVal.charAt(k)!='E'){
                  x=x+sVal.charAt(k);
                  k++;
               }
               x=x+contieneE(sVal);
               break;          
         }
         else x=x+sVal.charAt(k);         
        }
        return x;
}
  public static String  contieneE(String cadena){
      char cVal;
      for (int i = 0; i < cadena.length(); i++) {
          cVal= cadena.charAt(i);
          if(cVal=='e' || cVal=='E'){
              return cadena.substring(i, cadena.length()); 
          }
      }
        return "";
  }
}
