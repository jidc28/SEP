<%-- 
    Document   : CreateUser
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%@page import="com.myapp.struts.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%
    Object codigo = session.getAttribute("codigo");
    Object status = session.getAttribute("status");
    if (codigo != "") {%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/ShowUser.css">
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <div id="contenedor">

            <div id="encabezado">
                <a title="Inicio" rel="home">
                    <img src="imagenes/logo.jpg" alt="Inicio">
                </a>
            </div>


            <div id="encabezado">
                <div id="menu-principal">          
                    <ul id="menu">
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        Bienvenidos, administrador.
                                        <html:link action="/perfil">Perfil</html:link> |
                                        <html:link href="/sistema2/VistaAdministrador.jsp">
                                            Inicio
                                        </html:link> |
                                        <html:link action="/contactenos">
                                            Contáctenos
                                        </html:link> |
                                        <html:link action="/ayuda">
                                            Ayuda
                                        </html:link> |
                                        <html:link action="/cerrarSesion" onclick="return confirm('¿Está seguro que desea cerrar sesión?')">
                                            Cerrar Sesión
                                        </html:link> 
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </ul>
                </div>
            </div>

            <div id="cuerpo-principal">

                <div id="contenido-der">
                    <h1 style="background-color: cornflowerblue;width: 200px;margin-left: auto; margin-right: auto">Cambiar status de decanato</h1>
                    <div id="content">

                        <div style="width: 404px;margin-left: auto; margin-right: auto;">

                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Codigo</h1>	
                                        </td>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Status de Decanato</h1>	
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <p style="color: brown"> <%=codigo.toString()%></p>	
                                        </td>
                                        <td>
                                            <html:form  action="/cambiarStatusDecanatoA">
                                                <html:hidden property="codigo" value="<%=codigo.toString()%>"/>

                                                <html:select property="status">
                                                    <html:option value="visible">Visible</html:option>
                                                    <html:option value="oculta">Oculta</html:option>                                                  
                                                </html:select>

                                                <html:submit value="Cambiar Status"/>
                                            </html:form>	
                                        </td>

                                    </tr>

                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

        </div>

    </body>
</html>

<%} else {%>
<html>

    <title> hello</title>
</html>
<% }%>

