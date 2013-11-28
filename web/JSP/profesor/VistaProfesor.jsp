<%-- 
    Document   : VistaProfesor
    Created on : 06/06/2013, 10:04:58 PM
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
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <div style="width:760px;margin-left:auto;margin-right:auto;">

            <logic:present name="actualizacion">
                <p align ="center" style="background-color: springgreen;
                   width: 300px; margin-left: auto; margin-right: auto">
                    Información actualizada exitosamente.
                </p>
            </logic:present>
            <logic:present name="eliminacion">
                <p align ="center" style="background-color: springgreen;
                   width: 300px; margin-left: auto; margin-right: auto">
                    Información eliminada exitosamente.
                </p>
            </logic:present>


            <div align="center" >
                <h1 >Gestión de información del profesor</h1>
                <table border="0">
                    <tbody>
                        <tr>
                            <td>UsbID</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="usuario" property="usbid"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Cédula</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="profesor" property="cedula"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="profesor" property="nombre"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Apellido</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="profesor" property="apellido"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Género</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="profesor" property="genero"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Correo electrónico institucional</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="profesor" property="email"/>
                            </td>
                        </tr>
                                                </tr>
                        <tr>
                            <td>Correo electrónico personal</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="profesor" property="email_personal"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Lapso Contractual</td>
                            <td>
                                <html:text style="width: 127px; height 20px;" disabled="true" name="profesor" property="lapso_contractual_inicio"/>
                                <html:text style="width: 127px; height 20px;" disabled="true" name="profesor" property="lapso_contractual_fin"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Nivel</td>
                            <td>
                                <html:text style="width: 258px; height 20px;" disabled="true" name="profesor" property="nivel"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Jubilación</td>
                            <td>
                                <html:text style="width: 258px; height: 20px;" disabled="true" name="profesor" property="jubilado"/>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table style="margin-top: 5px;">
                        <tbody>
                            <td>
                                <html:form  action="/modificarInfoP" method="POST" enctype="multipart/form-data" onsubmit="return(this)" >
                                    <html:hidden name="usuario" property="usbid"/>
                                    <html:submit style="margin: 2.5px;" styleClass="btn btn-info" value="Modificar"/>
                                </html:form>
                            </td>
                            <td>
                                <html:form  action="/agregarInfoP" >
                                    <html:hidden name="usuario" property="usbid"/>
                                    <html:submit style="margin: 2.5px;" styleClass="btn btn-default" value="Agregar información"/>
                                </html:form>
                            </td>
                            <td>
                                <html:form  action="/eliminarInfoP" >
                                    <html:hidden name="usuario" property="usbid"/>
                                    <html:submit style="margin: 2.5px;" styleClass="btn btn-danger" value="Eliminar"/>
                                </html:form>
                            </td>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
