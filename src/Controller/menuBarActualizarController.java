package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class menuBarActualizarController implements Initializable {

    @FXML
    private StackPane satck;
    @FXML
    private BorderPane border;
    @FXML
    private Button btnOro;
    @FXML
    private Button btnPlata;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnTodos;
 
    @FXML
    void getOro(MouseEvent event) {
        Loadpage("/view/UpdatePrecioOro");
    }

    @FXML
    void getClient(MouseEvent event) {
        Loadpage("/view/UpdateClientes");
    }

    @FXML
    void getTodo(MouseEvent event) {
        Loadpage("/view/updateProductos");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void Loadpage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuBienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border.setCenter(root);
    }
}
