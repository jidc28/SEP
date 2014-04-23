<%-- 
    Document   : EditarDepartamento
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html:form action="/editarDepartamento" acceptCharset="ISO-8859-1" 
           onsubmit="return(this)">
    <html:hidden name="departamento" property="codigo"/>
    <h4><bean:write name="departamento" property="nombre"/></h4>
    <h4>(<bean:write name="departamento" property="codigo"/>)</h4>
    <br>
    <table border="0" style="margin-top: 0px;">
        <tbody>
            <tr style="height: 35px;">
                <td style="color: black; font-size: 14px; font-weight: bold;">
                    NOMBRE DEL DEPARTAMENTO
                </td>
            </tr>
            <tr>
                <td>
                    <center>
                        <html:text property="nombre" name="departamento" maxlength="50" 
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
        </tbody>
    </table>
    <html:submit styleClass="btn btn-success"
                 onclick="javascript: return confirm('¿Está seguro de que los datos son correctos?')">
        Modificar
    </html:submit>
</html:form>
<br>