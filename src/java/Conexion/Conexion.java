package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class Conexion {
    public static Conexion instancia;
    private Connection cnn;
    
    private Conexion(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            cnn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "VOTACION", "votacion");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized static Conexion iniciarConexion(){
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getCnn() {
        return cnn;
    }
    
    public void cerrarConexion(){
        instancia = null;
    }
}