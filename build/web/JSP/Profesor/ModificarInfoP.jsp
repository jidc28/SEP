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
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <h4> Modificar Información </h4>
        <html:form action="/guardarModificarInfoP" acceptCharset="ISO=8859-1" onsubmit="return(this)">
            <table border="0">
                <tbody>
                    <tr>
                        <td>UsbID</td>
                        <td>
                            <html:text disabled="true" name="profesor" property="usbid" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="usbid" />
                        </td>
                    </tr>
                    <tr>
                        <td>Cédula</td>
                        <td>
                            <html:text disabled="true" name="profesor" property="cedula" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="cedula" />
                        </td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td>
                            <html:text disabled="true" name="profesor" property="nombre" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="nombre" />
                        </td>
                    </tr>
                    <tr>
                        <td>Apellido</td>
                        <td>
                            <html:text disabled="true" name="profesor" property="apellido" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="apellido" />
                        </td>
                    </tr>
                    <tr>
                        <td>Género</td>
                        <td>
                            <html:text disabled="true" name="profesor" property="genero" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            <html:hidden name="profesor" property="genero" />
                        </td>
                    </tr>
                    <tr>
                        <td>Correo Electrónico</td>
                        <td>
                            <html:text disabled="false" name="profesor" property="email" errorStyleClass="error"
                                       errorKey="org.apache.struts.action.ERROR"></html:text>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="color: firebrick">
                            <html:errors property="email"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Lapso Contractual</td>
                        <td>
                            <html:select property="lapso_contractual">
                                <html:option value="1">1</html:option>
                                <html:option value="3">3</html:option>
                                <html:option value="5">5</html:option>
                            </html:select> Año(s)
                        </td>
                    </tr>
                    <tr>
                        <td>Nivel</td>
                        <td>
                            <html:select property="nivel">
                                <html:option value="ayudanteAcad">Ayudante Académico</html:option>
                                <html:option value="asistente">Asistente</html:option>
                                <html:option value="agregado">Agregado</html:option>
                                <html:option value="asociado">Asociado</html:option>
                                <html:option value="titular">Titular</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>¿Ha sido jubilado en el último</td>
                    </tr>
                    <tr>
                        <td>año de una institución de la</td>
                        <td>
                            <html:select property="jubilado">
                                <html:option value="S">Si</html:option>
                                <html:option value="N">No</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>administración pública?</td>
                    </tr>
                </tbody>
            </table>
            <p style="text-align: center">
                <html:submit> Guardar Datos </html:submit></p>
            </html:form>
    </body>
</html>