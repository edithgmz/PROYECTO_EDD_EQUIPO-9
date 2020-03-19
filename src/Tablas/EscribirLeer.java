/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import MetodoSimplex.GuardarSimplex;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class EscribirLeer {
    public void Fichero(String sFich, Object oVal){ 
        try{
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(sFich)); 
           oos.writeObject(oVal);            
           oos.close();
        } catch (Exception e){
           JOptionPane.showMessageDialog(null,"Ocurrio un error al teclear el primer dato del archivo.","Error!",JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
        }
    }
    public ArrayList LeerFichero(String sFich) throws IOException{
        File ArchivoEntrada;
        FileInputStream CanalEntrada = null;
        ObjectInputStream ObjetoEntrada = null;
        ArrayList<GuardarSimplex> aAuxiliar = new ArrayList<GuardarSimplex>();
        boolean bFinal = true;
	try{
		ArchivoEntrada=new File(sFich);
		CanalEntrada = new FileInputStream(ArchivoEntrada);
		ObjetoEntrada = new ObjectInputStream(CanalEntrada);
		do{
			try{
			aAuxiliar.add((GuardarSimplex) ObjetoEntrada.readObject());
			}catch(IOException | ClassNotFoundException exc){
				bFinal=false;
			}
		}while (bFinal==true);
		ObjetoEntrada.close();	
		CanalEntrada.close();
//		archivoEntrada.delete();
	}catch (IOException e) {
//                objetoEntrada.close();	
//		canalEntrada.close();
		System.out.println("Error al extraer.");
//                 JOptionPane.showMessageDialog(null,"No se pudo cargar el archivo  ...");
	}
        return aAuxiliar;
    } 
}
