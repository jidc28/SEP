<%-- 
    Document   : ListarEvaluacionesProfesor
    Created on : Apr 28, 2014, 1:38:56 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4>  
    Listado de evaluaciones pendientes
</h4>

<table style="margin: 0px; margin-top: 20px; margin-left: 20px;" align="left">
    <tbody>
        <logic:iterate id="profesor" name="profesores">
            <tr>
                <td style="padding: 0px; color: #999; font-size: 14px;">
                    <bean:write name="profesor" property="usbid"/>
                </td>
                <td style="padding: 5px; font-size: 14px; padding-left: 15px;">
                    <bean:write name="profesor" property="apellido"/>, <bean:write name="profesor" property="nombre"/>
                </td>
                <td>
                    <html:form action="/listarEvaluacionesPendientesGeneral" style="margin: 0px;">
                        <html:hidden name="dicta" property="usbidProfesor" 
                                     value="${profesor.getUsbid()}"/>
                        <html:hidden name="dicta" property="opcion" 
                                     value="pendiente"/>
                        <html:submit styleClass="link2" style="padding-left: 15px;">
                            Evaluación General
                        </html:submit>
                    </html:form>
                </td>
                <td>
                    <html:form action="/listarEvaluacionesPendientes" style="margin: 0px;">
                        <html:hidden name="Coordinacion" property="codigo" 
                                     value="${profesor.getUsbid()}"/>
                        <html:submit styleClass="link2" style="padding-left: 15px;">
                            Evaluación Específica
                        </html:submit>
                    </html:form>
                </td>
            </tr>
        </logic:iterate>
    </tbody>
</table>