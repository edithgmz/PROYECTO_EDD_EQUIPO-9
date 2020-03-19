/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrigenDestino;

import Tablas.EscribirLeer;
import java.io.File;
import java.io.Serializable;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author David
 */
public class GuardarOrigenDestino implements Serializable{
    public String[][] Coeficientes;
    public String Metodo;
    boolean Fila; 
    boolean Columna;

    public GuardarOrigenDestino(String sMet, boolean bFil, boolean bCol) {
        this.Metodo = sMet;
        this.Fila = bFil;
        this.Columna = bCol;
    }
    public String getMetodo() {
        return Metodo;
    }
    public void setMetodo(String sMet) {
        this.Metodo = sMet;
    }
    public boolean EsFila() {
        return Fila;
    }
    public void setFila(boolean bFil) {
        this.Fila = bFil;
    }
    public boolean EsColumna() {
        return Columna;
    }
    public void setColumna(boolean bCol) {
        this.Columna = bCol;
    }
    public void TablaDatos(JTable table){
        int iFila= table.getRowCount();
        int iColumna = table.getColumnCount();        
        Coeficientes = new String[iFila][iColumna];        
        for (int i = 0; i < iFila; i++) {
            for (int j = 0; j < iColumna; j++) {
                Coeficientes[i][j] =" "+ table.getValueAt(i,j);
            }
        }
    }
    public void ArchivoGuardado(String sRuta, String sNombre, GuardarOrigenDestino gVal) {
        int iOp = 0;
        if (!sRuta.substring(sRuta.length() - 4, sRuta.length()).equalsIgnoreCase(".tpt")) {
            sRuta = sRuta + ".tpt";
        }
        File fFile = new File(sRuta);
        if (fFile.exists()) {
            iOp = JOptionPane.showConfirmDialog(null, sNombre + " Ya existe \n¿Desea reemplazarlo?","Guardar",JOptionPane.WARNING_MESSAGE);
        }
        if (iOp == 0) {
            EscribirLeer archivo = new EscribirLeer();
            archivo.Fichero(sRuta, gVal);
        }
    }
}
