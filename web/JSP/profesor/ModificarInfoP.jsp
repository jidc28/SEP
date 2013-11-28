<%-- 
    Document   : ModificarInfoP
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
        <link rel="stylesheet" type="text/css" href="css/datetimepicker/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="css/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
        <script type="text/javascript" src="css/bootstrap2.3.2/js/bootstrap.js"></script>
        <script type="text/javascript" src="css/bootstrap2.3.2/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#datetimepicker1').datetimepicker({
                    pickTime: false
                });
            });
        </script>
        <script type="text/javascript">
            $(function() {
                $('#datetimepicker2').datetimepicker({
                    pickTime: false
                });
            });
        </script>
        <h4> Modificar Información </h4>
        <html:form action="/guardarModificarInfoP" acceptCharset="ISO=8859-1" onsubmit="return(this)">
            <table border="0">
                <tbody>
                    <tr style="height: 35px;">
                        <td>UsbID</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="usbid" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="usbid" />
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Cédula</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="cedula" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="cedula" />
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Nombre</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="nombre" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="nombre" />
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Apellido</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="apellido" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="apellido" />
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Género</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="genero" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="genero" />
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Correo electrónico institucional</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="email" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="email" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color: firebrick">
                            <html:errors property="email"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Correo electrónico personal</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="false" name="profesor" property="email_personal" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Lapso Contractual</td>
                        <td colspan="2" style="color: firebrick">
                            <p style="font-size: 12px;">Formato: mm/dd/aaaa</p>
                                <div id="datetimepicker1" class="input-append">
                                  <html:text name="profesor" property="lapso_contractual_inicio" style="height: 30px; width: 100px;"></html:text>
                                  <span class="add-on" style="height: 30px">
                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                                    </i>
                                  </span>
                                </div>
                                <div id="datetimepicker2" class="input-append">
                                  <html:text name="profesor" property="lapso_contractual_fin" style="height: 30px; width: 100px;"></html:text>
                                  <span class="add-on" style="height: 30px">
                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                                    </i>
                                  </span>
                                </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nivel</td>
                        <td>
                            <html:select style="width: 258px;" property="nivel">
                                <html:option value="Ayudante Academico">Ayudante Académico</html:option>
                                <html:option value="Asistente">Asistente</html:option>
                                <html:option value="Agregado">Agregado</html:option>
                                <html:option value="Asociado">Asociado</html:option>
                                <html:option value="Titular">Titular</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 280px;">¿Ha sido jubilado en el último año de una institución de la administración pública?</td>
                        <td>
                            <html:select style="width: 258px;" property="jubilado">
                                <html:option value="S">Si</html:option>
                                <html:option value="N">No</html:option>
                            </html:select>
                        </td>
                </tbody>
            </table>
            <br>
            <p style="text-align: center">
                <html:submit styleClass="btn btn-success"> Guardar Datos </html:submit></p>
            </html:form>
            
    </body>
</html>