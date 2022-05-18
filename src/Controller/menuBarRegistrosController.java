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

public class menuBarRegistrosController implements Initializable {

    @FXML
    private BorderPane borderP;
    @FXML
    private Button btnRegistroClientes;
    @FXML
    private Button btnRegistroProductos;

    @FXML
    void getProdcutos(MouseEvent event) {
        Loadpage("/view/RegistrosProductos");
    }

    @FXML
    void getClientes(MouseEvent event) {
        Loadpage("/view/RegistroClientes");
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
        borderP.setCenter(root);
    }

}
