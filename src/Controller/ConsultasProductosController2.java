package Controller;

import Alerts.Alerts;
import Conexion.ConexionSQL;

import Model.Productos;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class ConsultasProductosController2 implements Initializable {

    BoletaController bolController;
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
    int myIndex, fila;

    @FXML
    private JFXTextField txtKila;

    private String CP_deBol;
    private String descripcion_deBol;
    private int cantidad_deBol;
    private double precioUnit_deBol;
    private String material_deBol;
    private String Kilates_deBol;

    public void getPro(BoletaController bolController) {
      //  System.out.println("listo ya envie productos");
        this.bolController = bolController;
    }
    ObservableList<Productos> products = FXCollections.observableArrayList();

    @FXML
    void sendCantidad(ActionEvent event) {
        fila = TvProduc.getSelectionModel().getSelectedIndex();
        // System.out.println(fila);
        if (fila == -1) {
            Alerts erro = new Alerts();
            erro.mostrarAlertError(event, "Fila Vacia", "Debes de seleccionar una fila de la tabla");
        } else {
            CP_deBol = TvProduc.getItems().get(fila).getCP_inven();
            descripcion_deBol = TvProduc.getItems().get(fila).getDescripcion_inven();
            cantidad_deBol = TvProduc.getItems().get(fila).getCantidad_inven();
            Kilates_deBol = TvProduc.getItems().get(fila).getKila_inven();
            material_deBol = TvProduc.getItems().get(fila).getTipoMaterial_inven();
            precioUnit_deBol = TvProduc.getItems().get(fila).getPrecio_inven();
            int c = 0;
            int j = 0;
            double nada=0.0;
            String cant = JOptionPane.showInputDialog("ingrese cantidad");
            if ((cant.equals("")) || (cant.equals("0"))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar algun valor mayor que 0");
            } else {
                int canting = Integer.parseInt(cant);
                int comp = Integer.parseInt(comparar(CP_deBol));
                if (canting > comp) {
                    JOptionPane.showMessageDialog(null, "Ud. no cuenta con el stock apropiado");
                } else {
                    for (int i = 0; i < bolController.TVDetalle2.getItems().size(); i++) {
                        Object com = bolController.TVDetalle2.getItems().get(i).getCP_deBol();
                        if (CP_deBol.equals(com)) {
                            j = i;
                            bolController.ModificaStock(j,CP_deBol, descripcion_deBol, canting, precioUnit_deBol, nada, Kilates_deBol,material_deBol);
                         
                          c =c+ 1;
                          //  System.out.println(com + "asdadasd");
                     
                        }
                      
                    }
                    if (c == 0) {
                        bolController.AgregaProductos(CP_deBol, descripcion_deBol, canting, precioUnit_deBol, nada, Kilates_deBol,material_deBol);
                    }

                }
            }

        }
    }

    public String comparar(String id) {
        String cant = "";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM inventarios WHERE CP_inven='" + id + "'");
            while (rs.next()) {
                cant = rs.getString("cantidad_inven");
            }

        } catch (SQLException ex) {

        }
        return cant;
    }

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
            /*
            TvProduc.setRowFactory(tv -> {
                TableRow<Productos> myRow = new TableRow<>();
                myRow.setOnMouseClicked(event
                        -> {
                    if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                        //  myIndex = TvProduc.getSelectionModel().getSelectedIndex();
                        //   id = TvProduc.getItems().get(myIndex).getCP_inven();
                        //  txtID1.setText(id);
                        //   txtDes.setText(TvProduc.getItems().get(myIndex).getDescripcion_inven());
                        // txtStck.setText(TvProduc.getItems().get(myIndex).getCantidad_inven() + "");
                        // txtCons.setText(TvProduc.getItems().get(myIndex).getTipoMaterial_inven());
                        // txtPrecio.setText(TvProduc.getItems().get(myIndex).getPrecio_inven() + "");
                        //    txtKila.setText(TvProduc.getItems().get(myIndex).getKila_inven());
                        // txtGramos.setText(TvProduc.getItems().get(myIndex).getGramos_inven() + "");
                    }
                });
                return myRow;
            });
             */
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
