<%-- 
    Document   : index
    Created on : 14-09-2018, 19:42:20
    Author     : Fernando
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.CandidatoDTO"%>
<%@page import="DAO.CandidatoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Votación</title>
    </head>
    <body>
        <%
            ArrayList<CandidatoDTO> listaCandidatos = new CandidatoDAO().readAll();
        %>
        <form name="formVotacion" action="Votar" method="POST">
            <h1>Formulario de Votación</h1>
            <table border="2">
                <tbody>
                    <tr>
                        <td>Nombre y Apellido:</td>
                        <td><input type="text" name="txtNombre" value="" pattern="[A-ZÑa-zñ ]{1,50}" title="Formato 'Nombre Apellido'" required/></td>
                    </tr>
                    <tr>
                        <td>Rut:</td>
                        <td><input type="text" name="txtRut" value="" pattern="\d{7,8}-[\d|kK]{1}" title="Formato '22042308-5'" required/></td>
                    </tr>
                    <tr>
                        <td>Candidato:</td>
                        <td>
                            <select name="ddlCandidato">
                                <% for (CandidatoDTO candidato: listaCandidatos) { %>
                                <option value="<%=candidato.getRut() %>"><%=candidato.getNombre() %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Votar" name="btnVotar" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
