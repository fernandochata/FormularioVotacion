<%-- 
    Document   : resultado
    Created on : 14-09-2018, 19:42:34
    Author     : Fernando
--%>

<%@page import="DAO.ConteoDAO"%>
<%@page import="DTO.ConteoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.CandidatoDTO"%>
<%@page import="DAO.CandidatoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            String mensaje = (String)request.getSession().getAttribute("mensaje");
            ArrayList<ConteoDTO> listado = new ConteoDAO().readAll();
        %>
        
        <h1>Resultado de Votación</h1>
        <%=mensaje %>
        <table border="1">
            <thead>
                <tr>
                    <th>Candidato</th>
                    <th>Votos</th>
                </tr>
            </thead>
            <tbody>
                <% for (ConteoDTO conteo: listado) { %>
                    <tr>
                        <td><%=conteo.getCandidato() %></td>
                        <td><%=conteo.getConteo() %> </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <a href="index.jsp">Regresar</a>
    </body>
</html>
