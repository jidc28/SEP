<%-- 
    Document   : AgregarProfesor
    Created on : Feb 15, 2014, 4:06:00 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4> Planilla para agregar un profesor </h4>
<html:form action="/agregarProfesor">
    <table border="0">
        <tbody>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong>USBID</strong></td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="usbid"/>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong>CÉDULA</strong></td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="cedula"/>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong>NOMBRE</strong></td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="nombre"/>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong> APELLIDO</strong> </td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="apellido"/>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"> <strong> GÉNERO</strong> </td>
                <td>
                    <html:radio property="genero" value="M" style="margin: 0px;">
                        Masculino
                    </html:radio>
                    <br>
                    <html:radio property="genero" value="F" style="margin: 0px;">
                        Femenino
                    </html:radio><br>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"> 
                    <strong> CORREO ELECTRÓNICO <br> INSTITUCIONAL </strong> 
                </td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="email"/>
                </td>
            </tr>
        </tbody>
    </table>
    <html:submit styleClass="btn btn-success" style="margin-top: 20px;">
        Agregar Profesor
    </html:submit>
</html:form>