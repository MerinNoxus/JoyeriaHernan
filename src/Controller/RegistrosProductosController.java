package Controller;

import Alerts.Alerts;
import Conexion.ConexionSQL;
import Model.RegistroProductos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilerias.GnerarID;

public class RegistrosProductosController implements Initializable {

    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event ;
    PreparedStatement ps;
    @FXML
    private TableView<RegistroProductos> TvProduc;

    @FXML
    private TableColumn<RegistroProductos, String> colID;

    @FXML
    private TableColumn<RegistroProductos, String> colDes;

    @FXML
    private TableColumn<RegistroProductos, Integer> colStock;

    @FXML
    private TableColumn<RegistroProductos, String> colCons;

    @FXML
    private TableColumn<RegistroProductos, Double> colPrecio;

    @FXML
    private TableColumn<RegistroProductos, String> colKila;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtDes;

    @FXML
    private JFXTextField txtStck;

    @FXML
    private JFXTextField txtCons;

    @FXML
    private JFXTextField txtPrecio;

    @FXML
    private JFXTextField txtKila;

    @FXML
    private JFXButton btnInsert;

    @FXML
    void Insert(ActionEvent event) throws SQLException {
        insert();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConexionSQL cc = new ConexionSQL();
        Connection cn = cc.obtener_conexion();
        numeros();
        ShowProducts();
    }

    public ObservableList<RegistroProductos> getProductosList() {

        ObservableList<RegistroProductos> productosList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM inventarios";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            RegistroProductos produc;
            while (rs.next()) {
                produc = new RegistroProductos(rs.getString("CP_inven"), rs.getString("Descripcion_inven"), rs.getInt("cantidad_inven"), rs.getString("tipoMaterial_inven"), rs.getInt("precio_inven"), rs.getString("kila_inven"));
                productosList.add(produc);
            }
        } catch (SQLException ex) {
            System.out.println("nada");
        }
        return productosList;
    }

    public void ShowProducts() {

        ObservableList<RegistroProductos> list = getProductosList();
        colID.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("CP_inven"));
        colDes.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("Descripcion_inven"));
        colStock.setCellValueFactory(new PropertyValueFactory<RegistroProductos, Integer>("cantidad_inven"));
        colCons.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("tipoMaterial_inven"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<RegistroProductos, Double>("precio_inven"));
        colKila.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("kila_inven"));
        TvProduc.getColumns().setAll(colID, colDes, colStock, colCons, colPrecio, colKila);
        TvProduc.getItems().setAll(list);
    }

    void numeros() {

        String c = "";
        String SQL = "select max(CP_inven) from inventarios";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtID.setText("Pro0000001");
            } else {
                GnerarID gen = new GnerarID();
                gen.idProductos(c);
                txtID.setText(gen.serie());
            }

        } catch (SQLException ex) {
            System.out.println("no");
        }
    }

    private void insert() throws SQLException {

        String insertSql = "INSERT INTO inventarios (CP_inven, Descripcion_inven, cantidad_inven, tipoMaterial_inven, precio_inven, kila_inven) VALUES (?,?,?,?,?,?)";
        try {
            ps = cn.prepareCall(insertSql);
            ps.setString(1, txtID.getText());
            ps.setString(2, txtDes.getText());
            ps.setString(3, txtStck.getText());
            ps.setString(4, txtCons.getText());
            ps.setString(5, txtPrecio.getText());
            ps.setString(6, txtKila.getText());
            
            if(txtID.getText().isEmpty()){
            Alerts vacio =new Alerts();
                
            vacio.mostrarAlertWarning(event, "Campo ID vacio", "Debes de agregar el campo del ID");
            }
            ps.executeUpdate();
            ShowProducts();
            txtID.setText("");
            numeros();
            txtCons.setText("");
            txtDes.setText("");
            txtKila.setText("");
            txtPrecio.setText("");
            txtStck.setText("");
       } catch (Exception w) {
        }

    }

}
