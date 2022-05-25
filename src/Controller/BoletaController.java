package Controller;

import Alerts.Alerts;
import Conexion.ConexionSQL;
import Model.DetalleBoletas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilerias.GenerarIDBoletas;

public class BoletaController implements Initializable, Runnable {

    Thread h1;
    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event;
    PreparedStatement ps;

    @FXML
    private JFXTextField txtNom;
    @FXML
    private TextField txtDescuento;
    @FXML
    private JFXButton btnPorducts;
    @FXML
    private JFXTextField txtClientFre;

    @FXML
    private JFXTextField txtID1;

    @FXML
    protected TableView<DetalleBoletas> TVDetalle2;

    @FXML
    private TableColumn<DetalleBoletas, String> colCodPro;

    @FXML
    private TableColumn<DetalleBoletas, String> colDes;

    @FXML
    protected TableColumn<DetalleBoletas, Integer> colStock;

    @FXML
    private TableColumn<DetalleBoletas, Double> colPrecioU;
    @FXML
    private TableColumn<DetalleBoletas, String> colKilates;
    @FXML
    private TableColumn<DetalleBoletas, Double> colPrecioVenta;
    @FXML
    private TableColumn<DetalleBoletas, String> colMaterial;

    double x0ffset, y0ffset;
    @FXML
    private JFXTextField txtApe;
    @FXML
    private JFXButton btnsearchClient;
    @FXML
    private JFXTextField txtNumBol;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtFecha;
    double descuento;
    BoletaController bol1;
    ObservableList<DetalleBoletas> detalle = FXCollections.observableArrayList();
    private String CP_deBol;
    private String descripcion_deBol;
    private String Kilates_deBol;
    private String material_deBol;

    public void ModificaStock(int index, String CP_deBol, String descripcion_deBol, int cantidad_deBol, double precioUnit_deBol, double precioVenta_deBol, String Kilates_deBol, String material_deBol) {
        detalle.set(index, new DetalleBoletas(CP_deBol, descripcion_deBol, cantidad_deBol, precioUnit_deBol, precioVenta_deBol, Kilates_deBol, material_deBol));
    }

