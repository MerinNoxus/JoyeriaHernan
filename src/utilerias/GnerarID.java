package utilerias;

public class GnerarID {

   private String codPro ="";
    private String dato;
    private int cont = 1;
    private int parnum;

    public void idProductos(String dato) {
        parnum = Integer.parseInt(dato.substring(3));
        
        if ((parnum >= 1000000) || (parnum < 10000000)) {
            int can = cont + parnum;
            codPro = "Pro" + can;
        }
        if ((parnum >= 100000) || (parnum < 1000000)) {
            int can = cont + parnum;
            codPro = "Pro0" + can;
        }
        if ((parnum >= 10000) || (parnum < 100000)) {
            int can = cont + parnum;
            codPro = "Pro00" + can;
        }
        if ((parnum >= 1000) || (parnum < 10000)) {
            int can = cont + parnum;
            codPro = "Pro000" + can;
        }
        if ((parnum >= 100) || (parnum < 1000)) {
            int can = cont + parnum;
            codPro = "Pro0000" + can;
        }

        if ((parnum >= 9) || (parnum < 100)) {
            int can = cont + parnum;
            codPro = "Pro00000" + can;
        }
        if ((parnum < 9)) {
            int can = cont + parnum;
            codPro = "Pro000000" + can;
        }

    }

    public String serie() {
        return codPro;
    }
    
}
