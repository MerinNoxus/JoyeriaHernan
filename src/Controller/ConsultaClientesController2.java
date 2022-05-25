package Controller;

import Conexion.ConexionSQL;
import Model.Clientes;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultaClientesController2 implements Initializable {

    BoletaController bolController;
    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    int myIndex;
    String id;
    String idC;
    String Nom;
    String apes;
    String clitnf;

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
    private JFXTextField txtsearch;
public void getInfoClient(BoletaController bolController){
    System.out.println("listo ya lo llame");
    this.bolController=bolController;
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowClientes();
    }

    public ObservableList<Clientes> getClientesList() {

        ObservableList<Clientes> ClientesList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM clientess";
            st = cn.createStatement();
            rs = st.executeQuery(query);
            Clientes clie;
            while (rs.next()) {
                clie = new Clientes(rs.getString("CC_clie"), rs.getString("nombre_clie"), rs.getString("apellido_clie"), rs.getString("isclieFrecuent"));
                ClientesList.add(clie);
            }
        } catch (SQLException ex) {
            System.out.println("nada");
        }
        return ClientesList;
    }

    public void ShowClientes() {

        ObservableList<Clientes> list = getClientesList();
        colID.setCellValueFactory(new PropertyValueFactory<>("CC_clie"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre_clie"));
        colApe.setCellValueFactory(new PropertyValueFactory<>("apellido_clie"));
        colClietrFre.setCellValueFactory(new PropertyValueFactory<>("isclieFrecuent"));
        TvClient.getColumns().setAll(colID, colNom, colApe, colClietrFre);
        TvClient.getItems().setAll(list);
        
        TvClient.setRowFactory(tv -> {
            TableRow<Clientes> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = TvClient.getSelectionModel().getSelectedIndex();
                    id = TvClient.getItems().get(myIndex).getCC_clie();
                    idC = id;
                    Nom = TvClient.getItems().get(myIndex).getNombre_clie();
                    clitnf = TvClient.getItems().get(myIndex).getIsclieFrecuent();
                    apes = TvClient.getItems().get(myIndex).getApellido_clie() + "";
              
                bolController.getInfoClient( id, Nom, clitnf, apes);
                }

            });
            return myRow;

        });
         
        FilteredList<Clientes> filterdata = new FilteredList<>(list, b -> true);
        txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterdata.setPredicate(clients -> {
                if (newValue.isEmpty() || newValue == "" || newValue == null) {
                    return true;

                }
                String ser = newValue.toLowerCase();
                if (clients.getCC_clie().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else if (clients.getApellido_clie().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else if (clients.getIsclieFrecuent().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else if (clients.getNombre_clie().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        SortedList<Clientes> sorted = new SortedList<>(filterdata);
        sorted.comparatorProperty().bind(TvClient.comparatorProperty());
        TvClient.setItems(sorted);
    }
}
