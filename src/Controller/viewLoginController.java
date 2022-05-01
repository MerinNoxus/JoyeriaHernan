package Controller;

import Alerts.Alerts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Conexion.ConexionSQL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class viewLoginController implements Initializable {

    ActionEvent event;
    String title, context;
    private Connection con;
    @FXML
    private JFXButton close, btnacceder;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtcontra;
  int i = 0;
    @FXML
    void cerrar(ActionEvent event) {
        cerrarVentana(event);
    }

    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();                          //Me cierra la ventana
    }

    @FXML
    void Accede(ActionEvent event) {

          AccederSQLLogin();
    }

    public void AccederSQLLogin() {
      
          ConexionSQL cc = new ConexionSQL();
            Connection cn = cc.obtener_conexion();
        boolean band = false;
        String usuario1 = txtUsuario.getText().trim();
        String pass1 = txtcontra.getText();
        String usu, pass;

        if (usuario1.isEmpty() && pass1.isEmpty() && !band) {
            Alerts alertas = new Alerts();
            title = "Ningún Campo Llenado";
            context = "Desbes de ingresar tu Usuario y Contraseña";
            alertas.mostrarAlertError(event, title, context);
            band = true;

        } else if (usuario1.isEmpty() && !band) {
            Alerts vacio = new Alerts();
            title = "Error de Usuario";
            context = "No puedes dejar el campo Usuario Vacio";
            vacio.mostrarAlertError(event, title, context);

        } else if (pass1.isEmpty() && !band) {
            Alerts vacio2 = new Alerts();
            title = "Error de Contraseña";
            context = "Parece que haz dejado la contraseña vacia";
            vacio2.mostrarAlertError(event, title, context);
            
            usuario1 = txtUsuario.getText().trim();
            pass1 = txtcontra.getText();
            
            //  String sql = "SELECT * FROM usuarios1 WHERE usuario='" + usuario1 + "' AND pass='" + pass1 + "'";
        }else{
            try {
                String sql = "SELECT * FROM usuarios1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                //int ro=rs.getRow();
                usu = rs.getString("usuario");
                pass = rs.getString("pass");

                if (usu.equals(usuario1) && pass.equals(pass1)) {
                    Alerts igual = new Alerts();
                    igual.MostrarConfirmacion(event);

                } else if (i == 3) {
                    Alerts error = new Alerts();
                    title = "Error";
                    context = "Haz excedido el número de intetos, regresa más tarde :D";
                    error.mostrarAlertError(event, title, context);

                } else {
                    Alerts again= new Alerts();
                     title = "El Usuario y Contraseña no concuerdan ";
                    context = "Verifica bien el nombre de usuario y contraseña" + "\n quedan "+(3-i)+" intentos";
                    again.mostrarAlertError(event, title, context); 
                    i++;
                }
            
            } catch (SQLException ex) {
            
                System.out.println(ex.toString());
            }
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConexionSQL cc = new ConexionSQL();
       Connection cn = cc.obtener_conexion();
    }

}
