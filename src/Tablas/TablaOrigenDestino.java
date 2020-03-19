package Tablas;

import static Interfaz.OrigenDestino.dDatos;
import static Interfaz.OrigenDestino.modRest;
import OrigenDestino.Datos;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EdithGmz
 */
public class TablaOrigenDestino {
    public void tablaRestricciones(int iDest, int  iOri, JTable jtRest, int iDes){
        Object oCabeza[] = new String[iDest + 2+iDes];
        oCabeza[0] = ("");
        oCabeza[iDest + 1 + iDes] = ("Oferta");
        for (int i = 1; i <= (iDest + iDes); i++){
            oCabeza[i] = ("Destino " + i );
        }
        modRest = new DefaultTableModel(oCabeza, 0){
            public boolean isCellEditable(int row, int column){
                if (column == 0 || (row == jtRest.getRowCount()-1 && column == jtRest.getColumnCount()-1))
                    return false;
                return true;
            }
        };
        jtRest.setModel(modRest);
        String fila[] = new String[iDest + 2 + iDes];
        for (int i = 0; i < iOri ; i++){
            fila[0] = new String("Origen " + (i + 1));
            modRest.addRow(fila);
        }
        fila[0] = new String("Demanda" );
        modRest.addRow(fila);
    }
    public void tablaSolucion(JTable jtRest, JTable jtSolu,int filaExtra, int columnaExtra){
        int row = jtRest.getRowCount();
        int column = jtRest.getColumnCount();
        extraerDatos(jtRest);
        Object oCabeza[] = new String[column + columnaExtra];
        for (int i = 0; i < (column +columnaExtra); i++)
            oCabeza[i] = ("");
        DefaultTableModel modSolu = new DefaultTableModel(oCabeza, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        jtSolu.setModel(modSolu);
        String sOrigen[];
        for (int i = 0; i < (row + 1 + filaExtra); i++){
            sOrigen= new String[column+columnaExtra];
            if(i > 0 && i < row){
                sOrigen[0]=" " + i;
            }
            modSolu.addRow(sOrigen);
        }
        for (int i = 0; i < modSolu.getColumnCount(); i++){
            jtSolu.getColumnModel().getColumn(i).setPreferredWidth(70);
        }
        String t;
        for (int i = 0; i < row-1; i++){
            t = ""+modRest.getValueAt(i, column-1);
            modSolu.setValueAt(t, i+1, column-1);
        }
        for (int i = 1; i < column-1; i++) {
            t = ""+modRest.getValueAt(row-1, i);
            modSolu.setValueAt(t, (modSolu.getRowCount() - 1 - columnaExtra), i);
            modSolu.setValueAt(i, 0, i);
        }
        modSolu.setValueAt("Demanda", row, 0);
        modSolu.setValueAt("Oferta ", 0, column-1);
        if(columnaExtra>0)
            modSolu.setValueAt("Penaliza", 0, column);
        if(filaExtra>0)
            modSolu.setValueAt("Penaliza", row+1, 0);
    }
    private void  extraerDatos(JTable jtRestriciones){
        int row = jtRestriciones.getRowCount();
        int column = jtRestriciones.getColumnCount();
        dDatos = new Datos[row-1][column-2];
        for (int i = 0; i < row-1; i++) {
            for (int j = 0; j < column-2; j++) {
                dDatos[i][j] = new Datos(0, ""+jtRestriciones.getValueAt(i, j+1), false);
            }
        }
    }
}
