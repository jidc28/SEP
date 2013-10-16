<%-- 
    Document   : ModificarInfoP
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
--%>

<%  Object usbid = session.getAttribute("usbid");
    Object nombre = session.getAttribute("nombre");
    Object apellido = session.getAttribute("apellido");
    Object cedula = session.getAttribute("cedula");
    Object genero = session.getAttribute("genero");
    Object email = session.getAttribute("email");
    Object nivel = session.getAttribute("nivel");
    Object jubilado = session.getAttribute("jubilado");
    Object lapso = session.getAttribute("lapso");

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
                    <a class="menuitem" href="VistaProfesor.jsp">
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
                    <h1>Gestión de información del profesor</h1>

                    <html:form action="/guardarModificarInfoP">
                        <div id="welcome">
                            <table border="0">
                                <tbody>
                                <td><html:hidden property="usbid" value="<%=usbid.toString()%>"/></td>
                                <tr>
                                    <td>USBID:</td>
                                    <td><html:text disabled="true" property="nada" value="<%=usbid.toString()%>"/></td>
                                </tr>
                                <tr>
                                    <td>Cedula:</td>
                                    <td><html:text property="cedula" value="<%=cedula.toString()%>"/></td>
                                </tr>
                                <tr><td style="color:firebrick">
                                        <html:errors property="cedula"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Nombre:</td>
                                    <td><html:text property="nombre" value="<%=nombre.toString()%>"/></td>
                                </tr>
                                <tr>
                                    <td>Apellido:</td>
                                    <td><html:text property="apellido" value="<%=apellido.toString()%>"/></td>
                                </tr>
                                <tr>
                                    <td>Genero:</td>
                                    <td>
                                        <html:select property="genero">
                                            <%if (genero.equals("M")) {%>
                                            <html:option value="M">Masculino</html:option>
                                            <html:option value="F">Femenino</html:option>
                                            <%} else {%>
                                            <html:option value="F">Femenino</html:option>
                                            <html:option value="M">Masculino</html:option>
                                            <%}%>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Correo Electrónico:</td>
                                    <td>
                                        <html:text property="email" value="<%=email.toString()%>"/>
                                    </td>

                                </tr>
                                <tr><td style="color:firebrick">
                                        <html:errors property="email"/>
                                    </td></tr>



                                <tr>
                                    <td>Lapso Contractual:</td>
                                    <td>
                                        <html:select property="lapso_contractual">
                                            <%if (lapso.equals("1")) {%>
                                            <html:option value="1">1</html:option>
                                            <html:option value="3">3</html:option>
                                            <html:option value="5">5</html:option>
                                            <%} else if (lapso.equals("3")) {%>
                                            <html:option value="3">3</html:option>
                                            <html:option value="1">1</html:option>
                                            <html:option value="5">5</html:option>
                                            <%} else {%>
                                            <html:option value="5">5</html:option>
                                            <html:option value="1">1</html:option>
                                            <html:option value="3">3</html:option>
                                            <%}%>
                                        </html:select>Año(s)
                                    </td>
                                </tr>
                                <tr>
                                    <td>Nivel:</td>
                                    <td>
                                        <html:select property="nivel">
                                            <%if (nivel.equals("ayudanteAcad")) {%>
                                            <html:option value="ayudanteAcad">Ayudante Académico</html:option>
                                            <html:option value="asistente">Asistente</html:option>
                                            <html:option value="agregado">Agregado</html:option>
                                            <html:option value="asociado">Asociado</html:option>
                                            <html:option value="titular">Titular</html:option>
                                            <%} else if (nivel.equals("asistente")) {%>
                                            <html:option value="asistente">Asistente</html:option>
                                            <html:option value="ayudanteAcad">Ayudante Académico</html:option>
                                            <html:option value="agregado">Agregado</html:option>
                                            <html:option value="asociado">Asociado</html:option>
                                            <html:option value="titular">Titular</html:option>
                                            <%} else if (nivel.equals("agregado")) {%>
                                            <html:option value="agregado">Agregado</html:option>
                                            <html:option value="ayudanteAcad">Ayudante Académico</html:option>
                                            <html:option value="asistente">Asistente</html:option>
                                            <html:option value="asociado">Asociado</html:option>
                                            <html:option value="titular">Titular</html:option>
                                            <%} else if (nivel.equals("asociado")) {%>
                                            <html:option value="asociado">Asociado</html:option>
                                            <html:option value="ayudanteAcad">Ayudante Académico</html:option>
                                            <html:option value="asistente">Asistente</html:option>
                                            <html:option value="agregado">Agregado</html:option>
                                            <html:option value="titular">Titular</html:option>
                                            <%} else {%>
                                            <html:option value="titular">Titular</html:option>
                                            <html:option value="ayudanteAcad">Ayudante Académico</html:option>
                                            <html:option value="asistente">Asistente</html:option>
                                            <html:option value="agregado">Agregado</html:option>
                                            <html:option value="asociado">Asociado</html:option>
                                            <%}%>
                                        </html:select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>¿Ha sido jubilado en el último año de una institución de la administración pública?</td>
                                    <td
                                        ><html:select property="jubilado">
                                            <% if (jubilado.equals("S")) {%>
                                            <html:option value="S">Si</html:option>
                                            <html:option value="N">No</html:option>
                                            <%} else {%>
                                            <html:option value="N">No</html:option>
                                            <html:option value="S">Si</html:option>
                                            <%}%>

                                        </html:select>
                                    </td>
                                </tr>


                                <tr>
                                    <td>
                                        <html:submit value="Guardar" />
                                    </td>

                                </tr>                                         
                                </tbody>
                            </table>
                        </div>
                    </html:form>
                </div>

            </div>

    </body>
</html>

<%} else {%>
<html>

    <title> hello</title>
</html>
<% }%>