    public void AgregaProductos(String CP_deBol, String descripcion_deBol, int cantidad_deBol, double precioUnit_deBol, double precioVenta_deBol, String Kilates_deBol, String material_deBol) {
        detalle.add(new DetalleBoletas(CP_deBol, descripcion_deBol, cantidad_deBol, precioUnit_deBol, precioVenta_deBol, Kilates_deBol, material_deBol));
        colCodPro.setCellValueFactory(new PropertyValueFactory<>("CP_deBol"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion_deBol"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad_deBol"));
        colPrecioU.setCellValueFactory(new PropertyValueFactory<>("precioUnit_deBol"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta_deBol"));
        colKilates.setCellValueFactory(new PropertyValueFactory<>("Kilates_deBol"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material_deBol"));
        TVDetalle2.setItems(detalle);
    }

    @FXML
    void openProducts(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader producs = new FXMLLoader(getClass().getResource("/view/ConsultasProductos2.fxml"));
            Parent root2 = (Parent) producs.load();
            ConsultasProductosController2 consulProducs2 = producs.getController();
            consulProducs2.getPro(bol1);

            stage.initStyle(StageStyle.DECORATED.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);

            root2.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x0ffset = event.getSceneX();
                    y0ffset = event.getSceneY();
                }
            });
            stage.setScene(new Scene(root2));
            stage.getIcons().add(new Image("/Imagenes/icons8_add_shopping_cart_3.ico"));
            root2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - x0ffset);
                    stage.setY(event.getScreenY() - y0ffset);
                }

            });
            stage.setTitle("Joyería Hernández-Productos");

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BoletaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void btnCalculo(ActionEvent event) {
        double pre;
        int can;
        double subtotal = 0;
        double imp = 0.0;
        double valor;
        //descuento = Double.valueOf(txtDescuento.getText());

        //  System.out.println("nooooo entra");
        for (int i = 0; i < TVDetalle2.getItems().size(); i++) {
            pre = TVDetalle2.getItems().get(i).getPrecioUnit_deBol();
            can = TVDetalle2.getItems().get(i).getCantidad_deBol();
            CP_deBol = TVDetalle2.getItems().get(i).getCP_deBol();
            descripcion_deBol = TVDetalle2.getItems().get(i).getDescripcion_deBol();
            material_deBol = TVDetalle2.getItems().get(i).getMaterial_deBol();
            Kilates_deBol = TVDetalle2.getItems().get(i).getKilates_deBol();
            //System.out.println(can + "\n" + pre);
            //    if (descuento == 0 || txtDescuento.getText().isEmpty()) {
            imp = pre * can;
            subtotal += imp;
            valor = Math.rint(imp * 100) / 100;
            detalle.set(i, new DetalleBoletas(CP_deBol, descripcion_deBol, can, pre, valor, Kilates_deBol, material_deBol));
            System.out.println(valor);
            colCodPro.setCellValueFactory(new PropertyValueFactory<>("CP_deBol"));
            colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion_deBol"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad_deBol"));
            colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta_deBol"));
            colPrecioU.setCellValueFactory(new PropertyValueFactory<>("precioUnit_deBol"));
            colMaterial.setCellValueFactory(new PropertyValueFactory<>("material_deBol"));
            colKilates.setCellValueFactory(new PropertyValueFactory<>("Kilates_deBol"));
            TVDetalle2.setItems(detalle);
            //}

        }

        txtTotal.setText("" + Math.rint(subtotal * 100) / 100);
    }

    @FXML
    void btnDelete(ActionEvent event) {
        if (TVDetalle2.getSelectionModel().isEmpty()) {
            Alerts nose = new Alerts();
            nose.mostrarAlertError(event, "Error", "Debes de Seleccionar una fila de la tabla para poder eliminar");
        } else {
            detalle.remove(TVDetalle2.getSelectionModel().getSelectedItem());
        }
    }

    public void getInfoClient(String codID, String name, String ClientFre, String lastname) {
        txtID1.setText(codID);
        txtNom.setText(name);
        txtClientFre.setText(ClientFre);
        txtApe.setText(lastname);
    }

    @FXML
    void OpenClients(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader clients = new FXMLLoader(getClass().getResource("/view/ConsultaClientes2.fxml"));
            AnchorPane root2 = (AnchorPane) clients.load();
            ConsultaClientesController2 consulClient2 = clients.getController();
            consulClient2.getInfoClient(bol1);

            stage.initStyle(StageStyle.DECORATED.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            root2.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x0ffset = event.getSceneX();
                    y0ffset = event.getSceneY();
                }
            });
            stage.setScene(new Scene(root2));
            stage.getIcons().add(new Image("/Imagenes/icons8_people.ico"));
            root2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - x0ffset);
                    stage.setY(event.getScreenY() - y0ffset);
                }

            });
            stage.setTitle("Joyería Hernández-Clientes");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BoletaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void btnSell(ActionEvent event) {

    }

    @FXML
    void eventKey(KeyEvent event) {

    }

    void numeros() {

        String c = "";
        String SQL = "select max(Num_Bol) from Boletas";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtNumBol.setText("BolJH0000001");
            } else {
                GenerarIDBoletas gen = new GenerarIDBoletas();
                gen.idBol(c);
                txtNumBol.setText(gen.serie());
            }

        } catch (SQLException ex) {
            System.out.println("no");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bol1 = this;
        numeros();
        txtFecha.setText(getCurrentTimeStamp());
        h1 = new Thread(this);
        h1.start();
    }

    public static String getCurrentTimeStamp() {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            txtFecha.setText(getCurrentTimeStamp());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException d) {

            }
        }
    }
}
