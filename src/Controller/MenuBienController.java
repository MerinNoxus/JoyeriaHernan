
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class MenuBienController implements Initializable {
@FXML
private BorderPane BP;
@FXML
    private AnchorPane AP;
      @FXML
    void consultas(MouseEvent event) {
          Loadpage("/view/menuBarConsultas");
    }

    @FXML
    void contacs(MouseEvent event) {

    }

    @FXML
    void home(MouseEvent event) {
        BP.setCenter(AP);
      
    }

    @FXML
    void movet(MouseEvent event) {
 Loadpage("/view/menuBarMovimientos");
    }

    @FXML
    void registros(MouseEvent event) {
        Loadpage("/view/menuBarRegistros");
    }

    @FXML
    void reports(MouseEvent event) {
 Loadpage("/view/menuBarReportes");
    }

    @FXML
    void update(MouseEvent event) {
   Loadpage("/view/menuBarActualizar");
    }
     @FXML
    void delete(MouseEvent event) {
 Loadpage("/view/menuBarDelete");
    }
    
    
    
    
 private void Loadpage(String page){
Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuBienController.class.getName()).log(Level.SEVERE, null, ex);
        }
   BP.setCenter(root);
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
