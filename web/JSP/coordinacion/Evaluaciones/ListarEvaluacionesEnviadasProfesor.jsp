<%-- 
    Document   : ListarEvaluacionesEnviadasProfesor
    Created on : Mar 12, 2014, 11:53:01 AM
    Author     : smaf
--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4>  
    Listado de evaluaciones enviadas
</h4>

<table style="margin: 0px; margin-top: 20px; margin-left: 20px;">
    <tbody>
        <logic:iterate id="profesor" name="profesores">
            <tr>
                <td style="padding: 0px; color: #999; font-size: 14px;">
                    <bean:write name="profesor" property="usbid"/>
                </td>
                <td style="padding: 5px;">
                    <html:form action="/listarAnosEvaluados" style="margin: 0px;">
                        <html:hidden name="profesor" property="usbid"/>
                        <html:submit styleClass="link2">
                            <bean:write name="profesor" property="apellido"/>, <bean:write name="profesor" property="nombre"/>
                        </html:submit>  
                    </html:form>
                </td>
            </tr>
        </logic:iterate>
    </tbody>
</table>
