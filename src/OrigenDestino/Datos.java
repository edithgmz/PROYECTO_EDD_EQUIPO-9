package OrigenDestino;

/**
 *
 * @author EdithGmz
 */
public class Datos {
    private double dCant;
    private String sPrecio;
    private boolean bLleno;
    //Constructor
    public Datos(double dC, String sP, boolean bL){
        dCant = dC;
        sPrecio = sP;
        bLleno = bL;
    }
    //Métodos Get
    public double getCant() {
        return dCant;
    }
    public String getPrecio() {
        return sPrecio;
    }
    public boolean isLleno() {
        return bLleno;
    }
    //Métodos Set
    public void setCant(double dCant) {
        this.dCant = dCant;
    }
    public void setPrecio(String sPrecio) {
        this.sPrecio = sPrecio;
    }
    public void setLleno(boolean bLleno) {
        this.bLleno = bLleno;
    }
}
