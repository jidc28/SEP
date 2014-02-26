<%-- 
    Document   : agregarProfesor
    Created on : Feb 15, 2014, 4:06:00 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Agregar Profesor</title>
    </head>
    <body>
        <h4> Planilla para agregar un profesor </h4>
        <html:form action="/agregarProfesor">
            <table border="0">
                <tbody>
                    <tr style="height: 35px;">
                        <td style="font-size: 14px;"><strong>USBID</strong></td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" name="Profesor" property="usbid"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td style="font-size: 14px;"><strong>CÉDULA</strong></td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" name="Profesor" property="cedula"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td style="font-size: 14px;"><strong>NOMBRE</strong></td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" name="Profesor" property="nombre"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td style="font-size: 14px;"><strong> APELLIDO</strong> </td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" name="Profesor" property="apellido"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td style="font-size: 14px;"> <strong> GÉNERO</strong> </td>
                        <td>
                            <html:radio property="genero" value="M" style="margin: 0px;">
                                Masculino
                            </html:radio>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>
                        </td>
                        <td>
                            <html:radio property="genero" value="F" style="margin: 0px;">
                                Femenino
                            </html:radio><br>
                        </td>                        
                    </tr>
                    <tr style="height: 35px;">
                        <td style="font-size: 14px;"> <strong> CORREO ELECTRÓNICO <br> INSTITUCIONAL </strong> </td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" name="Profesor" property="email"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <html:submit styleClass="btn btn-success" style="margin-top: 20px;">
                Agregar Profesor
            </html:submit>
        </html:form>
    </body>
</html>
