<%-- 
    Document   : AgregarCoordinacion
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<h4>Registro de Coordinaciones</h4>

<html:form action="/registrarCoordinacion" method="POST" 
           acceptCharset="ISO-8859-1" enctype="multipart/form-data" 
           onsubmit="return(this)">
    <table border="0" style="margin-top: 30px;">
        <tbody>
            <logic:present name="decanatos">
                <tr style="height: 35px;">
                    <td style="color: black; font-size: 14px; font-weight: bold;">
                        ADSCRIBIR A DECANATO
                    </td>
                </tr>
                <tr style="height: 35px;">
                    <td>
                        <center>
                            <html:select styleClass="input-nombre" 
                                         name="Coordinacion" property="decanato">
                                <logic:iterate name="decanatos" id="decanato">
                                    <option>
                                        <bean:write name="decanato" property="nombre"/>
                                    </option>
                                </logic:iterate>
                            </html:select>
                        </center>
                    </td>
                </tr>
            </logic:present>
                <tr style="height: 35px;">
                    <td style="color: black; font-size: 14px; font-weight: bold;">
                        CÓDIGO DE LA COORDINACIÓN
                    </td>
                </tr>
                <tr>
                    <td>
                        <center>
                            <html:text name="Coordinacion" property="codigo" 
                                       maxlength="10" errorStyleClass="error"
                                       styleClass="input-codigo" style="height: 30px;"
                                       errorKey="org.apache.struts.action.ERROR">
                            </html:text>
                        </center>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="color:firebrick">
                        <html:errors property="codigo"/>
                    </td>
                </tr>
                <tr style="height: 35px;">
                    <td style="color: black; font-size: 14px; font-weight: bold;">
                        NOMBRE DE LA COORDINACIÓN
                    </td>
                </tr>
                <tr>
                    <td>
                        <center>
                            <html:text property="nombre" name="Coordinacion" maxlength="50" 
                                       styleClass="input-nombre" style="height: 30px;" 
                                       errorKey="org.apache.struts.action.ERROR"/>
                        </center>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="color:firebrick">
                        <html:errors property="nombre"/>
                    </td>
                </tr>
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