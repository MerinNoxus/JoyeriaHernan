package Controller;

import Conexion.ConexionSQL;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class menuBarReportesController implements Initializable {

    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnBoletas;

    @FXML
    private Button btnSisApartado;

    @FXML
    void ReporteProductos(ActionEvent event) {
        try {
            JasperReport report;
            JasperPrint jprint = null;
            report = JasperCompileManager.compileReport("src/reportes/ReportP.jrxml");
            jprint = JasperFillManager.fillReport(report, null, cc.obtener_conexion());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "ocurrio un error " + ex.getMessage());
        }

    }
    
    @FXML
    void ReporteClientes(ActionEvent event) {
 try {
            JasperReport report;
            JasperPrint jprint = null;
            report = JasperCompileManager.compileReport("src/reportes/ReporteClientes.jrxml");
            jprint = JasperFillManager.fillReport(report, null, cc.obtener_conexion());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "ocurrio un error " + ex.getMessage());
        }

    }
@FXML
    void ReporteBoletas(ActionEvent event) {
try {
            JasperReport report;
            JasperPrint jprint = null;
            report = JasperCompileManager.compileReport("src/reportes/ReporteBoletas.jrxml");
            jprint = JasperFillManager.fillReport(report, null, cc.obtener_conexion());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "ocurrio un error " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
