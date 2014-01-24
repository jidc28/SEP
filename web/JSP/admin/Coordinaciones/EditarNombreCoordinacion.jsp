<%-- 
    Document   : EditarNombreCoordinacion
    Created on : 01/10/2013, 06:16:42 PM
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
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Gestión de coordinaciones</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <html:form action="/cambiarNombreCoordinacionA" acceptCharset="ISO-8859-1" onsubmit="return(this)">
            <html:hidden name="Coordinacion" property="codigo"/>
            <h4><bean:write name="Coordinacion" property="nombre"/></h4>
            <h4>(<bean:write name="Coordinacion" property="codigo"/>)</h4>
            <br>
            <table border="0" style="margin-top: 0px;">
                <tbody>
                    <tr style="height: 35px;">
                        <td style="color: black; font-size: 14px; font-weight: bold;">
                            NOMBRE DE LA COORDINACIÓN
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <center>
                            <html:text property="nombre" name="Coordinacion" maxlength="50" 
                                       styleClass="input-nombre" style="height: 30px;" 
                                       errorKey="org.apache.struts.action.ERROR"/>
                        </center>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color:firebrick">
                            <html:errors property="registro"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color:firebrick">
                            <html:errors property="nombre"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <html:submit styleClass="btn btn-success"
                         onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
                Modificar
            </html:submit>
        </html:form>                 
    </body>
</html>
