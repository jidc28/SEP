<%-- 
    Document   : ConsultaCarreraSuccess
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
            
            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto">
                Carrera modificada exitosamente.
            </p>
            <h4> Lista de Carreras en el sistema:</h4>
            <div id="testTable">
                <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                    <thead>
                        <tr>
                            <th width="155px" align="center">
                                Codigo
                            </th>
                            <th width="155px" align="center">
                                Nombre Carrera
                            </th>
                            <th width="155px" align="center">
                                Estado
                            </th>
                            <th width="155px" align="center">
                                Editar Nombre
                            </th>
                            <th width="155px" align="center">
                                Cambiar Estado
                            </th>
                    </thead>
                    <logic:iterate name="carreras" id="Carreras">
                        <tr>
                            <td width="150px" align="center">
                                <bean:write name="Carreras" property="codigo"/>
                            </td>
                            <td width="150px" align="center">
                                <bean:write name="Carreras" property="nombre"/>
                            </td>
                            <td width="150px" align="center">
                                <bean:write name="Carreras" property="status"/>
                            </td>
                            <td>
                                <html:form  action="/editarNombre" onsubmit="return(this)">
                                    <html:hidden name="Carreras" property="codigo" />
                                    <html:image src="imagenes/edit.png" value="" property=""/>
                                </html:form>
                            </td>
                            <td>
                                <html:form  action="/cambiarStatusCarrera" onsubmit="return(this)">
                                    <html:hidden name="Carreras" property="codigo" />
                                    <html:image src="imagenes/visibilidad.png" value="" property=""/>
                                </html:form>	                                        
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </div>

        </div>

    </body>
</html>