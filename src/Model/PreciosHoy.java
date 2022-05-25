
package Model;

public class PreciosHoy {
   private double PrecioHoy10K,PrecioHoyOro12K,PrecioHoyOro14K,PrecioHoyPlata;

    public PreciosHoy(double PrecioHoy10K, double PrecioHoyOro12K, double PrecioHoyOro14K, double PrecioHoyPlata) {
        this.PrecioHoy10K = PrecioHoy10K;
        this.PrecioHoyOro12K = PrecioHoyOro12K;
        this.PrecioHoyOro14K = PrecioHoyOro14K;
        this.PrecioHoyPlata = PrecioHoyPlata;
    }

    public double getPrecioHoy10K() {
        return PrecioHoy10K;
    }

    public void setPrecioHoy10K(double PrecioHoy10K) {
        this.PrecioHoy10K = PrecioHoy10K;
    }

    public double getPrecioHoyOro12K() {
        return PrecioHoyOro12K;
    }

    public void setPrecioHoyOro12K(double PrecioHoyOro12K) {
        this.PrecioHoyOro12K = PrecioHoyOro12K;
    }

    public double getPrecioHoyOro14K() {
        return PrecioHoyOro14K;
    }

    public void setPrecioHoyOro14K(double PrecioHoyOro14K) {
        this.PrecioHoyOro14K = PrecioHoyOro14K;
    }

    public double getPrecioHoyPlata() {
        return PrecioHoyPlata;
    }

    public void setPrecioHoyPlata(double PrecioHoyPlata) {
        this.PrecioHoyPlata = PrecioHoyPlata;
    }
   
    
}
