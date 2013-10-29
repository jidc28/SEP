<%-- 
    Document   : EditarNombreNucleoUniversitario
    Created on : 02/10/2013, 01:40:38 AM
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
        
            <div id="testTable">
                <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                    <thead>
                        <tr>
                            <th width="155px" align="center">
                                Codigo
                            </th>
                            <th width="155px" align="center">
                                Nombre Nucleo
                            </th>
                            <th width="155px" align="center">

                            </th>
                        </tr>
                    </thead>
                    <tr>
                        <html:form action="/cambiarNombreNucleoUniversitarioA" acceptCharset="ISO-8859-1" onsubmit="return(this)">
                            <td width="150px" align="center">
                                <html:hidden name="NucleoUniversitario" property="codigo"/>
                                <bean:write name="NucleoUniversitario" property="codigo"/>
                            </td>
                            <td width="150px" align="center">
                                <html:text name="NucleoUniversitario" property="nombre" maxlength="100" errorStyleClass="error"
                                           errorKey="org.apache.struts.action.ERROR">
                                </html:text>
                            </td>
                            <td width="150px" align="center">
                                <p style="text-align: center">
                                    <html:submit onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
                                        Modificar
                                    </html:submit>
                                </p>
                            </td>
                        </html:form>                 
                    </tr>
                </table>
            </div>

    </body>
</html>

