import Interfaz.Inicio;
import java.util.Arrays;
/**
 *
 * @author Equipo
 */

/* Para utilizar los métodos por medio de la consola quitar los comentarios del main.
 * El método de la Esquina Noroeste es el único completo y con todos los errores solucionados, también se puede
    usar en consola en esta misma clase. Hay un problema con el método Simplex, al dar clic en el botón "CONTINUAR"
    el problema no se ha solucionado completamente. El método del costo mínimo se puede probar en consola en esta
    misma clase. El glosario se puede utilizar correctamente.
 * Había un error en la tabla de asignación de datos al momento de dar clic en el botón "CONTINUAR" para poder
    continuar se debe deseleccionar la tabla y luego dar click en el botón.
 * Otro error se encontraba al momento de dar clic en el botón "SOLUCIONAR" para arreglarlo se realizaron algunos
    cambios en la clase "EsquinaNorOeste" en la variable iFila, el error se encontraba en iFila = dDatos[0].length,
    lo correcto es iFila = dDatos.length */

public class Principal {

    public static void main(String[] args) {
        //new Inicio().setVisible(true);
        /*int[] O = {100, 200, 300};
        int[] D = {150, 350, 100};
        int[][] C = {{11, 12, 14}, {9,19,10}, {25,12,17}};*/
        
        //EsqNO(O, D, C);
        
        /*int iTam = 3;
        int[][] capacidad = new int[iTam][iTam];
        int[][] costo = new int[iTam][iTam];
        agrega(capacidad, costo, 0, 1, 3, 1);
        agrega(capacidad, costo, 0, 2, 2, 1);
        agrega(capacidad, costo, 1, 2, 2, 1);
        int[] costof = CostMin(capacidad, costo, iTam, iTam);*/
        
        //CostoMinimo(O, D, C);
    }
    //CONSTRUCTOR
    public static int EsqNO(int[] aOferta, int[] aDemanda, int[][]aCosto){
        //ARREGLO DE LA TABLA
        int[][] aTabla = new int[aOferta.length][aDemanda.length];
        //VARIABLE DEL COSTO
        int iCostoTotal = 0;
        
        //IMPRIMIR LA TABLA INICIAL
         System.out.println("TABLA INICIAL");
        for (int i = 0; i < aOferta.length; i++) {
            System.out.println("Oferta:" + aOferta[i]);
            for (int j = 0; j < aDemanda.length; j++) {
                System.out.print(" [" + aTabla[i][j] + "]");
                System.out.println("\t Demanda:" + aDemanda[j]);
            }
        }
        
        //PROCESO PARA ASIGNAR LOS DATOS
        System.out.println(" ");
        System.out.println("TABLA ASIGNANDO DATOS");
        for (int i = 0; i < aOferta.length; i++) {
            System.out.print("Oferta:" + aOferta[i]+"\t");
            for (int j = 0; j < aDemanda.length; j++) {
                //CONDICIONAL SI LA OFERTA ES MAYOR O IGUAL QUE LA DEMANDA
                if(aOferta[i] >= aDemanda[j]){
                    //LLENAR ESA POSICION DE LA TABLA CON EL VALOR DE DEMANDA
                    //YA QUE COMO LA OFERTA ES MAYOR, PODEMOS CUMPLIR LA DEMANDA
                    aTabla[i][j] = aDemanda[j];
                    //RESTARLE A LA OFERTA LA DEMANDA Y ACTUALIZAR EL VALOR DE OFERTA
                    aOferta[i] = aOferta[i] - aDemanda[j];
                    //AL CUMPLIR LA DEMANDA ESTA SE VUELVE 0
                    aDemanda[j] = 0;
                //CONDICIONAL SI LA OFERTA ES MENOR QUE LA DEMANDA    
                }else{
                    //LLENAR ESA POSICION DE LA TABLA CON EL VALOR DE OFERTA
                    //YA QUE COMO LA OFERTA ES MENOR, PODEMOS LLENAR UNA PARTE DE LA DEMANDA
                    aTabla[i][j] = aOferta[i];
                    //RESTARLE A LA DEMANDA LA OFERTA Y ACTUALIZAR EL VALOR DE DEMANDA
                    aDemanda[j] = aDemanda[j] - aOferta[i];
                    //AL AGOTARNOS LA OFERTA ESTA SE VUELVE 0
                    aOferta[i] = 0;
                }
                //IMPRIMIR LA TABLA LLENA
                System.out.print("[" + aTabla[i][j] + "]");
                System.out.print("\tDemanda:" + aDemanda[j] + "\t");
            }
            System.out.println(" ");
        }
        //CALCULAR EL COSTO A TRAVES DE LOS PRECIOS
        for (int i = 0; i <= 2; i++) {
             for (int j = 0; j <= 2; j++) {
                 iCostoTotal = iCostoTotal + aCosto[i][j]*aTabla[i][j];
             }
         }
        //IMPRIMIR EL COSTO TOTAL MINIMO
        System.out.println(" ");
        System.out.println("Costo Total: " + iCostoTotal);
        return iCostoTotal;
    }
    
