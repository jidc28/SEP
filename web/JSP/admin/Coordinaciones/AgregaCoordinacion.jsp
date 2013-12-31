<%-- 
    Document   : AgregarCoordinacion
    Created on : 10/06/2013, 07:43:54 PM
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

        <h4>Registro de Coordinaciones</h4>

        <html:form action="/registrarCoordinacion" method="POST" acceptCharset="ISO-8859-1" enctype="multipart/form-data" onsubmit="return(this)">
            <table border="0" style="margin-top: 50px;">
                <tbody>
                    <tr style="height: 35px;">
                        <td style="color: black">Codigo de la Coordinación</td>
                        <td>
                            <html:text name="Coordinacion" property="codigo" maxlength="10" value="" errorStyleClass="error"
                                       style="margin-bottom: 0px;height: 30px;"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="color:firebrick">
                            <html:errors property="codigo"/>
                        </td>
                    </tr>

                    <tr>
                        <td style="color: black">Nombre de la Coordinación</td>
                        <td>
                            <html:text property="nombre" name="Coordinacion" maxlength="50" errorKey="org.apache.struts.action.ERROR"/>
                            <!--
                            <html:textarea name="Coordinacion" property="nombre" errorStyleClass="error"
                                       rows="5"
                                       style="margin-bottom: 0px;"
                                       errorKey="org.apache.struts.action.ERROR"/>
                            -->
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="color:firebrick">
                            <html:errors property="nombre"/>
                        </td>
                    <tr>
                        <td colspan="2" style="color:firebrick">
                            <html:errors property="registro"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <p style="text-align: center; margin-top: 10px;">
                <html:submit styleClass="btn btn-success"
                    onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
                    Agregar
                </html:submit>
                <html:reset styleClass="btn btn-default" value="Limpiar"/>
            </p>

        </html:form>

</body>
</html>
