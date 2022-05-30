package Controller;

import Alerts.Alerts;
import Conexion.ConexionSQL;
import Model.Productos;
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
import javafx.scene.input.KeyEvent;
import utilerias.GenerarIDProductos;

public class RegistrosProductosController implements Initializable {

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
    private JFXTextField txtID;
    @FXML
    private JFXTextField txtGramos;
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
    double kilates;
    double gramos, precio, precio2;
    String gram;

    @FXML
    void Insert(ActionEvent event) throws SQLException {
        insert();
    }

    @FXML
    void eventKey(KeyEvent event) throws SQLException {
        Object evt = event.getSource();
        if (evt.equals(txtDes)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !event.getCharacter().equals(" ")) {
                event.consume();
            } else if (txtDes.getText().length() > 50) {
                event.consume();
            }
        } else if (evt.equals(txtStck)) {
            if (!Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
            } else if (txtStck.getText().length() > 4) {
                event.consume();
            }
        } else if (evt.equals(txtCons)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !event.getCharacter().equals(" ")) {
                event.consume();
            } else if (txtCons.getText().length() > 20) {
                event.consume();
            }
            // } else if (evt.equals(txtGramos)) {
            //gramos = Integer.valueOf(txtGramos.getText());
            //System.out.println(gramos);
            //  if (!Character.isDigit(event.getCharacter().charAt(0)) && !event.getCharacter().equals(".")) {
            //    event.consume();
            // } else if (txtGramos.getText().length() > 7) {
            //   event.consume();
            //}
        } else if (evt.equals(txtPrecio)) {
            if (!Character.isDigit(event.getCharacter().charAt(0)) && !event.getCharacter().equals(".")) {
                event.consume();
            } else if (txtPrecio.getText().length() > 10) {
                event.consume();
            }
        }
        GetDatos();
    }

    void GetDatos() throws SQLException {
        gramos = Double.valueOf(txtGramos.getText());
        if (txtKila.getText().equalsIgnoreCase("10K")) {
            precio = queryprecio(gramos, "PrecioHoy10K");
            txtPrecio.setText(String.valueOf(precio));
        } else if (txtKila.getText().equalsIgnoreCase("12K")) {
            precio = queryprecio(gramos, "PrecioHoyOro12K");
            txtPrecio.setText(String.valueOf(precio));
        } else if (txtKila.getText().equalsIgnoreCase("14K")) {
            precio = queryprecio(gramos, "PrecioHoyOro14K");
            txtPrecio.setText(String.valueOf(precio));
        }else{
            
        }
    }

    double queryprecio(double gramos, String precioHoy) throws SQLException {
        String sql = "SELECT * FROM PreciosHoy";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        kilates = rs.getDouble(precioHoy);
        precio = kilates * gramos;
        double precio = Math.round(this.precio * 100.0) / 100.0;
        return precio;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConexionSQL cc = new ConexionSQL();
        Connection cn = cc.obtener_conexion();
        numeros();
        ShowProducts();
    }

    public ObservableList<Productos> getProductosList() {

        ObservableList<Productos> productosList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM inventarios";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Productos produc;
            while (rs.next()) {
                produc = new Productos(rs.getString("CP_inven"), rs.getString("Descripcion_inven"), rs.getInt("cantidad_inven"), rs.getString("tipoMaterial_inven"), rs.getDouble("precio_inven"), rs.getString("kila_inven"), rs.getDouble("Gramos_inven"));
                productosList.add(produc);
            }
        } catch (SQLException ex) {
            System.out.println("nada");
        }
        return productosList;
    }

    public void ShowProducts() {

        ObservableList<Productos> list = getProductosList();
        colID.setCellValueFactory(new PropertyValueFactory<>("CP_inven"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("Descripcion_inven"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad_inven"));
        colCons.setCellValueFactory(new PropertyValueFactory<>("tipoMaterial_inven"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_inven"));
        colKila.setCellValueFactory(new PropertyValueFactory<>("kila_inven"));
        colGramos.setCellValueFactory(new PropertyValueFactory<>("Gramos_inven"));
        TvProduc.getColumns().setAll(colID, colDes, colStock, colCons, colPrecio, colKila, colGramos);
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
                GenerarIDProductos gen = new GenerarIDProductos();
                gen.idProductos(c);
                txtID.setText(gen.serie());
            }

        } catch (SQLException ex) {
            System.out.println("no");
        }
    }

    private void insert() throws SQLException {

        String insertSql = "INSERT INTO inventarios (CP_inven, Descripcion_inven, cantidad_inven, tipoMaterial_inven, precio_inven, kila_inven,Gramos_inven) VALUES (?,?,?,?,?,?,?)";
        precio2 = Math.round(precio * 100.0) / 100.0;
        double gramm = Math.round(gramos * 100.0) / 100.0;
        try {
            ps = cn.prepareCall(insertSql);
            ps.setString(1, txtID.getText());
            ps.setString(2, txtDes.getText());
            ps.setString(3, txtStck.getText());
            ps.setString(4, txtCons.getText());
            if(precio==0){
                 ps.setDouble(5, Double.valueOf(txtPrecio.getText()));
            
            }else{
                 ps.setDouble(5, precio2);
            }
            ps.setString(6, txtKila.getText());
            if(gramm==0){
             ps.setDouble(7, Double.valueOf(txtGramos.getText()));
            }else{
                 ps.setDouble(7, gramm);
            }
           

            if (txtID.getText().isEmpty()) {
                Alerts vacio = new Alerts();
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
            txtGramos.setText("");
        } catch (Exception w) {
        }

    }

}
