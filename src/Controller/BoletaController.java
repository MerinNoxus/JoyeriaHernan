package Controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class BoletaController implements Initializable,Runnable {
Thread h1;
    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtClientFre;

    @FXML
    private JFXTextField txtID1;

    @FXML
    private TableView<?> TVDetalle;

    @FXML
    private TableColumn<?, ?> colCodPro;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableColumn<?, ?> colPrecioU;

    @FXML
    private TableColumn<?, ?> colPrecioVenta;

    @FXML
    private JFXTextField txtApe;

    @FXML
    private JFXTextField txtNumBol;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtFecha;

    @FXML
    void btnAddProducts(MouseEvent event) {

    }

    @FXML
    void btnCalculo(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnSearchClient(MouseEvent event) {

    }

    @FXML
    void btnSell(ActionEvent event) {

    }

    @FXML
    void eventKey(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
txtFecha.setText(getCurrentTimeStamp());
h1=new Thread(this);
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
       Thread ct= Thread.currentThread();
       while(ct==h1){
        txtFecha.setText(getCurrentTimeStamp());
        try{
            Thread.sleep(1000);
        }catch(InterruptedException d){
            
        }
       }
    }
}
