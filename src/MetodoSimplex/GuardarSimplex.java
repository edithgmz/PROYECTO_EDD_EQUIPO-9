/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoSimplex;

import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pascualon
 */
public class GuardarSimplex implements Serializable {     
    private int columnas=0;
    private int filas=0;
    private DefaultTableModel modeloRestricciones=null;
    private String[][] CoeficientesyZ=null;
    private String objetivo;
    //--------------------------------------------------------------------------
   public void crearMatrizCoeficientesyZ(DefaultTableModel modeloRestricciones){
       for (int filas = 0; filas < filas; filas++){
           for (int columnas = 0; columnas < columnas; columnas++) {
               CoeficientesyZ[filas][columnas]= String.valueOf(modeloRestricciones.getValueAt(filas,columnas));
           }
       }
   }
   //---------------------------------------------------------------------------
    public GuardarSimplex( DefaultTableModel modeloRestricciones, String objetivo) { 
        this.filas = modeloRestricciones.getRowCount();
        this.columnas = modeloRestricciones.getColumnCount();
        CoeficientesyZ = new String[filas][columnas];
        this.objetivo = objetivo;
        crearMatrizCoeficientesyZ(modeloRestricciones);
    }
    //--------------------------------------------------------------------------
    public void armarTabla(JTable tabla){
        Object cabeza[]= new String[columnas];
        cabeza[0]=(" ");
        for (int i = 1; i <=columnas-3; i++) 
            cabeza[i]=("X"+i);            
        cabeza[columnas-1]=(" ");cabeza[columnas-2]=(" "); 
        modeloRestricciones = new DefaultTableModel(cabeza,0){       
            public boolean isCellEditable(int row, int column) {
                       if(column == 0) return false;
                       if(row == 0 && column >= modeloRestricciones.getColumnCount()-2)
                           return  false;
                return true;          
              }
         };
        tabla.setModel(modeloRestricciones);
        String fila[]= new String[columnas];
        for (int filas = 0; filas < filas; filas++) {            
           for (int columnas = 0; columnas < columnas; columnas++) {
             fila[columnas]= CoeficientesyZ[filas][columnas];  
           }
           modeloRestricciones.addRow(fila);
       }
        //----------------------------------------------------------------------
        modeloRestricciones.setValueAt("", 0, columnas-2);
        modeloRestricciones.setValueAt("", 0, columnas-1);
        //----------------------------------------------------------------------
    }
    public DefaultTableModel getModeloRestricciones() {
        return modeloRestricciones;
    }
    //--------------------------------------------------------------------------
    public String getObjetivo() {
        return objetivo;
    }
    //--------------------------------------------------------------------------
}
