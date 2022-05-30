package Model;

public class ProductosComprados {

    private String producto;
    private int stock;
    private double precioU;
    private double precioVenta;
    private String kt;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioU() {
        return precioU;
    }

    public void setPrecioU(double precioU) {
        this.precioU = precioU;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public ProductosComprados(String producto, int stock, double precioU, double precioVenta, String kt) {
        this.producto = producto;
        this.stock = stock;
        this.precioU = precioU;
        this.precioVenta = precioVenta;
        this.kt = kt;
    }

}
