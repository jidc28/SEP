<%-- 
    Document   : listarEvaluacionesPendientes
    Created on : Dec 10, 2013, 5:45:12 PM
    Author     : smaf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<h4>Evaluaciones Pendientes
    <div style="font-size: 14px; color: grey;">
        <bean:write name="profesor" property="apellido"/>,
        <bean:write name="profesor" property="nombre"/>
    </div>
</h4>

<logic:empty name="evaluaciones_pendientes">
    <div class="alert alert-warning alert-dismissable" 
         id="alert-coord">
        <p>
            En este momento no existen evaluaciones sin comentar.
        </p>
    </div>     
</logic:empty>

<logic:notEmpty name="evaluaciones_pendientes">
    <table style="margin: 0px; margin-top: 20px; margin-left: 20px;" align="left">
        <tbody>
            <logic:iterate id="evaluacion" name="evaluaciones_pendientes">
                <tr>
                    <td style="padding: 0px; color: #999; font-size: 14px;">
                        <bean:write name="evaluacion" property="codigoMateria"/>
                    </td>
                    <td style="padding: 5px; font-size: 14px; padding-left: 15px;">
                        <bean:write name="evaluacion" property="opcion"/>
                    </td>
                    <td>
                        <img src="imagenes/chart-icon.png" style="width: 20px; height: 20px;">
                    </td>
                    <td>
                        <html:form action="/graficarRendimiento"
                                   style="margin: 0px;">
                            <html:hidden name="dicta" property="usbidProfesor"
                                         value="${evaluacion.getPrimerProfesor().getUsbid()}"/>
                            <html:hidden name="dicta" property="codigoMateria"
                                         value="${evaluacion.getCodigoMateria()}"/>
                            <html:submit styleClass="link2"
                                         style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                Ver rendimiento
                            </html:submit>
                        </html:form>
                    </td>
                    <td>
                        <html:form action="/hacerEvaluacion" 
                                   onsubmit="return(this)"
                                   style="margin: 0px;">
                            <html:hidden name="dicta" 
                                         property="codigoMateria"
                                         value="${evaluacion.getCodigoMateria()}"/>
                            <html:hidden name="dicta"
                                         property="usbidProfesor"
                                         value="${evaluacion.getPrimerProfesor().getUsbid()}"/>
                            <html:hidden name="dicta"
                                         property="opcion"
                                         value="pendiente"/>
                            <html:submit styleClass="link2"
                                         style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                Evaluar
                            </html:submit>
                        </html:form>
                    </td>
                </tr>
            </logic:iterate>
        </tbody>
    </table>
</logic:notEmpty>