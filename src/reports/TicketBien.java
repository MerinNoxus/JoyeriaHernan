package reports;

import Controller.BoletaController;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import com.sun.javafx.geom.transform.CanTransformVec3d;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

public class TicketBien {
private String nombre,idcliente,fecha,numbol;
    BoletaController bol;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNumbol(String numbol) {
        this.numbol = numbol;
    }

    public void setBol(BoletaController bol) {
        this.bol = bol;
    }

    public void llegaComp(BoletaController bol) {
        this.bol = bol;
    }

    public void imprimirTicket(int fila, String total, String efectivo, String devolucion) {
        try {
            /*
      for (int i = 0; i < TVDetalle2.getItems().size(); i++) {
                    descripcion_deBol = TVDetalle2.getItems().get(i).getDescripcion_deBol();
                    material_deBol = TVDetalle2.getItems().get(i).getMaterial_deBol();
                    Kilates_deBol = TVDetalle2.getItems().get(i).getKilates_deBol();
                    cantidad_deBol = TVDetalle2.getItems().get(i).getCantidad_deBol();
                    precioUnit_deBol = TVDetalle2.getItems().get(i).getPrecioUnit_deBol();
                    precioVenta_deBol = TVDetalle2.getItems().get(i).getPrecioVenta_deBol();
                    //imprime.setArticulos(descripcion_deBol + " " + cantidad_deBol + " " + precioUnit_deBol + " " + precioVenta_deBol + " " + material_deBol + " " + Kilates_deBol);
                     //.out.println(imprime);
                   
                }
             */
            PrinterMatrix printer = new PrinterMatrix();
            Extenso e = new Extenso();

            e.setNumber(20.30);
            //Definir el tamanho del papel para la impresion de dinamico y 32 columnas
            int filas = fila;
            int tamaño = filas + 40;
            printer.setOutSize(tamaño, 120);

            //Imprimir = 1ra linea de la columa de 1 a 32
            printer.printTextWrap(0, 1, 5, 120, "==============================================================");
            printer.printTextWrap(1, 1, 30, 60, "Joyería Hernández"); //Nombre establecimiento
            printer.printTextWrap(3, 1, 30, 70, " Centro"); //Barrio
            printer.printTextWrap(4, 1, 30, 70, "Calle Reforma #10"); //Direccion
            printer.printTextWrap(5, 1, 30, 70, "4000"); //Codigo Postal
            printer.printTextWrap(6, 1, 9, 41, "Fecha:"+fecha); //Aqui va la fecha de recibo
            //printer.printTextWrap(6, 1, 38, 80, "Hora"); //Aqui va la hora de recibo
            printer.printTextWrap(7, 1, 9, 80, "Numero de boleta: "+numbol); //Numero del recibo - FACTURA O PEDIDO
            printer.printTextWrap(8, 1, 9, 80, "Cliente: "+nombre);//Nombre del Cliente
             printer.printTextWrap(9, 1, 9, 80, "IDCliente: "+idcliente);//Nombre del Cliente
            printer.printTextWrap(10, 1, 5, 120, "——————————–——————————–——————————–——————————–——–———–——–—–———–—");
            printer.printTextWrap(11, 1, 6, 120, "ID         DESCRIPCION            PRECIO UND   CANT  TOTAL");
            printer.printTextWrap(12, 1, 0, 120, " ");
            for (int i = 0; i < bol.TVDetalle2.getItems().size(); i++) {
                String cod = bol.TVDetalle2.getItems().get(i).getCP_deBol();
                String descripcion_deBol = bol.TVDetalle2.getItems().get(i).getDescripcion_deBol();
                double precioUnit_deBol = bol.TVDetalle2.getItems().get(i).getPrecioUnit_deBol();
                String cant = String.valueOf(bol.TVDetalle2.getItems().get(i).getCantidad_deBol());
                String total1 = String.valueOf(bol.TVDetalle2.getItems().get(i).getPrecioVenta_deBol());
                int p = 13 + i; //Fila

                printer.printTextWrap(p, 1, 6, 16, cod);
                printer.printTextWrap(p, 1, 17, 39, descripcion_deBol);
                printer.printTextWrap(p, 1, 40, 48, String.valueOf(precioUnit_deBol));
                  printer.printTextWrap(p, 1, 53, 55, cant);
                    printer.printTextWrap(p, 1, 58, 66, total1);

            }
        
            String tot = printer.alinharADireita(10, total);
            printer.printTextWrap(filas + 18, 1, 5, 80, "Total a pagar: ");
            printer.printTextWrap(filas + 18, 1, 20, 80, "$" + tot);

            String efe = printer.alinharADireita(10, efectivo);
            printer.printTextWrap(filas + 19, 1, 5, 80, "Efectivo : ");
            printer.printTextWrap(filas + 19, 1, 20, 80, "$" + efe);

            String cam = printer.alinharADireita(10, devolucion);
            printer.printTextWrap(filas + 20, 1, 5, 80, "Cambio : ");
            printer.printTextWrap(filas + 20, 1, 20, 80, "$" + cam);

            printer.printTextWrap(filas + 21, 1, 5, 120, "——————————–——————————–——————————–——————————–——–———–——–—–———–—");
            printer.printTextWrap(filas + 22, 1, 26, 80, "!Gracias por su preferencia!");
            printer.printTextWrap(filas + 23, 1, 30, 80, "Joyería Hernández v1.0");
            printer.printTextWrap(filas + 24, 1, 31, 80, "Software a Medida");
            printer.printTextWrap(filas + 25, 1, 25, 80, "Contacto: merinfap11@gmail.com");
            printer.printTextWrap(filas + 26, 1, 5, 120, "==============================================================");

            ///CREAR ARCHIVO EN CARPETA DEL PROYECTO PARA PEDIDOS
            printer.toFile("C:\\reportes\\impresion.txt");
            FileInputStream inputStream = null;

            try {
                inputStream = new FileInputStream("C:\\reportes\\impresion.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
            if (inputStream == null) {
                return;
            }

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc document = new SimpleDoc(inputStream, docFormat, null);
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                try {
                    printJob.print(document, attributeSet);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.err.println("No existen impresoras instaladas");
            }

            inputStream.close();

        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al imprimir " + e);
        }
    }
}
