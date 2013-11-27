<%-- 
    Document   : AgregaCarrera
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
    <body onload="clearForms()" onunload="clearForms()">
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <h4>
            <bean:write name="decanato_actual" property="nombre"/>: 
            <br>
            Registro de Carreras
        </h4>

        <html:form action="/registrarCarrera" method="POST" acceptCharset="ISO-8859-1" enctype="multipart/form-data" onsubmit="return(this)">
            <table border="0">
                <tbody>
                    <tr>
                        <td style="color: black">Codigo de la Carrera</td>
                        <td>
                            <html:text name="Carrera" property="codigo" maxlength="10" value="" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="color:firebrick">
                            <html:errors property="codigo"/>
                        </td>
                    </tr>

                    <tr>
                        <td style="color: black">Nombre de la Carrera</td>
                        <td>
                            <html:text name="Carrera" property="nombre" maxlength="50" value="" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
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
            <p style="text-align: center">
                <html:submit onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
                    Agregar Carrera
                </html:submit>
                <html:reset value="Limpiar"/>
            </p>

        </html:form>
</body>
</html>
