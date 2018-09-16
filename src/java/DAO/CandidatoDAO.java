package DAO;

import Conexion.Conexion;
import DTO.CandidatoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandidatoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_INSERT = "INSERT INTO CANDIDATOS VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE CANDIDATOS SET NOMBRE=? WHERE RUT = ?";
    private static final String SQL_READ = "SELECT * FROM CANDIDATOS WHERE RUT = ?";
    private static final String SQL_READALL = "SELECT * FROM CANDIDATOS";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public boolean create(CandidatoDTO aux) { 
        try {          
            ps = con.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, aux.getRut());
            ps.setString(2, aux.getNombre());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean update(CandidatoDTO aux) {
        try {           
            ps = con.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, aux.getNombre());
            ps.setString(2, aux.getRut());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public CandidatoDTO read(Object key) {
        CandidatoDTO permiso = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                permiso = new CandidatoDTO(rs.getString(1), rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permiso;
    }
    
    public ArrayList<CandidatoDTO> readAll() {
        ArrayList<CandidatoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new CandidatoDTO(rs.getString(1), rs.getString(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
}
