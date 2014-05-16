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
    Listado de evaluaciones enviadas
    <div style="font-size: 14px; color: grey;">
        <bean:write name="profesor" property="apellido"/>,
        <bean:write name="profesor" property="nombre"/>
    </div>
</h4>

<table style="margin: 0px; margin-top: 20px; margin-left: 20px;" align="left">
    <tbody>
        <logic:iterate id="evaluacion" name="evaluaciones">
            <tr>
                <td style="padding: 5px;">
                    <html:form action="/listarEvaluacionesEnviadas" style="margin: 0px;">
                        <html:hidden name="evaluacion" property="usbid_profesor" 
                                     value="${profesor.getUsbid()}"/>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Septiembre-Diciembre">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="SD"/>
                        </logic:equal>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Enero-Marzo">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="EM"/>
                        </logic:equal>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Abril-Julio">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="AJ"/>
                        </logic:equal>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Intensivo">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="V"/>
                        </logic:equal>
                        <html:hidden name="evaluacion" property="ano"/>
                        <html:submit styleClass="link2">
                            Evaluaciones de <bean:write name="evaluacion" property="trimestre"/> <bean:write name="evaluacion" property="ano"/>
                        </html:submit>
                    </html:form>
                </td>
                <td style="padding: 5px;">
                    <html:form action="/descargarPDF" style="margin: 0px;">
                        <html:hidden name="evaluacion" property="usbid_profesor" 
                                     value="${profesor.getUsbid()}"/>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Septiembre-Diciembre">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="SD"/>
                        </logic:equal>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Enero-Marzo">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="EM"/>
                        </logic:equal>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Abril-Julio">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="AJ"/>
                        </logic:equal>
                        <logic:equal name="evaluacion" property="trimestre"
                                     value="Intensivo">
                            <html:hidden name="evaluacion" property="trimestre"
                                         value="V"/>
                        </logic:equal>
                        <html:hidden name="evaluacion" property="ano"/>
                        <html:submit styleClass="btn btn-danger">
                            Descargar PDF
                        </html:submit>
                    </html:form>
                </td>
            </tr>
        </logic:iterate>
    </tbody>
</table>
