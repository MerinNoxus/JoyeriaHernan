
package Controller;

import Alerts.Alerts;
import Model.Clientes;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import Conexion.ConexionSQL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpdateClientesController implements Initializable {
      ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
      int myIndex;
    String id;
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
    private JFXTextField txtsearch;
    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtApe;

    @FXML
    private JFXTextField txtClientFre;

    @FXML
    void Insert(ActionEvent event) {
String nombre,apellidos,clienteFre;
 myIndex = TvClient.getSelectionModel().getSelectedIndex();
    id = TvClient.getItems().get(myIndex).getCC_clie();
    
    nombre=txtNom.getText();
    apellidos=txtApe.getText();
    clienteFre=txtClientFre.getText();
        try
        {
            ps = cn.prepareStatement("UPDATE clientess SET  nombre_clie = ?,apellido_clie = ? ,isclieFrecuent=? WHERE CC_clie =?");
            ps.setString(1,nombre);
            ps.setString(2, apellidos);
            ps.setString(3, clienteFre);
             ps.setString(4,id);
            ps.executeUpdate();
            ShowClientes();
            txtsearch.setText("");
        }catch(Exception es){
        Alerts no=new Alerts();
        no.mostrarAlertInfo(event, "Recargue","Recargue la ventana de nuevo para efectuar los cambios");
       
        }
    }

    @FXML
    void eventKey(KeyEvent event) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
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
                   txtID.setText(id);
                    txtNom.setText(TvClient.getItems().get(myIndex).getNombre_clie());
                    txtApe.setText(TvClient.getItems().get(myIndex).getApellido_clie()+ "");
                    txtClientFre.setText(TvClient.getItems().get(myIndex).getIsclieFrecuent());
                
                    
                }
            });
            return myRow;
        });
           FilteredList<Clientes> filterdata = new FilteredList<>(list, b -> true);
           txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filterdata.setPredicate(clients -> {
                    if(newValue.isEmpty()||newValue==""||newValue==null){
                    return true;
                    
                    }
                    String ser=newValue.toLowerCase();
                    if(clients.getCC_clie().toLowerCase().indexOf(ser)>-1){
                    return true;
                    }else if(clients.getApellido_clie().toLowerCase().indexOf(ser)>-1){
                    return true;
                    }else if(clients.getIsclieFrecuent().toLowerCase().indexOf(ser)>-1){
                    return true;
                    }else if(clients.getNombre_clie().toLowerCase().indexOf(ser)>-1){
                    return true;
                    }else
                        
                    
                    return false;
                    
                });

            });
           SortedList<Clientes>sorted=new SortedList<>(filterdata);
            sorted.comparatorProperty().bind(TvClient.comparatorProperty());
             TvClient.setItems(sorted);
    }
}
