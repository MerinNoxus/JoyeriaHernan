package Model;

public class Productos {

    private String CP_inven;
    private String Descripcion_inven;
    private Integer cantidad_inven;
    private String tipoMaterial_inven;
    private double precio_inven;
    private String kila_inven;
    private double Gramos_inven;

    public Productos() {
    }

    public Productos(String CP_inven, String Descripcion_inven, Integer cantidad_inven, String tipoMaterial_inven, double precio_inven, String kila_inven,double Gramos_inven) {
        this.CP_inven = CP_inven;
        this.Descripcion_inven = Descripcion_inven;
        this.cantidad_inven = cantidad_inven;
        this.tipoMaterial_inven = tipoMaterial_inven;
        this.precio_inven = precio_inven;
        this.kila_inven = kila_inven;
        this.Gramos_inven=Gramos_inven;
    }

    public double getGramos_inven() {
        return Gramos_inven;
    }

    public void setGramos_inven(double Gramos_inven) {
        this.Gramos_inven = Gramos_inven;
    }

    public String getCP_inven() {
        return CP_inven;
    }

    public void setCP_inven(String CP_inven) {
        this.CP_inven = CP_inven;
    }

    public String getDescripcion_inven() {
        return Descripcion_inven;
    }

    public void setDescripcion_inven(String Descripcion_inven) {
        this.Descripcion_inven = Descripcion_inven;
    }

    public Integer getCantidad_inven() {
        return cantidad_inven;
    }

    public void setCantidad_inven(Integer cantidad_inven) {
        this.cantidad_inven = cantidad_inven;
    }

    public String getTipoMaterial_inven() {
        return tipoMaterial_inven;
    }

    public void setTipoMaterial_inven(String tipoMaterial_inven) {
        this.tipoMaterial_inven = tipoMaterial_inven;
    }

    public double getPrecio_inven() {
        return precio_inven;
    }

    public void setPrecio_inven(double precio_inven) {
        this.precio_inven = precio_inven;
    }

    public String getKila_inven() {
        return kila_inven;
    }

    public void setKila_inven(String kila_inven) {
        this.kila_inven = kila_inven;
    }

   

}
