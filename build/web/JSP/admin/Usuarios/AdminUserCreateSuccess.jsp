<%-- 
    Document   : AdminUserCreateSuccess
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

                    <p align ="center" style="background-color: springgreen;
                       width: 300px; margin-left: auto; margin-right: auto">
                        Usuario registrado exitosamente.
                    </p>

                    <h1>Gestión de Usuario</h1>
                    <html:link action="/createUserA" >
                        <p align ="center">Crear Usuario</p>
                    </html:link>
                    <html:link action="/showUserA" >
                        <p align ="center">Mostrar Usuarios</p>
                    </html:link>
                    <html:link action="/listarProfesores" >
                        <p align ="center">Mostrar Profesores</p>
                    </html:link>

                </div>
                <div id="contenido-der">
                    <h1>Gestión de Carreras</h1>
                    <html:link action="/agregaCarreraA" >
                        <p align ="center">Agregar Carrera</p>
                    </html:link>
                    <html:link action="/consultaCarreraA" >
                        <p align ="center">Consultar Carrera</p>
                    </html:link>

                </div>
                <div id="contenido-der">
                    <h1>Gestión de Decanatos</h1>
                    <html:link action="/agregaDecanatoA" >
                        <p align ="center">Agregar Decanato</p>
                    </html:link>
                    <html:link action="/consultaDecanatoA" >
                        <p align ="center">Consultar Decanato</p>
                    </html:link>

                </div>
                <div id="contenido-der">
                    <h1>Gestión de Coordinaciones</h1>
                    <html:link action="/agregaCoordinacionA" >
                        <p align ="center">Agregar Coordinación</p>
                    </html:link>
                    <html:link action="/consultaCoordinacionA" >
                        <p align ="center">Consultar Coordinación</p>
                    </html:link>

                </div>
                <div id="contenido-der">
                    <h1>Gestión de Núcleos Universitarios</h1>
                    <html:link action="/NucleoUnivA" >
                        <p align ="center">Agregar Núcleo Universitario</p>
                    </html:link>
                    <html:link action="/consultaNucleoUniversitarioA" >
                        <p align ="center">Consultar Núcleo Universitario</p>
                    </html:link>

                </div>
            </div>

        </div>

    </body>
</html>