    //COSTO MINIMO
     public static int[] CostMin(int[][] aiTabla, int[][] aiCosto, int ival, int ival2){
          int a = aiTabla.length;
    int[] b = new int[a];
    int[] c = new int[a];
    for (int iflujo = 0, costoflujo = 0;; ++iflujo) {
      Arrays.fill(b, Integer.MAX_VALUE);
      //b[ival] = 0;
      for (int i = 0; i < a; i++)
        for (int j = 0; j < a; j++)
          for (int k = 0; k < a; k++)
            if (aiTabla[j][k] > 0 && b[j] < Integer.MAX_VALUE && b[k] > b[j] + aiCosto[j][k]) {
              b[k] = b[j] + aiCosto[j][k];
              c[k] = j;
            }
      if (b[0] == Integer.MAX_VALUE)
        return new int[] { costoflujo, iflujo };
      costoflujo += b[ival2];
      for (int v = ival2; v != ival; v = c[v]) {
        --aiTabla[c[v]][v];
        ++aiTabla[v][c[v]];
      }
    }
     }
     
      public static void agrega(int[][] aiTabla, int[][] aiCosto, int iVal1, int iVal2, int capacidad, int costo) {
    aiTabla[iVal1][iVal2] = capacidad;
    aiCosto[iVal1][iVal2] = costo;
    aiCosto[iVal2][iVal1] = -costo;
  }
      public static int CostoMinimo(int[] aOferta, int[] aDemanda, int[][]aCosto){
        //ARREGLO TABLA
        int[][] aTabla = new int[aOferta.length][aDemanda.length];
        //VARIABLE COSTO TOTAL
        int iCostoTotal = 0;
        //VARIABLE ENCONTRAR EL COSTO MINIMO EN LA TABLA
        int aMenor = aCosto[0][0];
        //VARIABLE DE LA FILA
        int iF = 0;
        //VARIABLE DE LA COLUMNA
        int iC = 0;
        //VARIABLES SI LA FILA Y COLUMNA ESTAN SATISFECHAS (LLENAS)
        boolean bFLlena = false;
        boolean bCLlena = false;
        //CICLO
        for (int i = 0; i < aOferta.length; i++) {
            for (int j = 0; j < aDemanda.length; j++) {
                //CONDICIONAL SI MENOR ES MAYOR A COSTO
                if(aMenor > aCosto[i][j]){
                    //SI ES ASI, MENOR SERA IGUAL A COSTO
                    aMenor = aCosto[i][j];
                    //LAS VARIABLES TOMARAN LOS VALORES DE i Y j
                    iF = i;
                    iC = j;
                }
            }
        }
        //CONDICIONAL, MIENTRAS COLUMNA SEA DIFERNETE A VERDADERO
        while (bCLlena != true) {
            //CONDICIONAL, SI LA OFERTA ES MAYOR O IGUAL A LA DEMANDA
            if(aOferta[iF] >= aDemanda[iC]){
                //LA POSICION EN LA TABLA SE LLENARA CON LA DEMANDA
                aTabla[iF][iC] = aDemanda[iC];
                //SE ACTUALIZARA EL VALOR DE OFERTA
                aOferta[iF] = aOferta[iF] - aDemanda[iC];
                //YA QUE LA DEMANDA SE CUMPLIO ESTA SERA 0
                aDemanda[iC] = 0;
            }
            //CONDICIONAL, SI LA OFERTA ES MENOR A LA DEMANDA
            else{
                //LA POSICION EN LA TABAL SE LLENARA CON LA OFERTA
                aTabla[iF][iC] = aOferta[iF];
                //SE ACTUALIZARA LA DEMANDA
                aDemanda[iC] = aDemanda[iC] - aOferta[iF];
                //LA OFERTA SERA 0, PORQUE SE AGOTO
                aOferta[iF] = 0;
            }
            //ENCONTRAR LA MANERA DE IMPRIMIR LA TABLA Y TODOS LOS VALORES
            
            //ENCONTRAR EL SIGUIENTE MENOR COSTO DE ENVIO
        }
        return iCostoTotal;
    }
}