<%-- 
    Document   : CreateUser
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%
    Object usbid = session.getAttribute("usbid");
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
        <link rel="stylesheet" type="text/css" href="css/CreateUser.css">
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
                                        <html:link action="/perfil">Perfil</html:link> |
                                        <html:link href="VistaAdministrador.jsp">
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
                    <h1>Registro de Decanatos</h1>
                    <div id="content">

                        <html:form action="/registrarDecanato">
                            <div id="welcome">
                                <table border="0">
                                    <tbody>
                                        
                                        <tr>
                                            <td colspan="2">
                                                <bean:write name="Decanato" property="errorCodigo" filter="false"/>
                                                &nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <bean:write name="Decanato" property="errorCodigoFormato" filter="false"/>
                                                &nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <bean:write name="Decanato" property="errorNombre" filter="false"/>
                                                &nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <bean:write name="Decanato" property="errorNombreFormato" filter="false"/>
                                                &nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Codigo:</td>
                                            <td><html:text property="codigo" /><span style="color: red"> *</span></td>
                                        </tr>
                                        <tr>
                                            <td>Nombre</td>
                                            <td><html:text property="nombre" /><span style="color: red"> *</span></td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <html:submit value="Registrar" /></td>
                                            <td>
                                                <html:reset value="Limpiar" />
                                            </td>
                                        </tr>                                         
                                    </tbody>
                                </table>
                                <p style="text-align: right">Nota:<span style="color: red"> * </span>Campos obligatorios.</p>
                            </div>
                        </html:form>


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

