/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrigenDestino;

import static Interfaz.OrigenDestino.dDatos;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class EsquinaNorOeste {
    private int iEsqFila=0;
    private int iEsqColum=0;
    private double dDemTotal;
    private double dOferTotal;
    private int iFila = dDatos.length;
    private int iColumna = dDatos.length;
    
    public boolean noroeste(JTable jtSolu, DefaultTableModel modSolu){
        calcularDemandaOfertaTotal(jtSolu, 0, 0);
        if(dDemTotal == dOferTotal){
            if(iEsqColum < iColumna || iEsqFila < iFila){
                double dDem = Double.parseDouble(""+jtSolu.getValueAt(jtSolu.getRowCount()-1, iEsqColum+1));
                double dOfer = Double.parseDouble(""+jtSolu.getValueAt(iEsqFila+1, jtSolu.getColumnCount()-1));
                if(dDem == dOfer){
                    jtSolu.setValueAt(0,jtSolu.getRowCount()-1, iEsqColum+1);
                    jtSolu.setValueAt(0,iEsqFila+1, jtSolu.getColumnCount()-1);
                    dDatos[iEsqFila][iEsqColum].setCant(dDem);
                    demanda(jtSolu);
                    oferta(jtSolu);
                    iEsqColum++;
                    iEsqFila++;
                } else if(dDem > dOfer){
                    dDem = dDem - dOfer;
                    jtSolu.setValueAt(0,iEsqFila+1, jtSolu.getColumnCount()-1);
                    jtSolu.setValueAt(dDem,jtSolu.getRowCount()-1, iEsqColum+1);
                    dDatos[iEsqFila][iEsqColum].setCant(dOfer);
                    oferta(jtSolu);
                    iEsqFila++;
                } else if(dOfer > dDem){
                    dOfer = dOfer - dDem;
                    jtSolu.setValueAt(0,jtSolu.getRowCount()-1, iEsqColum+1);
                    jtSolu.setValueAt(dOfer,iEsqFila+1, jtSolu.getColumnCount()-1);
                    dDatos[iEsqFila][iEsqColum].setCant(dDem);
                    demanda(jtSolu);
                    iEsqColum++;
                }
                return false;
            } else
                return true;
        } else{
            JOptionPane.showMessageDialog(null,"La oferta no satisface la demanda.");
            return  false;
        }
    }
    public void demanda(JTable jtSolu){
        for (int i = 0; i < iFila; i++){
            dDatos[i][iEsqColum].setLleno(true);
        }
    }
    public void oferta(JTable tableSolucion){
        for (int i = 0; i < iColumna; i++){
            dDatos[iEsqFila][i].setLleno(true);
        }
    }
    public void calcularDemandaOfertaTotal(JTable jtSolu, int filaExtra, int columnaExtra){
        dDemTotal=0;
        dOferTotal=0;
        try{
            for (int i = 1; i < (jtSolu.getColumnCount() - 1 - columnaExtra); i++){
            dDemTotal = dDemTotal+ Double.parseDouble(""+jtSolu.getValueAt(jtSolu.getRowCount() - 1 - filaExtra, i));
            }
            for (int i = 1; i < (jtSolu.getRowCount() - 1 - filaExtra); i++){
            dOferTotal = dOferTotal+ Double.parseDouble("" + jtSolu.getValueAt(i, jtSolu.getColumnCount() - 1 - columnaExtra));
            }
        } catch(Exception e){}
  }
}    
       
