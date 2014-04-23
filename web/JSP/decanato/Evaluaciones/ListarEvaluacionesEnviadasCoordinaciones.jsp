<%-- 
    Document   : ListarEvaluacionesEnviadasCoordinaciones
    Created on : Apr 17, 2014, 5:07:23 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4>  
    Listado de Coordinaciones
</h4>

<table style="margin: 0px; margin-top: 20px; margin-left: 20px; width: 90%;" align="left">
    <tbody>
        <logic:iterate id="coordinacion" name="coordinaciones">
            <tr>
                <td style="padding: 0px; color: #999; font-size: 14px;">
                    <bean:write name="coordinacion" property="codigo"/>
                </td>
                <td>      
                    <html:form action="/consultarProfesoresEvaluados" style="margin: 0px;">
                        <html:hidden name="Coordinacion" property="codigo"
                                     value="${coordinacion.getCodigo()}"/>
                        <html:submit styleClass="link2" style="padding-left: 0px;">
                            <bean:write name="coordinacion" property="nombre"/>
                        </html:submit>
                    </html:form>
                </td>
            </tr>
        </logic:iterate>
    </tbody>
</table>

