
package Controller;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Title_barController implements Initializable {
   // double x=0,y=0;
        
    
  @FXML
    void close(MouseEvent event) {
     Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();                          //Me cierra la ventana
    }
    
    @FXML
    void max(MouseEvent event) {
        
      
     Node source = (Node) event.getSource();     
        Stage stage = (Stage) source.getScene().getWindow();    
        stage.setFullScreenExitHint("Presione [ESC] para salir de Pantalla Completa");
        stage.setFullScreen(true);
    }

    @FXML
    void min(MouseEvent event) {
 Node source = (Node) event.getSource();     
        Stage stage = (Stage) source.getScene().getWindow();    
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    
    
}
