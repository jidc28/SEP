<%-- 
    Document   : VistaProfesor
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

<logic:present>
    <div class="alert alert-success" id="alert">
        Nuevo Ingreso al Sistema, por favor ingrese sus datos.
    </div>

    <html:form action="/almacenarInfoProfesor" acceptCharset="ISO=8859-1" onsubmit="return(this)">
        <table border="0">
            <tbody>
                <tr style="height: 35px">
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
                <tr style="height: 35px;">
                    <td>Correo electrónico personal</td>
                    <td>
                        <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="false" name="profesor" property="email_personal" errorStyleClass="error"
                                   errorKey="org.apache.struts.action.ERROR"></html:text>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color: firebrick">
                        <html:errors property="email"/>
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
                        <html:select style="width: 258px;" name="profesor" property="nivel">
                <option>
                    <bean:write name="profesor" property="nivel"/>
                </option>
                <logic:iterate name="niveles" id="nivel_i">
                    <option>
                        <bean:write name="nivel_i"/>
                    </option>
                </logic:iterate>
            </html:select>
        </td>
    </tr>
    <tr>
        <td style="width: 280px;">¿Ha sido jubilado en el último año de una 
            institución de la administración pública?</td>
        <td>
            <logic:equal name="profesor" property="nivel" value="Ayudante Academico">
                <html:select style="width: 258px;" property="jubilado" disabled="true">
                    <html:option value="N">No</html:option>
                </html:select>
                <html:hidden name="profesor" property="jubilado" value="N"/>
            </logic:equal>
            <logic:notEqual name="profesor" property="nivel" value="Ayudante Academico">
                <logic:equal name="profesor" property="jubilado" value="N">
                    <html:select style="width: 258px;" property="jubilado">
                        <html:option value="N">No</html:option>
                        <html:option value="S">Si</html:option>
                    </html:select>
                </logic:equal>
                <logic:equal name="profesor" property="jubilado" value="S">
                    <html:select style="width: 258px;" property="jubilado">
                        <html:option value="S">Si</html:option>
                        <html:option value="N">No</html:option>
                    </html:select>
                </logic:equal>
            </logic:notEqual>
            <logic:equal name="profesor" property="jubilado" value="">
                <html:select style="width: 258px;" property="jubilado">
                    <html:option value="N">No</html:option>
                    <html:option value="S">Si</html:option>
                </html:select>
            </logic:equal>
        </td>
    </tr>
</tbody>
</table>

<h4>Títulos Obtenidos</h4>

<table>
    <tbody>
        <tr>
            <td>Títulos</td>
            <td>
                <html:text style="width: 258px; margin-bottom: 0px;height: 30px;" disabled="false" 
                           name="titulos" property="titulo" errorStyleClass="error" 
                           errorKey="org.apache.struts.action.ERROR"></html:text>
                </td>
            </tr>
        </tbody>
    </table>
</html:form>
</logic:present>