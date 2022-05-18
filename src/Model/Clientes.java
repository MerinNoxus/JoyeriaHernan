
package Model;


public class Clientes {
    private String CC_clie;
    private String nombre_clie;
    private String apellido_clie;
    private String isclieFrecuent;

    public Clientes(String CC_clie, String nombre_clie, String apellido_clie,String isclieFrecuent ) {
        this.CC_clie = CC_clie;
        this.nombre_clie = nombre_clie;
        this.apellido_clie = apellido_clie;
        this.isclieFrecuent=isclieFrecuent;
    }

    public String getIsclieFrecuent() {
        return isclieFrecuent;
    }

    public void setIsclieFrecuent(String isclieFrecuent) {
        this.isclieFrecuent = isclieFrecuent;
    }

    public String getCC_clie() {
        return CC_clie;
    }

    public void setCC_clie(String CC_clie) {
        this.CC_clie = CC_clie;
    }

    public String getNombre_clie() {
        return nombre_clie;
    }

    public void setNombre_clie(String nombre_clie) {
        this.nombre_clie = nombre_clie;
    }

    public String getApellido_clie() {
        return apellido_clie;
    }

    public void setApellido_clie(String apellido_clie) {
        this.apellido_clie = apellido_clie;
    }
    
}
