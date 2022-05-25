package Model;
public class DetalleBoletas {
    private String CP_deBol;
    private String descripcion_deBol;
    private int cantidad_deBol;
    private double precioUnit_deBol;
    private double precioVenta_deBol;
    private String Kilates_deBol;
    private String material_deBol;
    
    public DetalleBoletas(String CP_deBol, String descripcion_deBol, int cantidad_deBol, double precioUnit_deBol, double precioVenta_deBol, String Kilates_deBol, String material_deBol) {
        this.CP_deBol = CP_deBol;
        this.descripcion_deBol = descripcion_deBol;
        this.cantidad_deBol = cantidad_deBol;
        this.precioUnit_deBol = precioUnit_deBol;
        this.precioVenta_deBol = precioVenta_deBol;
        this.Kilates_deBol = Kilates_deBol;
        this.material_deBol = material_deBol;
    }

    /*
    public DetalleBoletas(String CP_deBol, String descripcion_deBol, int cantidad_deBol, double precioUnit_deBol, String material_deBol, String Kilates_deBol) {

        this.CP_deBol = CP_deBol;
        this.descripcion_deBol = descripcion_deBol;
        this.cantidad_deBol = cantidad_deBol;
        this.precioUnit_deBol = precioUnit_deBol;
        this.Kilates_deBol = Kilates_deBol;
        this.material_deBol = material_deBol;
    }
     */
    public String getKilates_deBol() {
        return Kilates_deBol;
    }

    public void setKilates_deBol(String Kilates_deBol) {
        this.Kilates_deBol = Kilates_deBol;
    }

    public String getMaterial_deBol() {
        return material_deBol;
    }

    public void setMaterial_deBol(String material_deBol) {
        this.material_deBol = material_deBol;
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

    public int getCantidad_deBol() {
        return cantidad_deBol;
    }

    public void setCantidad_deBol(int cantidad_deBol) {
        this.cantidad_deBol = cantidad_deBol;
    }

    public double getPrecioUnit_deBol() {
        return precioUnit_deBol;
    }

    public void setPrecioUnit_deBol(double precioUnit_deBol) {
        this.precioUnit_deBol = precioUnit_deBol;
    }

    public double getPrecioVenta() {
        return precioVenta_deBol;
    }

    public void setPrecioVenta(double precioVenta_deBol) {
        this.precioVenta_deBol = precioVenta_deBol;
    }
}
