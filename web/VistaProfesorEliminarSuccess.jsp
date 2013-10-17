<%-- 
    Document   : VistaProfesorEliminarSuccess
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
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
                    <a class="menuitem" href="VistaProfesor.jsp">
                        Inicio
                    </a>
                    <a class="menuitem" href="noimplementado.jsp">
                        Perfil
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


            <div style="width:760px;margin-left:auto;margin-right:auto;">
                <p align ="center" style="background-color: springgreen;
                   width: 300px; margin-left: auto; margin-right: auto">
                    Información eliminada exitosamente.
                </p>
                <div align="center" >
                    <h1 >Gestión de información del profesor</h1>
                    <html:form  action="/modificarInfoP" method="POST" enctype="multipart/form-data" onsubmit="return(this)" >
                        <html:hidden name="usuario" property="usbid"/>
                        <html:submit  value="Modificar información"/>
                    </html:form>
                    <html:form  action="/eliminarInfoP" >
                        <html:hidden name="usuario" property="usbid"/>
                        <html:submit value="Eliminar información"/>
                    </html:form>
                    <html:form  action="/agregarInfoP" >
                        <html:hidden name="usuario" property="usbid"/>
                        <html:submit value="Agregar información"/>
                    </html:form>
                </div>
            </div>

        </div>

    </body>
</html>
