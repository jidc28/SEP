<%-- 
    Document   : ConsultaCoordinacion
    Created on : 01/10/2013, 12:16:56 AM
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

             <h4> Lista de Coordinaciones en el sistema:</h4>
            <div id="testTable">
                <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                    <thead>
                        <tr>
                            <th width="20%" align="center">
                                Codigo
                            </th>
                            <th width="20%" align="center">
                                Nombre Coordinacion
                            </th>
                            <th width="20%" align="center">
                                Estado
                            </th>
                            <th width="20%" align="center">
                                Modificar Nombre
                            </th>
                            <th width="20%" align="center">
                                Modificar Estado
                            </th>
                        </tr>
                    </thead>
                    <logic:iterate name="coordinaciones" id="Coordinaciones">
                        <tr>
                            <td width="20%" align="center">
                                <bean:write name="Coordinaciones" property="codigo"/>
                            </td>
                            <td width="20%" align="center">
                                <bean:write name="Coordinaciones" property="nombre"/>
                            </td>
                            <td width="20%" align="center">
                                <bean:write name="Coordinaciones" property="estado"/>
                            </td>
                            <td width="20%" align="center">
                                <html:form action="/editarNombreCoordinacion" onsubmit="return(this)">
                                    <html:hidden name="Coordinaciones" property="codigo"/>
                                    <html:image src="imagenes/edit.png" value="" property=""/>
                                </html:form>
                            </td>
                            <td width="20%" align="center">
                                <html:form action="/cambiarStatusCoordinacion" onsubmit="return(this)">
                                    <html:hidden name="Coordinaciones" property="codigo"/>
                                    <html:image src="imagenes/visibilidad.png" value="" property=""/>
                                </html:form>
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </div>

    </body>
</html>