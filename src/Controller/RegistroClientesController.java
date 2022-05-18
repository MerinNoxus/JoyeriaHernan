package Controller;

import Alerts.Alerts;
import Conexion.ConexionSQL;
import Model.Clientes;
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
import utilerias.GenerarIDClientes;

public class RegistroClientesController implements Initializable {

    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event;
    PreparedStatement ps;
    @FXML
    private TableView<Clientes> TvClient;

    @FXML
    private TableColumn<Clientes, String> colID;

    @FXML
    private TableColumn<Clientes, String> colNom;

    @FXML
    private TableColumn<Clientes, String> colApe;
    @FXML
    private TableColumn<Clientes, String> colClietrFre;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtApe;

    @FXML
    private JFXButton btnInsert;
    @FXML
    private JFXTextField txtClientFre;

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtNom)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !event.getCharacter().equals(" ")) {
                event.consume();
            } else if (txtNom.getText().length() > 29) {
                event.consume();
            }
        } else if (evt.equals(txtApe)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !event.getCharacter().equals(" ")) {
                event.consume();
            } else if (txtApe.getText().length() > 29) {
                event.consume();
            }
        } else if (evt.equals(txtClientFre)) {
            if (!Character.isLetter(event.getCharacter().charAt(0))) {
                event.consume();
            }
            if (txtClientFre.getText().length() > 1) {
                event.consume();
            }
        }

    }

    @FXML
    void Insert(ActionEvent event) throws SQLException {
        insert();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cc = new ConexionSQL();
        cn = cc.obtener_conexion();
        numeros();
        ShowProducts();
    }

    public ObservableList<Clientes> getClientesList() {

        ObservableList<Clientes> ClientesList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM clientess";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Clientes clien;
            while (rs.next()) {
                clien = new Clientes(rs.getString("CC_clie"), rs.getString("nombre_clie"), rs.getString("apellido_clie"), rs.getString("isclieFrecuent"));
                ClientesList.add(clien);
            }
        } catch (SQLException ex) {
            System.out.println("nada");
        }
        return ClientesList;
    }

    public void ShowProducts() {

        ObservableList<Clientes> list = getClientesList();
        colID.setCellValueFactory(new PropertyValueFactory<>("CC_clie"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre_clie"));
        colApe.setCellValueFactory(new PropertyValueFactory<>("apellido_clie"));
        colClietrFre.setCellValueFactory(new PropertyValueFactory<>("isclieFrecuent"));
        TvClient.getColumns().setAll(colID, colNom, colApe, colClietrFre);
        TvClient.getItems().setAll(list);
    }

    void numeros() {

        String c = "";
        String SQL = "select max(CC_clie) from clientess";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtID.setText("Cli0000001");
            } else {
                GenerarIDClientes gen = new GenerarIDClientes();
                gen.idClientes(c);
                txtID.setText(gen.serie());
            }

        } catch (SQLException ex) {
            System.out.println("no");
        }
    }

    private void insert() throws SQLException {
  if(txtClientFre.getText().isEmpty()){
            txtClientFre.setText("No");
            }
      

            if (txtID.getText().isEmpty()) {
                Alerts vacio = new Alerts();
                vacio.mostrarAlertWarning(event, "Campo ID vacio", "Debes de agregar el campo del ID");
            }    else if(txtNom.getText().isEmpty()){
              Alerts vacio = new Alerts();
                vacio.mostrarAlertWarning(event, "Campo Nombre vacio", "Debes de agregar el campo Nombre");
            }
           else if(txtApe.getText().isEmpty()){
                Alerts vacio = new Alerts();
                vacio.mostrarAlertWarning(event, "Campo Apellidos vacio", "Debes de agregar el campo Apellidos");
            }
        else if(txtNom.getText().isEmpty() && txtApe.getText().isEmpty()){
                Alerts vacio = new Alerts();
                vacio.mostrarAlertWarning(event, "Campos  vacios", "Debes de agregar datosm a los campos Nombre y Apellidos");
          }else{
              String insertSql = "INSERT INTO clientess (CC_clie, nombre_clie, apellido_clie, isclieFrecuent) VALUES (?,?,?,?)";
        try {
            ps = cn.prepareCall(insertSql);
            ps.setString(1, txtID.getText());
            ps.setString(2, txtNom.getText());
            ps.setString(3, txtApe.getText());
            ps.setString(4, txtClientFre.getText());
               ps.executeUpdate();
            ShowProducts();
            txtID.setText("");
            numeros();
            txtNom.setText("");
            txtApe.setText("");
            txtClientFre.setText("");
        } catch (Exception w) {
        }
        }
         
txtClientFre.setText("");        
    }
}
