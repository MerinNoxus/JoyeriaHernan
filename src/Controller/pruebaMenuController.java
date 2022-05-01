package Controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class pruebaMenuController extends Application {

    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane1.setVisible(false);
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), pane1);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();

        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.5), pane2);
        trans.setByX(-680);
        trans.play();

        menu.setOnMouseClicked(event -> {

            pane1.setVisible(true);

            FadeTransition fade1 = new FadeTransition(Duration.seconds(0.5), pane1);
            fade1.setFromValue(1);
            fade1.setToValue(0.15);
            fade1.play();

            TranslateTransition trans1 = new TranslateTransition(Duration.seconds(0.5), pane2);
            trans1.setByX(+680);
            trans1.play();
        });
        
        
        
        pane1.setOnMouseClicked(event -> {
            FadeTransition fade1 = new FadeTransition(Duration.seconds(0.5), pane1);
            fade1.setFromValue(1);
            fade1.setToValue(0.15);
            fade1.play();
            
            fade1.setOnFinished(event1 ->{
            pane1.setVisible(true);
            
            });
            TranslateTransition trans1 = new TranslateTransition(Duration.seconds(0.5), pane2);
            trans1.setByX(-680);
            trans1.play();

        });

    }

}
