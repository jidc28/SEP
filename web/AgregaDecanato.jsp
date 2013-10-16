<%-- 
    Document   : AgregarDecanato
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

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

            <h4>Registro de Decanatos</h4>

            <html:form action="/registrarDecanato" method="POST" acceptCharset="ISO-8859-1" enctype="multipart/form-data" onsubmit="return(this)">
                <table border="0">
                    <tbody>
                        <tr>
                            <td style="color: black">Codigo del Decanato</td>
                            <td>
                                <html:text name="Decanato" property="codigo" maxlength="10" value="" errorStyleClass="error"
                                           errorKey="org.apache.struts.action.ERROR"></html:text>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="color:firebrick">
                                <html:errors property="codigo"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="color: black">Nombre del Decanato</td>
                            <td>
                                <html:text name="Decanato" property="nombre" maxlength="50" value="" errorStyleClass="error"
                                           errorKey="org.apache.struts.action.ERROR"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="color:firebrick">
                                <html:errors property="nombre"/>
                            </td>
                        <tr>
                            <td colspan="2" style="color:firebrick">
                                <html:errors property="registro"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p style="text-align: center">
                    <html:submit onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
                        Agregar Decanato
                    </html:submit>
                    <html:reset value="Limpiar"/>
                </p>

            </html:form>

        </div>

    </body>
</html>
