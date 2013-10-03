<%-- 
    Document   : AdminDecanatoRegistrarSuccess
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
--%>

<% Object usbid = session.getAttribute("usbid");
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
        <link rel="stylesheet" type="text/css" href="css/AdminUserCreateSuccess.css">
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
                                        <html:link href="noimplementado.jsp">Perfil</html:link> |
                                        <html:link href="VistaAdministrador.jsp">
                                            Inicio
                                        </html:link> |
                                        <html:link href="noimplementado.jsp">
                                            Contáctenos
                                        </html:link> |
                                        <html:link href="noimplementado.jsp">
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

                    <p align ="center" style="background-color: springgreen;
                       width: 300px; margin-left: auto; margin-right: auto">
                        Decanato registrado exitosamente.
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

<%} else {%>
<html>

    <title> hello</title>
</html>
<% }%>

