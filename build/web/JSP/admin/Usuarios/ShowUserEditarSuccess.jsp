<%-- 
    Document   : ShowUserEditarSuccess
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%@page import="Clases.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%
    Object usbid = session.getAttribute("usbid");
    ArrayList<Usuario> users = (ArrayList<Usuario>) session.getAttribute("usuarios");
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
                    
                    <p align ="center" style="background-color: springgreen;
                       width: 400px; margin-left: auto; margin-right: auto">
                        Rol y Departamento de Usuario modificado exitosamente.
                    </p>
                    
                    <h1 style="background-color: cornflowerblue;width: 140px;margin-left: auto; margin-right: auto">Lista de usuarios</h1>
                    <div id="content">

                        <%if (users.size() != 0) {%>
                        <div style="width: 604px;margin-left: auto; margin-right: auto;">



                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Usbid</h1>	
                                        </td>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Tipo de Usuario</h1>	
                                        </td>
                                        <td>
                                            <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Departamento</h1>	
                                        </td>
                                    </tr>
                                    <%for (int i = 0; i < users.size(); i++) {%>
                                    <tr>
                                        <td>
                                            <p style="color: green"> <%=users.get(i).getUsbid()%></p>	
                                        </td>
                                        <td>
                                            <p style="color: green"> <%=users.get(i).getTipousuario()%></p>	
                                        </td>
                                        <td>
                                            <p style="color: green"> <%=users.get(i).getDepartamento()%></p>	
                                        </td>
                                        <td>
                                            <html:form  action="/editarUser">
                                                <html:hidden property="usbid" value="<%=users.get(i).getUsbid()%>"/>
                                                <html:hidden property="tipousuario" value="<%=users.get(i).getTipousuario()%>"/>
                                                <html:hidden property="departamento" value="<%=users.get(i).getDepartamento()%>"/>
                                                <html:submit value="Editar"/>
                                            </html:form>
                                        </td>
                                        <td>
                                            <html:form  action="/eliminarUser">
                                                <html:hidden property="usbid" value="<%=users.get(i).getUsbid()%>"/>
                                                <html:hidden property="tipousuario" value="<%=users.get(i).getTipousuario()%>"/>
                                                <html:hidden property="departamento" value="<%=users.get(i).getDepartamento()%>"/>
                                                <html:submit value="Eliminar" onclick="return confirm('¿Está seguro que desea eliminar este usuario?')"/>
                                            </html:form>	                                        
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>


                            <%} else {%>
                            No hay Usuarios En el Sistema para Mostrar
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
