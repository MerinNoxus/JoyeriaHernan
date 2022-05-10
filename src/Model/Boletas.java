
package Model;

public class Boletas {
   private String Num_Bol;
   private String CC_Bol;
   private String precioUnit_Bol;
   private String fecha_Bol;

    public Boletas(String Num_Bol, String CC_Bol, String precioUnit_Bol, String fecha_Bol) {
        this.Num_Bol = Num_Bol;
        this.CC_Bol = CC_Bol;
        this.precioUnit_Bol = precioUnit_Bol;
        this.fecha_Bol = fecha_Bol;
    }

    public String getNum_Bol() {
        return Num_Bol;
    }

    public void setNum_Bol(String Num_Bol) {
        this.Num_Bol = Num_Bol;
    }

    public String getCC_Bol() {
        return CC_Bol;
    }

    public void setCC_Bol(String CC_Bol) {
        this.CC_Bol = CC_Bol;
    }

    public String getPrecioUnit_Bol() {
        return precioUnit_Bol;
    }

    public void setPrecioUnit_Bol(String precioUnit_Bol) {
        this.precioUnit_Bol = precioUnit_Bol;
    }

    public String getFecha_Bol() {
        return fecha_Bol;
    }

    public void setFecha_Bol(String fecha_Bol) {
        this.fecha_Bol = fecha_Bol;
    }
   
   
}
