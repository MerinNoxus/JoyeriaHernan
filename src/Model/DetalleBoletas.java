
package Model;


public class DetalleBoletas {
    private String Num_deBol;
    private String CP_deBol;
    private String descripcion_deBol;
    private String cantidad_deBol;
    private String precioUnit_deBol;
    private String precioVenta_deBol;

    public DetalleBoletas(String Num_deBol, String CP_deBol, String descripcion_deBol, String cantidad_deBol, String precioUnit_deBol, String precioVenta_deBol) {
        this.Num_deBol = Num_deBol;
        this.CP_deBol = CP_deBol;
        this.descripcion_deBol = descripcion_deBol;
        this.cantidad_deBol = cantidad_deBol;
        this.precioUnit_deBol = precioUnit_deBol;
        this.precioVenta_deBol = precioVenta_deBol;
    }

    public String getNum_deBol() {
        return Num_deBol;
    }

    public void setNum_deBol(String Num_deBol) {
        this.Num_deBol = Num_deBol;
    }

    public String getCP_deBol() {
        return CP_deBol;
    }

    public void setCP_deBol(String CP_deBol) {
        this.CP_deBol = CP_deBol;
    }

    public String getDescripcion_deBol() {
        return descripcion_deBol;
    }

    public void setDescripcion_deBol(String descripcion_deBol) {
        this.descripcion_deBol = descripcion_deBol;
    }

    public String getCantidad_deBol() {
        return cantidad_deBol;
    }

    public void setCantidad_deBol(String cantidad_deBol) {
        this.cantidad_deBol = cantidad_deBol;
    }

    public String getPrecioUnit_deBol() {
        return precioUnit_deBol;
    }

    public void setPrecioUnit_deBol(String precioUnit_deBol) {
        this.precioUnit_deBol = precioUnit_deBol;
    }

    public String getPrecioVenta_deBol() {
        return precioVenta_deBol;
    }

    public void setPrecioVenta_deBol(String precioVenta_deBol) {
        this.precioVenta_deBol = precioVenta_deBol;
    }
    
}
