<%-- 
    Document   : AdminDecanatoRegistrarSuccess
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
                    <a class="menuitem" href="VistaAdministrador.jsp">
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
                    Decanato registrado exitosamente.
                </p>

                <div >
                    <h1 align="center">Gestión de Usuario</h1>
                    <!--
                    <html:link action="/createUserA" >
                        <h5 align ="center">Crear Usuario</h5>
                    </html:link>
                    -->
                    <html:link action="/showUserA" >
                        <h5 align ="center">Mostrar Usuarios</h5>
                    </html:link>
                    <html:link action="/listarProfesores" >
                        <h5 align ="center">Mostrar Profesores</h5>
                    </html:link>

                </div>
                <div >
                    <h1 align="center">Gestión de Carreras</h1>
                    <html:link action="/agregaCarreraA" >
                        <h5 align ="center">Agregar Carrera</h5>
                    </html:link>
                    <html:link action="/consultaCarreraA" >
                        <h5 align ="center">Consultar Carrera</h5>
                    </html:link>

                </div>
                <div >
                    <h1 align="center">Gestión de Decanatos</h1>
                    <html:link action="/agregaDecanatoA" >
                        <h5 align ="center">Agregar Decanato</h5>
                    </html:link>
                    <html:link action="/consultaDecanatoA" >
                        <h5 align ="center">Consultar Decanato</h5>
                    </html:link>

                </div>
                <div >
                    <h1 align="center">Gestión de Coordinaciones</h1>
                    <html:link action="/agregaCoordinacionA" >
                        <h5 align ="center">Agregar Coordinación</h5>
                    </html:link>
                    <html:link action="/consultaCoordinacionA" >
                        <h5 align ="center">Consultar Coordinación</h5>
                    </html:link>

                </div>
                <div >
                    <h1 align="center">Gestión de Núcleos Universitarios</h1>
                    <html:link action="/NucleoUnivA" >
                        <h5 align ="center">Agregar Núcleo Universitario</h5>
                    </html:link>
                    <html:link action="/consultaNucleoUniversitarioA" >
                        <h5 align ="center">Consultar Núcleo Universitario</h5>
                    </html:link>

                </div>
            </div>

        </div>

    </body>
</html>
