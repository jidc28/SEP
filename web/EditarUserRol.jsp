<%-- 
    Document   : EditarUserRol
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%@page import="com.myapp.struts.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%
    Object usbid = session.getAttribute("usbid");
    Object tipousuario = session.getAttribute("tipousuario");
    Object departamento = session.getAttribute("departamento");
    if (usbid != "") {%>
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
                                        Bienvenido, administrador
                                        <html:link action="/noImplementado">Perfil</html:link> |
                                        <html:link href="VistaAdministrador.jsp">
                                            Inicio
                                        </html:link> |
                                        <html:link action="/noImplementado">
                                            Contáctenos
                                        </html:link> |
                                        <html:link action="/noImplementado">
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
                    <h1 style="background-color: cornflowerblue;width: 200px;margin-left: auto; margin-right: auto">Cambiar rol de usuario</h1>
                    <div id="content">

                        <div style="width: 604px;margin-left: auto; margin-right: auto;">

                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Usbid</h1>	
                                        </td>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Departamento</h1>	
                                        </td>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Tipo de Usuario</h1>	
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <p style="color: brown"> <%=usbid.toString()%></p>	
                                        </td>
                                        <td>
                                            <html:form  action="/cambiarRolUser">
                                                <html:hidden property="usbid" value="<%=usbid.toString()%>"/>

                                                <html:select property="departamento">
                                                    <html:option value="dpto1">Dpto1</html:option>
                                                    <html:option value="dpto2">Dpto2</html:option>
                                                    <html:option value="dpto3">Dpto3</html:option>
                                                    <html:option value="dpto4">Dpto4</html:option>
                                                    <html:option value="dpto5">Dpto5</html:option>                                                  
                                                </html:select>
                                        </td>
                                        <td>
                                                <html:select property="tipousuario">
                                                    <html:option value="profesor">Profesor</html:option>
                                                    <html:option value="administrador">Administrador</html:option>
                                                    <html:option value="departamento">Departamento</html:option>
                                                    <html:option value="coordinacion">Coordinación</html:option>
                                                    <html:option value="decanato">Decanato</html:option>                                                  
                                                </html:select>
                                        </td>
                                        <td>
                                                <html:submit value="Cambiar Rol"/>
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

