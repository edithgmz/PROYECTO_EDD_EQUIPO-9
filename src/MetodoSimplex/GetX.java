package MetodoSimplex;

import static Interfaz.MSimplex.jtaOp;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EdithGmz
 */
public class GetX {
    private static int filaX;
    public static void mostar(DefaultTableModel modeloSolucion,int Xn){
        int row = modeloSolucion.getRowCount();
        int column = modeloSolucion.getColumnCount();
        jtaOp.setText("   === Solución ===\n\n");
        String xn;
        for(int i = 1; i <= Xn; i++){
            xn = "X" + i;
            if(hayXn(modeloSolucion, xn, row,column))
                jtaOp.append(">> "+xn+" = "+AproximarNumero.valorAproximado(String.valueOf
        (modeloSolucion.getValueAt(filaX,column-2 ))) + "\n");
            else
                jtaOp.append(">> "+xn+" = 0"+"\n");
        } 
        jtaOp.append("\n>> Z = "+String.valueOf(modeloSolucion.getValueAt(row-2,column-2 )));
    }
    private static boolean hayXn(DefaultTableModel modSolu,String xn,int row,int column){
        String x;
        for(int i = 2; i < row-2; i++){
            x = String.valueOf(modSolu.getValueAt(i, column-3));
            if(x.equalsIgnoreCase(xn)){
                filaX = i;
                return true;
            }
        }
        return false;
    }
}
