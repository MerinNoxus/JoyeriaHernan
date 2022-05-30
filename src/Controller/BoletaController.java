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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import reports.TicketBien;
import utilerias.GenerarIDBoletas;

public class BoletaController implements Initializable, Runnable {

    Thread h1;
    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event;
    PreparedStatement ps;
    @FXML
    private TextField txtEfectivo;
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
    public TableView<DetalleBoletas> TVDetalle2;

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

    BoletaController bol1;
    ObservableList<DetalleBoletas> detalle = FXCollections.observableArrayList();
    private String CP_deBol;
    private String descripcion_deBol;
    private int cantidad_deBol;
    private double precioUnit_deBol;
    private double precioVenta_deBol;
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
        double descuento = 0;
        double resultado = 0;
        double pre;
        int can;
        double subtotal = 0;
        double imp = 0.0;
        double valor;
        String Porcentaje = "^([0-9]{1,2}[%]?||[0-9]{1,4})$";
        String quitarPorcentaje;

        for (int i = 0; i < TVDetalle2.getItems().size(); i++) {
            pre = TVDetalle2.getItems().get(i).getPrecioUnit_deBol();
            can = TVDetalle2.getItems().get(i).getCantidad_deBol();
            CP_deBol = TVDetalle2.getItems().get(i).getCP_deBol();
            descripcion_deBol = TVDetalle2.getItems().get(i).getDescripcion_deBol();
            material_deBol = TVDetalle2.getItems().get(i).getMaterial_deBol();
            Kilates_deBol = TVDetalle2.getItems().get(i).getKilates_deBol();
            imp = pre * can;
            subtotal += imp;
            valor = Math.rint(imp * 100) / 100;
            detalle.set(i, new DetalleBoletas(CP_deBol, descripcion_deBol, can, pre, valor, Kilates_deBol, material_deBol));
            colCodPro.setCellValueFactory(new PropertyValueFactory<>("CP_deBol"));
            colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion_deBol"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("cantidad_deBol"));
            colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta_deBol"));
            colPrecioU.setCellValueFactory(new PropertyValueFactory<>("precioUnit_deBol"));
            colMaterial.setCellValueFactory(new PropertyValueFactory<>("material_deBol"));
            colKilates.setCellValueFactory(new PropertyValueFactory<>("Kilates_deBol"));

            TVDetalle2.setItems(detalle);
        }
        try {
            Pattern pat = Pattern.compile(Porcentaje);
            Matcher mat = pat.matcher(txtDescuento.getText());
            if (txtDescuento.getText().equals("0") || txtDescuento.getText().isEmpty() || txtDescuento.getText().equals("0.0")) {
                txtTotal.setText("" + Math.rint(subtotal * 100) / 100);

            } else if (mat.matches()) {
                quitarPorcentaje = txtDescuento.getText();
                boolean containsPorcen = quitarPorcentaje.endsWith("%");
                if (containsPorcen) {
                    quitarPorcentaje = quitarPorcentaje.replace("%", "");
                    descuento = Double.valueOf(quitarPorcentaje);
                    descuento = descuento / 100;
                    resultado = subtotal - (subtotal * descuento);
                    txtTotal.setText("" + Math.rint(resultado * 100) / 100);
                } else {
                    descuento = Double.valueOf(txtDescuento.getText());
                    resultado = subtotal - descuento;
                    txtTotal.setText("" + Math.rint(resultado * 100) / 100);
                }

            }
        } catch (Exception ex) {
            Alerts error = new Alerts();
            error.mostrarAlertError(event, "Error", "Verifique el campo Descuento e intentelo nuevamente");
        }

    }

    @FXML
    void btnDelete(ActionEvent event
    ) {
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
    void btnSell(ActionEvent event) throws IOException {
        if (txtClientFre.getText().equals("") || txtClientFre.getText().isEmpty()
                && txtID1.getText().equals("") || txtID1.getText().isEmpty()
                && txtNom.getText().equals("") || txtNom.getText().isEmpty()) {
            Alerts nope = new Alerts();
            nope.mostrarAlertWarning(event, "Cliente Error", "Debes de ingresar un cliente");
        } else {
            String cod;
            int can;
            for (int i = 0; i < TVDetalle2.getItems().size(); i++) {
                can = TVDetalle2.getItems().get(i).getCantidad_deBol();
                cod = TVDetalle2.getItems().get(i).getCP_deBol();
                descontarstock(cod, can);
            }
            Boleta();
            detalleboleta();
            txtApe.setText("");
            txtClientFre.setText("");
            txtID1.setText("");
            txtNom.setText("");
            txtTotal.setText("");
            txtEfectivo.setText("");
            txtDescuento.setText("");
        }
        int a = TVDetalle2.getItems().size() - 1;
        int i;
        for (i = a; i >= 0; i--) {
            detalle.remove(i);
        }
        numeros();

    }

    @FXML
    void eventKey(KeyEvent event) {

    }

    void Boleta() {
        String InsertVenta = "INSERT INTO Boletas(Num_Bol,CC_Bol,precioTotal_Bol,fecha_Bol)VALUES(?,?,?,?)";
        try {
            ps = cn.prepareStatement(InsertVenta);
            ps.setString(1, txtNumBol.getText());
            ps.setString(2, txtID1.getText());
            ps.setDouble(3, Double.valueOf(txtTotal.getText()));
            ps.setString(4, txtFecha.getText());
            int n = ps.executeUpdate();
            if (n > 0) {
                double cambio = Double.valueOf(txtEfectivo.getText());
                cambio = Math.rint(cambio - Double.valueOf(txtTotal.getText()));
                Alerts bien = new Alerts();
                bien.mostrarAlertInfo(event, "Venta", "Venta realizada,Cambio:" + cambio);
            TicketBien si=new TicketBien();
           int tam= TVDetalle2.getItems().size();
          si.llegaComp(bol1);
         si.setNumbol(txtNumBol.getText());
         si.setFecha(txtFecha.getText());
         si.setIdcliente(txtID1.getText());
         si.setNombre(txtNom.getText()+" "+txtApe.getText());
          si.imprimirTicket(tam,txtTotal.getText(),txtEfectivo.getText(),String.valueOf(cambio));
          
             
              
            }
        } catch (SQLException ex) {
            Alerts no = new Alerts();
            no.mostrarAlertError(event, "Error Venta", "Venta no realizada");
        }

    }

    void detalleboleta() {
        for (int i = 0; i < TVDetalle2.getItems().size(); i++) {
            String InsertarSQL = "INSERT INTO detalleBoletas(Num_deBol,CP_deBol,descripcion_deBol,cantidad_deBol,precioUnit_deBol,precioVenta_deBol,material_deBol,Kilates_deBol) VALUES (?,?,?,?,?,?,?,?)";
            CP_deBol = TVDetalle2.getItems().get(i).getCP_deBol();
            descripcion_deBol = TVDetalle2.getItems().get(i).getDescripcion_deBol();
            material_deBol = TVDetalle2.getItems().get(i).getMaterial_deBol();
            Kilates_deBol = TVDetalle2.getItems().get(i).getKilates_deBol();
            cantidad_deBol = TVDetalle2.getItems().get(i).getCantidad_deBol();
            precioUnit_deBol = TVDetalle2.getItems().get(i).getPrecioUnit_deBol();
            precioVenta_deBol = TVDetalle2.getItems().get(i).getPrecioVenta_deBol();
            try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);
                pst.setString(1, txtNumBol.getText());
                pst.setString(2, CP_deBol);
                pst.setString(3, descripcion_deBol);
                pst.setInt(4, cantidad_deBol);
                pst.setDouble(5, precioUnit_deBol);
                pst.setDouble(6, precioVenta_deBol);
                pst.setString(7, material_deBol);
                pst.setString(8, Kilates_deBol);
                pst.executeUpdate();

            } catch (SQLException ex) {

            }
        }
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

    void descontarstock(String codi, int can) {

        int cap = 0;
        int desfinal;
        String consul = "SELECT * FROM inventarios WHERE  CP_inven='" + codi + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                cap = rs.getInt("cantidad_inven");
            }

        } catch (Exception e) {
        }
        desfinal = cap - can;
        String modi = "UPDATE inventarios SET cantidad_inven='" + desfinal + "' WHERE CP_inven = '" + codi + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
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
