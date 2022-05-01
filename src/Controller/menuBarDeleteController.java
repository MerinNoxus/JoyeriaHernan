
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
import javafx.scene.layout.BorderPane;

public class menuBarDeleteController implements Initializable
{
    @FXML
    private BorderPane boerderP;
   
    
       @FXML
    void getProductosEliminados(MouseEvent event) {
           Loadpage("/view/DeleteProductos");
    }
    
 private void Loadpage(String page){
Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuBienController.class.getName()).log(Level.SEVERE, null, ex);
        }
boerderP.setCenter(root);
}
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       
    }    
}