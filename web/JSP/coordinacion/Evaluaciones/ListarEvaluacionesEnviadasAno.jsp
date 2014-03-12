<%-- 
    Document   : ListarEvaluacionesEnviadasAno
    Created on : Mar 12, 2014, 3:47:51 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4>  
    Evaluaciones del profesor:
    <bean:write name="profesor" property="apellido"/>,
    <bean:write name="profesor" property="nombre"/>
</h4>

<table style="margin: 0px; margin-top: 20px; margin-left: 20px;" align="left">
    <tbody>
        <logic:iterate id="ano" name="anos">
            <tr>
                <td style="padding: 5px;">
                    <html:form action="/listarAnosEvaluados">
                        <html:hidden name="Profesor" property="usbid" 
                                     value="${profesor.getUsbid()}"/>
                        <html:submit styleClass="link2">
                            Evaluaciones del año <bean:write name="ano"/>
                        </html:submit>  
                        <span style="color: #428bca;" 
                              class="glyphicon glyphicon-arrow-right">
                        </span>
                    </html:form>
                </td>
            </tr>
        </logic:iterate>
    </tbody>
</table>
