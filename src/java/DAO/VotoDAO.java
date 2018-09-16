package DAO;

import Conexion.Conexion;
import DTO.VotoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class VotoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_INSERT = "INSERT INTO VOTOS VALUES (?, ?)";
    private static final String SQL_READALL = "SELECT * FROM VOTOS";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public boolean create(VotoDTO aux) { 
        try {          
            ps = con.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, aux.getCandidato());
            ps.setString(2, aux.getCiudadano());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public ArrayList<VotoDTO> readAll() {
        ArrayList<VotoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new VotoDTO(rs.getString(1), rs.getString(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(VotoDTO.class.getName()).log(Level.SEVERE, null, ex);
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
