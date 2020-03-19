/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pascualon
 */
public class TablaSimplex {
     public static void TablaSolucion(int xn,int numRestric,DefaultTableModel modeloRestricciones, JTable tabla){          
          int cantidad_S = cantidad_S(modeloRestricciones); 
          int cantidad_A =cantidad_A(modeloRestricciones);
          int numeroColumnas = xn+cantidad_A+cantidad_S;
          
          DefaultTableModel modeloSolucion = crearModeloTabla(numeroColumnas);
          tabla.setModel(modeloSolucion); 
          
          //creacion de filas
          String filas[] = new String[numeroColumnas+4];
          for (int i = 0; i < (numRestric+4); i++){
              modeloSolucion.addRow(filas);
          }
               
          agregarX_S_A(xn, cantidad_S, cantidad_A, modeloSolucion, modeloRestricciones);
          agregarBase_Xb_Xn(modeloSolucion, modeloRestricciones);
          agregarIndices(xn,modeloRestricciones, modeloSolucion);
          
          //--------------------------------------------------------------------
          modeloSolucion.setValueAt("        Cj", 0, 0); 
          modeloSolucion.setValueAt("Xb  / Xj", 1, 0); 
          modeloSolucion.setValueAt("   Zj ",modeloSolucion.getRowCount()-2, 0); 
          modeloSolucion.setValueAt(" Cj - Zj", modeloSolucion.getRowCount()-1, 0);          
          modeloSolucion.setValueAt("Z = ",modeloSolucion.getRowCount()-2,modeloSolucion.getColumnCount()-3);
          ajustarTabla(tabla, modeloSolucion);
          //--------------------------------------------------------------------
    }
     //-------------------------------------------------------------------------
     private static int cantidad_A(DefaultTableModel modeloRestricciones){
        int cantidad_A=0;
        int row = modeloRestricciones.getRowCount();
        int columna = modeloRestricciones.getColumnCount();
        String tem ;
        for (int fila = 1; fila < row; fila++) {
            tem= String.valueOf(modeloRestricciones.getValueAt(fila, columna-2));              
            if(tem.equals(">=") || tem.equals(">"))cantidad_A ++;
            else if(tem.equals("="))cantidad_A ++;
        }
        return cantidad_A;
    }
     //-------------------------------------------------------------------------
    public static void agregarCj(int Xn,int numeroColumnas,int cantidad_S,int cantidad_A ,DefaultTableModel modeloRestricciones,DefaultTableModel modeloSolucion){
        for (int columnas = 1; columnas <= Xn; columnas++) {
            modeloSolucion.setValueAt(modeloRestricciones.getValueAt(0, columnas), 0, columnas);
        }
        for (int columnas = Xn; columnas <= (Xn+cantidad_S) ; columnas++) {
            modeloSolucion.setValueAt("0", 0, columnas);
        }
        for (int columnas = (Xn+cantidad_S); columnas <= (Xn+cantidad_S+cantidad_A); columnas++) {
            modeloSolucion.setValueAt("M", 0, columnas);
        } 
    }
    //--------------------------------------------------------------------------
    public static void agregarBase_Xb_Xn(DefaultTableModel modeloSolucion,DefaultTableModel modeloRestricciones){
        int column = modeloRestricciones.getColumnCount();
        int s=1,a=1;
        int row = modeloRestricciones.getRowCount();
        
        modeloSolucion.setValueAt("Base", 1, modeloSolucion.getColumnCount()-3);
        modeloSolucion.setValueAt("Xn", 1, modeloSolucion.getColumnCount()-2);
        
        for (int i = 1; i < row; i++){
            if(String.valueOf(modeloRestricciones.getValueAt(i, column-2)).equalsIgnoreCase("=") || String.valueOf(modeloRestricciones.getValueAt(i, column-2)).equalsIgnoreCase(">=")){
               modeloSolucion.setValueAt("A"+a, i+1, modeloSolucion.getColumnCount()-3);
               modeloSolucion.setValueAt("M", i+1, 0);
               a++;
            }
            else{ 
                modeloSolucion.setValueAt("S"+s, i+1, modeloSolucion.getColumnCount()-3);
                modeloSolucion.setValueAt("0", i+1, 0);
                s++;
            }
            modeloSolucion.setValueAt(""+modeloRestricciones.getValueAt(i, column-1), i+1, modeloSolucion.getColumnCount()-2);
        }
    }
    //--------------------------------------------------------------------------
    public static void ajustarTabla( JTable tabla, DefaultTableModel modeloSolucion ){
          tabla.setRowHeight(25);
          tabla.getColumnModel().getColumn(modeloSolucion.getColumnCount()-3).setPreferredWidth(35);
          tabla.getColumnModel().getColumn(modeloSolucion.getColumnCount()-2).setPreferredWidth(70);
          tabla.getColumnModel().getColumn(0).setPreferredWidth(50);  
    }
    //--------------------------------------------------------------------------
    public static void agregarX_S_A(int Xn,int cantidad_S,int cantidad_A,DefaultTableModel modeloSolucion,DefaultTableModel modeloRestricciones){
        int s=1,A=1;
        
        for (int columnas = 1; columnas <= Xn; columnas++) {// Agrega las x 
          modeloSolucion.setValueAt("X"+columnas, 1, columnas);
          modeloSolucion.setValueAt(modeloRestricciones.getValueAt(0, columnas), 0, columnas);
        }        
        
        for (int columnas = Xn+1; columnas <= (Xn+cantidad_S); columnas++) {// agragra las s en la segunda fila
           modeloSolucion.setValueAt("S"+s, 1, columnas);
           modeloSolucion.setValueAt("0", 0, columnas);           
            s++;
        }   
        
        for (int columnas = (Xn+cantidad_S)+1; columnas <= (Xn+cantidad_S+cantidad_A); columnas++) {// agragra las A en la segunda fila
           modeloSolucion.setValueAt("A"+A, 1, columnas);
           modeloSolucion.setValueAt("M", 0, columnas);
           A++;
        }
    } 
   //---------------------------------------------------------------------------
    public static  DefaultTableModel crearModeloTabla(int numeroColumnas){
         DefaultTableModel modelo;
         String cabeza[]= new String[numeroColumnas+4];
         
         for (int i = 0; i < (numeroColumnas +4); i++) {
             cabeza[i]="";  
         }
                     
         modelo = new DefaultTableModel(cabeza,0){
             public boolean isCellEditable(int row, int column) {
                return  false;
             }
          };   
          return modelo;
    }   
    //--------------------------------------------------------------------------
    private static int cantidad_S(DefaultTableModel modeloRestricciones){
        int cantidad_S=0;
        int row = modeloRestricciones.getRowCount();
        int columna = modeloRestricciones.getColumnCount();
        String temp;
        
        for (int fila = 1; fila < row; fila++) {
            temp = String.valueOf(modeloRestricciones.getValueAt(fila, columna-2));            
            if(temp.equalsIgnoreCase(">=") || temp.equalsIgnoreCase(">")|| temp.equalsIgnoreCase("<=") ||  temp.equalsIgnoreCase("<"))cantidad_S ++;            
        }
        return cantidad_S;
    }
    //--------------------------------------------------------------------------
    public static void agregarIndices(int xn,DefaultTableModel modeloRestricciones,DefaultTableModel modeloSolucion){
        int s=1,a=1;
        int row = modeloSolucion.getRowCount();
        int columna = modeloSolucion.getColumnCount();
        
        for (int fila = 2; fila < row-2; fila++) {
            for (int column = 1; column < columna-3; column++) {
                modeloSolucion.setValueAt("0", fila, column);
            }
        }
        
        String tem;
        int index;
        
        //aqui con estos for agregamos valores de las A & S
        for (int fila = 1; fila < modeloRestricciones.getRowCount(); fila++) {
            tem = String.valueOf(modeloRestricciones.getValueAt(fila, modeloRestricciones.getColumnCount()-2));
            for (int column = 1; column < modeloRestricciones.getColumnCount()-2; column++) {
                modeloSolucion.setValueAt(modeloRestricciones.getValueAt(fila,column), fila+1, column); 
            } 
            if(tem.equalsIgnoreCase("<=") || tem.equalsIgnoreCase("<")){
                  index = buscarIndice("S"+s, modeloSolucion);
                  modeloSolucion.setValueAt("1", fila+1, index);
                  s++;
            }
            else if(tem.equalsIgnoreCase(">=") || tem.equalsIgnoreCase(">")){
              index = buscarIndice("S"+s, modeloSolucion);
                  modeloSolucion.setValueAt("-1", fila+1, index);
                  s++;                  
                  index = buscarIndice("A"+a, modeloSolucion);
                  modeloSolucion.setValueAt("1", fila+1, index);
                  a++;                  
            }
            else if(tem.equalsIgnoreCase("=")){
                index = buscarIndice("A"+a, modeloSolucion);
                modeloSolucion.setValueAt("1", fila+1, index);
                a++;
            }
        }
    }
    //--------------------------------------------------------------------------
    private static int buscarIndice(String sVal,DefaultTableModel modeloSolucion){
        String temp;
        
        for (int columna = 1; columna < modeloSolucion.getColumnCount()-3; columna++) {
            temp = String.valueOf(modeloSolucion.getValueAt(1, columna));            
              if(temp.equalsIgnoreCase(sVal))return columna;
        }
        return -1;
    }
    //--------------------------------------------------------------------------
}
