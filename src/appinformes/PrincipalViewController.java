/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import static appinformes.AppInformes.conexion;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class PrincipalViewController implements Initializable {

    @FXML
    private MenuItem menuItem_ListadoFacturas;
    @FXML
    private MenuItem menuItem_VentasTotales;
    @FXML
    private MenuItem menuItem_FacturaPorCliente;
    @FXML
    private MenuItem menuItem_SubInformeListadoFacturas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void generarInformeListadoFacturas(ActionEvent event) {
        try {
            JasperReport jr =(JasperReport)JRLoader.loadObject(AppInformes.class.getResource("/resources/archivosJasper/AppInformes_ListadoFacturas.jasper"));

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion);
            JasperViewer.viewReport(jp, false);
        } 
        catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void generarInformeVentasTotales(ActionEvent event) {
        try {
            JasperReport jr =(JasperReport)JRLoader.loadObject(AppInformes.class.getResource("/resources/archivosJasper/AppInformes_VentasTotales.jasper"));

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion);
            JasperViewer.viewReport(jp, false);
        } 
        catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }

    @FXML
    private void generarFacturaPorCliente(ActionEvent event) throws IOException {
      
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/facturaPorClienteElegirAddressIDView.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Factura por cliente");
        stage.show();
    }

    @FXML
    private void generarSubInformeListadoFacturas(ActionEvent event) throws JRException, JRException {
        
        JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/resources/archivosJasper/AppInformes_SubInformesListadoFacturas_Principal.jasper"));
        JasperReport jsr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/resources/archivosJasper/AppInformes_SubInformeListadoFacturas_Secundario.jasper"));
        
        //Map de parámetros
        Map parametros = new HashMap();
        parametros.put("SubReportParameter", jsr);
        
        //Ya tenemos los datos para instanciar un objeto JasperPrint que permite ver,
        //imprimir o exportar a otros formatos
        JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros,conexion);
        JasperViewer.viewReport(jp, false);
        
    }
    
}
