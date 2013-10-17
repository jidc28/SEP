<%-- 
    Document   : ShowUser
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script type="text/javascript">
    function altRows(id) {
        if (document.getElementsByTagName) {

            var table = document.getElementById(id);
            var rows = table.getElementsByTagName("tr");

            for (i = 0; i < rows.length; i++) {
                if (i % 2 == 0) {
                    rows[i].className = "evenrowcolor";
                } else {
                    rows[i].className = "oddrowcolor";
                }
            }
        }
    }
    $(document).ready(function() {
        $('table').tablePagination({});
    });
    window.onload = function() {
        altRows('alternatecolor');
    }
</script>

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
            
            <h4> Lista de Profesores en el sistema:</h4>
            <div id="testTable">
                <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                    <thead>
                        <tr>
                            <th width="155px" align="center">
                                UsbID
                            </th>
                            <th width="155px" align="center">
                                Tipo de Usuario
                            </th>
                            <th width="155px" align="center">
                                Departamento
                            </th>
                            <th width="155px" align="center">
                                Estado
                            </th>
                            <th width="155px" align="center">
                                Modificar
                            </th>
                        </tr>
                    </thead>
                    <logic:iterate name="usuarios" id="Usuarios">
                        <tr>
                            <td width="150px" align="center">
                                <bean:write name="Usuarios" property="usbid"/>
                            </td>
                            <td width="150px" align="center">
                                <bean:write name="Usuarios" property="tipousuario"/>
                            </td>
                            <td width="150px" align="center">
                                <bean:write name="Usuarios" property="departamento"/>
                            </td>
                            <td width="150px" align="center">
                                Visible
                            </td>
                            <td width="150px" align="center">
                                <!--
                                <html:form action="/editarUser" onsubmit="return(this)">
                                    <html:hidden name="Usuarios" property="usbid"/>
                                    <html:image src="imagenes/edit.png" value="" property=""/>
                                </html:form>
                                -->
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </div>
        </div>

    </body>
</html>