<%-- 
    Document   : ConsultaDecanatoSuccess
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%@page import="com.myapp.struts.Decanato"%>
<%@page import="java.util.ArrayList"%>
<%
    Object usbid = session.getAttribute("usbid");
    ArrayList<Decanato> decans = (ArrayList<Decanato>) session.getAttribute("decanatos");
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
                
                    <p align ="center" style="background-color: springgreen;
                       width: 300px; margin-left: auto; margin-right: auto">
                        Status de Decanato modificado exitosamente.
                    </p>

                <div id="contenido-der">
                    <h1 style="background-color: cornflowerblue;width: 140px;margin-left: auto; margin-right: auto">Lista de Decanatos</h1>
                    <div id="content">

                        <%if (decans.size() != 0) {%>
                        <div style="width: 700px;margin-left: auto; margin-right: auto;">



                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Codigo</h1>	
                                        </td>
                                        <td>
                                            <h1 style="width: 220px;margin-left: auto; margin-right: auto;color: darkblue">Nombre de Decanato</h1>	
                                        </td>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Status de Decanato</h1>	
                                        </td>
                                    </tr>
                                    <%for (int i = 0; i < decans.size(); i++) {%>
                                    <tr>
                                        <td>
                                            <p style="color: brown"> <%=decans.get(i).getCodigo()%></p>	
                                        </td>
                                        <td>
                                            <p style="color: brown"> <%=decans.get(i).getNombre()%></p>	
                                        </td>
                                        <td>
                                            <p style="color: brown"> <%=decans.get(i).getStatus()%></p>	
                                        </td>
                                        <td>
                                            <html:form  action="/editarStatus">
                                                <html:hidden property="usbid" value="<%=usbid.toString()%>"/>
                                                <html:hidden property="codigo" value="<%=decans.get(i).getCodigo()%>"/>
                                                <html:submit value="Editar"/>
                                            </html:form>
                                        </td>
                                        <td>
                                            <html:form  action="/cambiarStatus">
                                                <html:hidden property="usbid" value="<%=usbid.toString()%>"/>
                                                <html:hidden property="codigo" value="<%=decans.get(i).getCodigo()%>"/>
                                                <html:submit value="Cambiar Status"/>
                                            </html:form>	                                        
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>


                            <%} else {%>
                            No hay Decanatos En el Sistema para Mostrar
                            <%}%>

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

