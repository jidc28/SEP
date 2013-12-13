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
        <logic:present name="actualizacion">
            <div class="alert alert-success" id="alert">
                Información actualizada exitosamente.
            </div>
        </logic:present>
        <logic:present name="eliminacion">
            <div class="alert alert-success" id="alert">
                Información eliminada exitosamente.
            </div>
        </logic:present>


        <div align="center" >
            <h4>Gestión de información del profesor</h4>
            <table border="0">
                <tbody>
                    <tr style="height: 35px;">
                        <td>UsbID</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="usuario" property="usbid"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Cédula</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="cedula"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Nombre</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="nombre"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Apellido</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="apellido"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Género</td>
                        <td>
                            <logic:equal name="profesor" property="genero" value="F">
                                <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="genero" value="Femenino"/>
                            </logic:equal>
                            <logic:equal name="profesor" property="genero" value="M">
                                <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="genero" value="Masculino"/>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Correo electrónico institucional</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="email"/>
                        </td>
                    </tr>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Correo electrónico personal</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="email_personal"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Lapso Contractual</td>
                        <td>
                            <html:text style="width: 127px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="lapso_contractual_inicio"/>
                            <html:text style="width: 127px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="lapso_contractual_fin"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Nivel</td>
                        <td>
                            <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="nivel"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td>Jubilación</td>
                        <td>
                            <logic:equal name="profesor" property="jubilado" value="S">
                                <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="jubilado" value="Si"/>
                            </logic:equal>
                            <logic:equal name="profesor" property="jubilado" value="N">
                                <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="jubilado" value="No"/>
                            </logic:equal>
                            <logic:equal name="profesor" property="jubilado" value="">
                                <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="true" name="profesor" property="jubilado" value=""/>
                            </logic:equal>
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
                    <!-- <html:form  action="/agregarInfoP" >
                        <html:hidden name="usuario" property="usbid"/>
                        <html:submit style="margin: 2.5px;" styleClass="btn btn-default" value="Agregar información"/>
                    </html:form>-->
                </td>
                <td>
                    <html:form  action="/eliminarInfoP" >
                        <html:hidden name="usuario" property="usbid"/>
                        <html:submit style="margin: 2.5px;" styleClass="btn btn-danger" value="Eliminar" 
                                     onclick="javascript: return confirm('¿Está seguro que desea eliminar su información?')"/>
                    </html:form>
                </td>
                </tbody>
            </table>
        </div>
    </body>
</html>
