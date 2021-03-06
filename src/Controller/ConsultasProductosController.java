package Controller;

import Conexion.ConexionSQL;
import Model.Productos;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyEvent;

public class ConsultasProductosController implements Initializable {

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
    private JFXTextField txtsearch;

    @FXML
    void busca(KeyEvent event) {

    }
    @FXML
    private JFXTextField txtKila;

    ObservableList<Productos> products = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String query = "SELECT * FROM inventarios";
        try {

            Statement st = cn.createStatement();
            ResultSet queO = st.executeQuery(query);
            while (queO.next()) {
                String id = queO.getString("CP_inven");
                String des = queO.getString("Descripcion_inven");
                int can = queO.getInt("cantidad_inven");
                String material = queO.getString("tipoMaterial_inven");
                double precio = queO.getDouble("precio_inven");
                precio = Math.round(precio * 100.0) / 100.0;
                String kt = queO.getString("kila_inven");
                double gramos = queO.getDouble("Gramos_inven");
                products.add(new Productos(id, des, can, material, precio, kt, gramos));

            }
            colID.setCellValueFactory(new PropertyValueFactory<>("CP_inven"));
            colDes.setCellValueFactory(new PropertyValueFactory<>("Descripcion_inven"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad_inven"));
            colCons.setCellValueFactory(new PropertyValueFactory<>("tipoMaterial_inven"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_inven"));
            colKila.setCellValueFactory(new PropertyValueFactory<>("kila_inven"));
            colGramos.setCellValueFactory(new PropertyValueFactory<>("Gramos_inven"));
            TvProduc.setItems(products);

            FilteredList<Productos> filterdata = new FilteredList<>(products, b -> true);

            txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filterdata.setPredicate(registroProductos -> {
                    if (newValue.isEmpty() || newValue == "" || newValue == null) {
                        return true;

                    }
                    String ser = newValue.toLowerCase();
                    if (registroProductos.getDescripcion_inven().toLowerCase().indexOf(ser) > -1) {
                        return true;
                    } else if (registroProductos.getCP_inven().toLowerCase().indexOf(ser) > -1) {
                        return true;
                    } else if (registroProductos.getTipoMaterial_inven().toLowerCase().indexOf(ser) > -1) {
                        return true;
                    } else if (registroProductos.getKila_inven().toLowerCase().indexOf(ser) > -1) {
                        return true;
                    } else {
                        return false;
                    }

                });

            });

            SortedList<Productos> sorted = new SortedList<>(filterdata);
            sorted.comparatorProperty().bind(TvProduc.comparatorProperty());
            TvProduc.setItems(sorted);
        } catch (Exception e) {
            System.out.println("si");
        }
    }

    /*
    public ObservableList<RegistroProductos> getProductosList() {

        ObservableList<RegistroProductos> productosList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM inventarios";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Productos produc;
            while (rs.next()) {
                produc = new Productos(rs.getString("CP_inven"), rs.getString("Descripcion_inven"), rs.getInt("cantidad_inven"), rs.getString("tipoMaterial_inven"), rs.getInt("precio_inven"), rs.getString("kila_inven"));
                productosList.add(produc);
            }
        } catch (SQLException ex) {
            System.out.println("nada");
        }
        return productosList;
    }

    public void ShowProducts() {

        ObservableList<RegistroProductos> list = getProductosList();
        ObservableList<RegistroProductos> filtro = getProductosList();
        
        colID.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("CP_inven"));
        colDes.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("Descripcion_inven"));
        colStock.setCellValueFactory(new PropertyValueFactory<RegistroProductos, Integer>("cantidad_inven"));
        colCons.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("tipoMaterial_inven"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<RegistroProductos, Double>("precio_inven"));
        colKila.setCellValueFactory(new PropertyValueFactory<RegistroProductos, String>("kila_inven"));
        TvProduc.getColumns().setAll(colID, colDes, colStock, colCons, colPrecio, colKila);
        TvProduc.getItems().setAll(list);
    
    }
  
     */
}
