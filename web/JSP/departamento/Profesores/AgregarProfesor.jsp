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


<logic:present name="Profesor_existente">
    <div class="alert alert-danger" id="alert">
        Este usuario ya se encuentra registrado en el sistema.
    </div>
</logic:present>

<h4> Planilla para agregar un profesor </h4>

<html:form action="/agregarProfesor" acceptCharset="ISO-8859-1">
    <table border="0">
        <tbody>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong>USBID</strong></td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="usbid"/>
                </td>
                <td style="color: red">
                    <label style="color: red;">
                    <html:errors property="usbid" />
                    </label>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong>CÉDULA</strong></td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="cedula"/>
                </td>
                <td style="color: red">
                    <label style="color: red;">
                    <html:errors property="cedula" />
                    </label>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong>NOMBRE</strong></td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="nombre"/>
                </td>
                <td style="color: red">
                    <label style="color: red;">
                    <html:errors property="nombre" />
                    </label>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"><strong> APELLIDO</strong> </td>
                <td>
                    <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" 
                               name="Profesor" property="apellido"/>
                </td>
                <td style="color: red">
                    <label style="color: red;">
                    <html:errors property="apellido" />
                    </label>
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
                <td style="color: red">
                    <label style="color: red;">
                    <html:errors property="genero" />
                    </label>
                </td>
            </tr>
            <tr style="height: 35px;">
                <td style="font-size: 14px;"> <strong> NIVEL</strong> </td>
                <td>
                    <html:select style="width: 258px;" name="Profesor" property="nivel">
                        <html:option value="Ayudante Académico">
                            Ayudante Académico
                        </html:option>
                        <html:option value="Asistente">
                            Asistente
                        </html:option>
                        <html:option value="Agregado">
                            Agregado
                        </html:option>
                        <html:option value="Asociado">
                            Asociado
                        </html:option>
                        <html:option value="Titular">
                            Titular
                        </html:option>
                    </html:select>
                </td>
                <td style="color: red">
                    <label style="color: red;">
                    <html:errors property="nivel" />
                    </label>
                </td>
            </tr>
        </tbody>
    </table>
    <html:submit styleClass="btn btn-success" style="margin-top: 20px;">
        Agregar Profesor
    </html:submit>
</html:form>