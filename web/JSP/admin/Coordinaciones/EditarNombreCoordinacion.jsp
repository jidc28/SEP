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
        <title>Sistema de Evaluación de Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <html:form action="/cambiarNombreCoordinacionA" acceptCharset="ISO-8859-1" onsubmit="return(this)">
            <html:hidden name="Coordinacion" property="codigo"/>
            <h4><bean:write name="Coordinacion" property="nombre"/></h4>
            <h4>(<bean:write name="Coordinacion" property="codigo"/>)</h4>
            <br>
        <center>
            <html:textarea name="Coordinacion" property="nombre" errorStyleClass="error"
                           rows="5" style="width: 300px;"
                           errorKey="org.apache.struts.action.ERROR">
            </html:textarea>
        </center>
        <p style="text-align: center; margin-top: 10px;">
            <html:submit styleClass="btn btn-success"
                         onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
                Modificar
            </html:submit>
        </html:form>                 
    </body>
</html>
