<%-- 
    Document   : VistaProfesor
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
        <link rel="stylesheet" type="text/css" href="css/VistaProfesor.css">
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
                                        Bienvenidos, profesor <%=usbid.toString()%>.
                                        <html:link action="/perfil">Perfil</html:link> |
                                        <html:link href="/sistema2/VistaProfesor.jsp">
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
                    
                    <p align ="center" style="background-color: springgreen;
                       width: 300px; margin-left: auto; margin-right: auto">
                        Información actualizada exitosamente.
                    </p>
                    
                    <h1>Gestión de información del profesor</h1>
                    <html:form  action="/modificarInfoP" >
                        <html:hidden property="usbid" value="<%=usbid.toString()%>"/>
                        <html:submit value="Modificar información"/>
                    </html:form>
                    <html:form  action="/eliminarInfoP" >
                        <html:hidden property="usbid" value="<%=usbid.toString()%>"/>
                        <html:submit value="Eliminar información"/>
                    </html:form>
                    <html:form  action="/agregarInfoP" >
                        <html:hidden property="usbid" value="<%=usbid.toString()%>"/>
                        <html:submit value="Agregar información"/>
                    </html:form>
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

