<%-- 
    Document   : EditarCoordinacion
    Created on : 01/10/2013, 06:16:42 PM
    Author     : Langtech
--%>

<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String tipousuario = usuario.getTipousuario();
    if (tipousuario.equals("administrador") || tipousuario.equals("decanato")) {
%>
<html:form action="/cambiarNombreCoordinacionA" acceptCharset="ISO-8859-1" 
           onsubmit="return(this)">
    <html:hidden name="Coordinacion" property="codigo"/>
    <h4>
        Modificar Coordinación
        <div style="font-size: 14px; color: grey;">
            <bean:write name="Coordinacion" property="codigo"/> -
            <bean:write name="Coordinacion" property="nombre"/>
        </div>
    </h4>
    <br>
    <table border="0" style="margin-top: 0px;">
        <tbody>
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
        <html:errors property="registro"/>
    </td>
</tr>
<tr>
    <td colspan="2" style="color:firebrick">
        <html:errors property="nombre"/>
    </td>
</tr>
</tbody>
</table>
<html:submit styleClass="btn btn-success"
             onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
    Modificar
</html:submit>
</html:form>                 
<%} else {
%>
<tiles:insert definition="noAutorizado"/>
<%    }
%>