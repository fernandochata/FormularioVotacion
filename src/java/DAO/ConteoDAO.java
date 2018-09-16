package DAO;

import Conexion.Conexion;
import DTO.ConteoDTO;
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
public class ConteoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_READALL = "SELECT CANDIDATOS.NOMBRE, COUNT(VOTOS.CANDIDATO) AS VOTOS FROM VOTOS INNER JOIN CANDIDATOS ON CANDIDATOS.RUT = VOTOS.CANDIDATO GROUP BY CANDIDATOS.NOMBRE, VOTOS.CANDIDATO ";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public ArrayList<ConteoDTO> readAll() {
       
        ArrayList<ConteoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new ConteoDTO(rs.getString(1), rs.getInt(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConteoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
