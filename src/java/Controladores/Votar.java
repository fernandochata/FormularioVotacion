
package Controladores;

import DAO.CiudadanoDAO;
import DAO.VotoDAO;
import DTO.CiudadanoDTO;
import DTO.VotoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class Votar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ciudadano = request.getParameter("txtRut");
        String nombre = request.getParameter("txtNombre");
        String candidato = request.getParameter("ddlCandidato");
        
        // VALIDAR FORMATO RUT
        
        // VALIDAR SI RUT YA POSEE VOTO
        if(!existeVoto(ciudadano)){
            // CREAR CIUDADANO
            if(new CiudadanoDAO().create(new CiudadanoDTO(ciudadano, nombre))){
                VotoDTO voto = new VotoDTO(candidato, ciudadano);
                // CREAR VOTO
                if(new VotoDAO().create(voto)){
                    String mensaje = "Voto registrado correctamente.";
                    request.getSession().setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                }else{
                    String mensaje = "Error al momento de ingresar su voto.";
                    request.getSession().setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                }
            }else{
                String mensaje = "Error al momento de crear ciudadano.";
                request.getSession().setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            }
        }else{
            String mensaje = "Rut ya emitió voto.";
            request.getSession().setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("resultado.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private boolean existeVoto(String rut){
        boolean aux = false;
        ArrayList<VotoDTO> listadoVotos = new VotoDAO().readAll();
        for (VotoDTO voto: listadoVotos) {
            if(voto.getCiudadano().equals(rut)){
                aux = true;
            }
        }
        return aux;
    }
    
    private boolean verificarRut(String rut){
        boolean respuesta = false;
        return respuesta;
    }
    
    private String getDV (int rut) {
        int digito, suma, resto, resultado, factor;
        for(factor = 2, suma = 0; rut > 0; factor++){
            digito = rut % 10;
            rut /= 10;
            suma += digito * factor;
            if(factor >= 7) factor = 1;
        } 
        resto = suma % 11;
        resultado = 11 - resto;
        String dv = "";
        switch (resultado) {
            case  0: dv = "0"; break;
            case  1: dv = "1"; break;
            case  2: dv = "2"; break;
            case  3: dv = "3"; break;
            case  4: dv = "4"; break;
            case  5: dv = "5"; break;
            case  6: dv = "6"; break;
            case  7: dv = "7"; break;
            case  8: dv = "8"; break;
            case  9: dv = "9"; break;
            case 10: dv = "K";   break;
        }
        return dv;
    }
}
