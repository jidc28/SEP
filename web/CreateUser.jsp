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
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <div id="body-content">

            <div >
                <div >
                    <img id="banner" src="imagenes/logo.jpg" alt="Inicio">
                </div>
            </div>
            
            <div id="sidebarL">
                <div class="glossymenu" style="width: 190px">
                    <a style="border-bottom: none;"/>
                    <a class="menuitem" href="noimplementado.jsp">
                        Perfil
                    </a>
                    <a class="menuitem" href="VistaAdministrador.jsp">
                        Inicio
                    </a>
                    <a class="menuitem" href="noimplementado.jsp">
                        Contáctenos
                    </a>
                    <a class="menuitem" href="noimplementado.jsp">
                        Ayuda
                    </a>
                    <a class="menuitem" href="cerrarSesion.do" onclick="return confirm('¿Está seguro que desea cerrar sesión?')" >
                        Cerrar Sesión
                    </a>
                    
                </div>
            </div>
            
            <div id="sidebarR">
                <a href="http://www.usb.ve/">
                    <img width="150" height="50" src="imagenes/somosusb.gif"/>
                </a>
            </div>

            <div id="cuerpo-principal">

                <div id="contenido-der">
                    <h1>Registro de usuario</h1>
                    <div id="content">

                        <html:form action="/createUser">
                            <div id="welcome">
                                <table border="0">
                                    <tbody>


                                        <tr>
                                            <td>USBID:</td>
                                            <td><html:text property="usbid" /><span style="color: red"> *</span></td>
                                        </tr>
                                            <tr>
                                                <td style="color: red">
                                                    <html:errors property="usbid" /> 
                                                </td>
                                            </tr>
                                        <tr>
                                            <td>Contraseña:</td>
                                            <td><html:password property="contrasena1" /><span style="color: red"> *</span></td>
                                        </tr>
                                        <tr>
                                            <td>Repetir Contraseña:</td>
                                            <td><html:password property="contrasena2" /><span style="color: red"> *</span></td>
                                        </tr>
                                        <tr>
                                           <td style="color: red">
                                           <html:errors property="difierePass" /> 
                                           </td>
                                        </tr>
                                        <tr>
                                           <td style="color: red">
                                           <html:errors property="contrasena" /> 
                                           </td>
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

