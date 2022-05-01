
package Alerts;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

public class Alerts {
    @FXML
public void mostrarAlertError(ActionEvent event,String Title,String context) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    //alert.setHeaderText(null);
    alert.setTitle(Title);
    alert.setContentText(context);
    alert.showAndWait();
}
 
@FXML
public  void mostrarAlertInfo(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setTitle("Info");
    alert.setContentText("Informacion sobre la aplicación");
    alert.showAndWait();
}
 
@FXML
public  void mostrarAlertWarning( ActionEvent event,String Title,String context) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    //alert.setHeaderText(null);
    alert.setTitle(Title);
    alert.setContentText(context);
    alert.showAndWait();
}
 
@FXML
public void mostrarAlertConfirmation(ActionEvent event, String Title,String context) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //alert.setHeaderText(null);
    alert.setTitle(Title);
    alert.setContentText(context);
   alert.showAndWait();

}
 
@FXML
public  void mostrarAlertCabecera(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Cabecera");
    alert.setTitle("Info");
    alert.setContentText("Informacion sobre la aplicación");
    alert.showAndWait();
}


public void MostrarConfirmacion(ActionEvent event){
    boolean band=false;

 Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Autorizado Inicio de sesión");
alert.setHeaderText("Que bueno tenerte por aquí, estamos feliz de verte nuevamente");
//alert.setContentText("Choose your option.");
alert.setGraphic(new ImageView(this.getClass().getResource("/Imagenes/pollito-y-polluelo-imagen-animada-0005.gif").toString()));
ButtonType buttonTypeOne = new ButtonType("De acuerdo,gracias");
alert.getButtonTypes().setAll(buttonTypeOne);
Optional result = alert.showAndWait();
if (result.get() == buttonTypeOne){
   
}
}

}
