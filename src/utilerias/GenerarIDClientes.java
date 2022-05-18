package utilerias;

public class GenerarIDClientes {

   private String codPro ="";
    private String dato;
    private int cont = 1;
    private int parnum;

    public void idClientes(String dato) {
        parnum = Integer.parseInt(dato.substring(3));
        
        if ((parnum >= 1000000) || (parnum < 10000000)) {
            int can = cont + parnum;
            codPro = "Cli" + can;
        }
        if ((parnum >= 100000) || (parnum < 1000000)) {
            int can = cont + parnum;
            codPro = "Cli0" + can;
        }
        if ((parnum >= 10000) || (parnum < 100000)) {
            int can = cont + parnum;
            codPro = "Cli00" + can;
        }
        if ((parnum >= 1000) || (parnum < 10000)) {
            int can = cont + parnum;
            codPro = "Cli000" + can;
        }
        if ((parnum >= 100) || (parnum < 1000)) {
            int can = cont + parnum;
            codPro = "Cli0000" + can;
        }

        if ((parnum >= 9) || (parnum < 100)) {
            int can = cont + parnum;
            codPro = "Cli00000" + can;
        }
        if ((parnum < 9)) {
            int can = cont + parnum;
            codPro = "Cli000000" + can;
        }

    }

    public String serie() {
        return codPro;
    }
    
}
