package Controller;

import Alerts.Alerts;
import Conexion.ConexionSQL;
import Model.Productos;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DeleteProductosController implements Initializable {

    ConexionSQL cc = new ConexionSQL();
    Connection cn = cc.obtener_conexion();
    ActionEvent event;
    PreparedStatement ps;
    @FXML
    private TableView<Productos> TvProduc;

    @FXML
    private TableColumn<Productos, String> colID;

    @FXML
    private TableColumn<Productos, String> colDes;

    @FXML
    private TableColumn<Productos, Integer> colStock;

    @FXML
    private TableColumn<Productos, String> colCons;

    @FXML
    private TableColumn<Productos, Double> colPrecio;

    @FXML
    private TableColumn<Productos, String> colKila;

    @FXML
    private JFXTextField txtID1;

    @FXML
    private JFXTextField txtDes;

    @FXML
    private JFXTextField txtStck;

    @FXML
    private JFXTextField txtCons;

    @FXML
    private JFXTextField txtPrecio;

    @FXML
    private JFXTextField txtKila;
    @FXML
    private JFXTextField txtsearch;

    @FXML
    void Delete(ActionEvent event) {

        Icon icono1 = new ImageIcon(getClass().getResource("/Imagenes/icons8_warning_shield_48px_1.png"));
        int res = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deaseas eliminar?");

        if (res == 0) {
            myIndex = TvProduc.getSelectionModel().getSelectedIndex();
            id = TvProduc.getItems().get(myIndex).getCP_inven();

            try {
                ps = cn.prepareStatement("delete from inventarios where CP_inven = ? ");
                ps.setString(1, id);
                ps.executeUpdate();
                Alerts no = new Alerts();
                no.mostrarAlertInfo(event, "Recargue", "Recargue la ventana de nuevo para efectuar los cambios");
                ShowProducts();
            } catch (SQLException ex) {
                Alerts no = new Alerts();
                no.mostrarAlertError(event, "Error al eliminar", "El campo no se ha podido eliminar");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowProducts();
    }

    public ObservableList<Productos> getProductosList() {

        ObservableList<Productos> productosList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM inventarios";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Productos produc;
            while (rs.next()) {
                produc = new Productos(rs.getString("CP_inven"), rs.getString("Descripcion_inven"), rs.getInt("cantidad_inven"), rs.getString("tipoMaterial_inven"), rs.getInt("precio_inven"), rs.getString("kila_inven"));
                productosList.add(produc);
            }
        } catch (SQLException ex) {
            System.out.println("nada");
        }
        return productosList;
    }

    public void ShowProducts() {

        ObservableList<Productos> list = getProductosList();
        colID.setCellValueFactory(new PropertyValueFactory<Productos, String>("CP_inven"));
        colDes.setCellValueFactory(new PropertyValueFactory<Productos, String>("Descripcion_inven"));
        colStock.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("cantidad_inven"));
        colCons.setCellValueFactory(new PropertyValueFactory<Productos, String>("tipoMaterial_inven"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precio_inven"));
        colKila.setCellValueFactory(new PropertyValueFactory<Productos, String>("kila_inven"));
        TvProduc.getColumns().setAll(colID, colDes, colStock, colCons, colPrecio, colKila);
        TvProduc.getItems().setAll(list);

        TvProduc.setRowFactory(tv -> {
            TableRow<Productos> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = TvProduc.getSelectionModel().getSelectedIndex();
                    id = TvProduc.getItems().get(myIndex).getCP_inven();
                    txtID1.setText(id);
                    txtDes.setText(TvProduc.getItems().get(myIndex).getDescripcion_inven());
                    txtStck.setText(TvProduc.getItems().get(myIndex).getCantidad_inven() + "");
                    txtCons.setText(TvProduc.getItems().get(myIndex).getTipoMaterial_inven());
                    txtPrecio.setText(TvProduc.getItems().get(myIndex).getPrecio_inven() + "");
                    txtKila.setText(TvProduc.getItems().get(myIndex).getKila_inven());

                }
            });
            return myRow;
        });
        FilteredList<Productos> filterdata = new FilteredList<>(list, b -> true);

        txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterdata.setPredicate(registroProductos -> {
                if (newValue.isEmpty() || newValue == "" || newValue == null) {
                    return true;

                }
                String ser = newValue.toLowerCase();
                if (registroProductos.getDescripcion_inven().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else if (registroProductos.getCP_inven().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else if (registroProductos.getTipoMaterial_inven().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else if (registroProductos.getKila_inven().toLowerCase().indexOf(ser) > -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        SortedList<Productos> sorted = new SortedList<>(filterdata);
        sorted.comparatorProperty().bind(TvProduc.comparatorProperty());
        TvProduc.setItems(sorted);
    }

    int myIndex;
    String id;

}
