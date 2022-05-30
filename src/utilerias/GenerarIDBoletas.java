package utilerias;

public class GenerarIDBoletas {

   private String codBol ="";
    private String dato;
    private int cont = 1;
    private int parnum;

    public void idBol(String dato) {
        parnum = Integer.parseInt(dato.substring(5));
        if ((parnum >= 1000000) || (parnum < 10000000)) {
            int can = cont + parnum;
            codBol = "BolJH" + can;
        }
        if ((parnum >= 100000) || (parnum < 1000000)) {
            int can = cont + parnum;
            codBol = "BolJH0" + can;
        }
        if ((parnum >= 10000) || (parnum < 100000)) {
            int can = cont + parnum;
            codBol = "BolJH00" + can;
        }
        if ((parnum >= 1000) || (parnum < 10000)) {
            int can = cont + parnum;
            codBol = "BolJH000" + can;
        }
        if ((parnum >= 100) || (parnum < 1000)) {
            int can = cont + parnum;
            codBol = "BolJH0000" + can;
        }

        if ((parnum >= 9) || (parnum < 100)) {
            int can = cont + parnum;
            codBol = "BolJH00000" + can;
        }
        if ((parnum < 9)) {
            int can = cont + parnum;
            codBol = "BolJH000000" + can;
        }

    }

    public String serie() {
        return codBol;
    }
    
}
