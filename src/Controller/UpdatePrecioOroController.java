package Controller;

import Alerts.Alerts;
import Conexion.ConexionSQL;
import Model.PreciosHoy;
import Model.Productos;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UpdatePrecioOroController implements Initializable {

    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event;
    PreparedStatement ps;
    @FXML
    private TableView<Productos> TvProduc;

    @FXML
    private TableColumn<Productos, String> colID;

    @FXML
    private TableColumn<Productos, String> colDes;

    @FXML
    private TableColumn<Productos, Integer> colStock;

    @FXML
    private TableColumn<Productos, String> colCons;

    @FXML
    private TableColumn<Productos, Double> colPrecio;

    @FXML
    private TableColumn<Productos, String> colKila;
    @FXML
    private TableColumn<Productos, Double> colGramos;
    @FXML
    private JFXComboBox<String> comboPrecios;
double prcio_in;
    @FXML
    private JFXTextField txtPrecios;
double precios;
    @FXML
    void Actualiza(ActionEvent event) {
        Double _10k,_12k,_14k;
        String kilates = comboPrecios.getSelectionModel().getSelectedItem().toString().toLowerCase();
        precios=Double.valueOf(txtPrecios.getText());
        precios= Math.round(precios * 100.0) / 100.0;
        if (txtPrecios.getText().isEmpty()) {
            Alerts error = new Alerts();
            error.mostrarAlertError(event, "Campo Vacío", "El campo precio, está Vacío");
        } else {
            if (kilates.equalsIgnoreCase("10K")) {
                try {
                    ps = cn.prepareStatement("UPDATE PreciosHoy SET PrecioHoy10K=?");
                    ps.setDouble(1, precios);
                    ps.executeUpdate();
                    String sql = "SELECT * FROM PreciosHoy";
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    rs.next();
                    _10k=rs.getDouble("PrecioHoy10K");
                    double roundDbl = Math.round(_10k*100.0)/100.0;
                    System.out.println(roundDbl);
                    ///////////////////////////////////////////////////////////////////////////////
                    
                    ps=cn.prepareStatement("update inventarios set precio_inven="+"Gramos_inven*"+roundDbl+"where tipoMaterial_inven='oro' and kila_inven='10k'" );
                    ps.executeUpdate();
                     Alerts info=new Alerts();
                    info.mostrarAlertInfo(event, "Actualizados", "Actualizados artículos de oro de 10 Kilates");
                } catch (Exception s) {

                }
            } else if (kilates.equalsIgnoreCase("12K")) {
                try {
                    ps = cn.prepareStatement("UPDATE PreciosHoy SET PrecioHoyOro12K=?");
                        ps.setDouble(1, precios);
                    ps.executeUpdate();
                     String sql = "SELECT * FROM PreciosHoy";
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    rs.next();
                    _12k=rs.getDouble("PrecioHoyOro12K");
                    ps=cn.prepareStatement("update inventarios set precio_inven=Gramos_inven*"+_12k+"where tipoMaterial_inven='oro' and kila_inven='12k'" );
                    ps.executeUpdate();
                     Alerts info=new Alerts();
                    info.mostrarAlertInfo(event, "Actualizados", "Actualizados artículos de oro de 12 Kilates");
                } catch (Exception s) {
                   
                }
            } else {
                try {
                    ps = cn.prepareStatement("UPDATE PreciosHoy SET PrecioHoyOro14K=?");
                      ps.setDouble(1, precios);
                    ps.executeUpdate();
                    String sql = "SELECT * FROM PreciosHoy";
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    rs.next();
                    _14k=rs.getDouble("PrecioHoyOro12K");
                    ps=cn.prepareStatement("update inventarios set precio_inven=Gramos_inven*"+_14k+"where tipoMaterial_inven='oro' and kila_inven='14k'" );
                    ps.executeUpdate();
                     Alerts info=new Alerts();
                    info.mostrarAlertInfo(event, "Actualizados", "Actualizados artículos de oro de 14 Kilates");
                } catch (Exception s) {

                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("10K", "12K", "14K");
        comboPrecios.setItems(list);
    }

}
