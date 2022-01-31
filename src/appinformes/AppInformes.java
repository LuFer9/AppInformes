/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 *
 * @author Luis
 */
public class AppInformes extends Application {
    
    public static Connection conexion = null;
    private AnchorPane root;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        //establecemos conexion con la BD
        conectaBD();
        
        //Cargamos la escena
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/principalView.fxml"));
        //asignamos la vista a un pane
        root = fxmlLoader.load();
        
        //creamos la scena
        Scene scene = new Scene(root);
        
        //Asignamos la scena
        primaryStage.setTitle("AppInformes");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void conectaBD(){
        
        //Establecemos conexion con la base de datos
        //base de datos existente en este mismo proyecto es un base de datos HSQLDB EMDEBBED para poder ejecutar el programa sin necesidad de un servicio
        //pero tambien se puede usar desde un servicio tal y como esta puesto, siempre que la BD tenga el mismo nombre y este en el puerto por defecto (9001)
        String baseDatos = "jdbc:hsqldb:hsql://localhost/appinformesbd";
        String usuario = "SA";
        String contrasenia = "";
        
        try{
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conexion = DriverManager.getConnection(baseDatos, usuario, contrasenia);
           
        }
        catch(ClassNotFoundException cnfe){
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        }
        catch(SQLException sqle){
            System.err.println("No se pudo conectar a BD");
            System.exit(1);
        }
         catch (java.lang.InstantiationException sqlex){
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
        catch (Exception ex){
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
   
    }
    
}
