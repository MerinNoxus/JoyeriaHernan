
package Model;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    private double x0ffset=0,y0ffset=0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginReal.fxml"));
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
       stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
              x0ffset=event.getSceneX();
              y0ffset=event.getSceneY();
            }
        });
        Scene scene = new Scene(root);
 stage.getIcons().add( new Image("/Imagenes/icono.png"));
 




        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
             stage.setX(event.getScreenX()-x0ffset);
              stage.setY(event.getScreenY()-y0ffset);
            }
            
            
        });
        
        
        stage.setTitle("Joyería Hernández");


        stage.setScene(scene);
        stage.show();
       scene.setFill(Color.TRANSPARENT);
        
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
