package DAO;

import Conexion.Conexion;
import DTO.CiudadanoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Chata
 */
public class CiudadanoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_INSERT = "INSERT INTO CIUDADANOS VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE CIUDADANOS SET NOMBRE=? WHERE RUT = ?";
    private static final String SQL_READ = "SELECT * FROM CIUDADANOS WHERE RUT = ?";
    private static final String SQL_READALL = "SELECT * FROM CIUDADANOS ORDER BY RUT";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public boolean create(CiudadanoDTO aux) { 
        try {          
            ps = con.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, aux.getRut());
            ps.setString(2, aux.getNombre());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean update(CiudadanoDTO aux) {
        try {           
            ps = con.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, aux.getNombre());
            ps.setString(2, aux.getRut());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public CiudadanoDTO read(Object key) {
        CiudadanoDTO permiso = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                permiso = new CiudadanoDTO(rs.getString(1), rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permiso;
    }
    
    public ArrayList<CiudadanoDTO> readAll() {
        ArrayList<CiudadanoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new CiudadanoDTO(rs.getString(1), rs.getString(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
}
