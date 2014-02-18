<%-- 
    Document   : VerPlanillasLlenas
    Created on : 08/01/2014, 06:08:41 PM
    Author     : jidc28
--%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="planilla_modificada">
    <div class="alert alert-success" id="alert">
        La planilla de la materia
        <bean:write name="planilla_modificada" property="codigo_materia"/>
        se ha guardado exitosamente.
    </div>  
</logic:present> 

<h4>
    Planillas llenas de: <bean:write name="profesor" property="usbid" />
    <div style="font-size: 14px; color: grey;">
        <bean:write name="profesor" property="nombre"/>
    </div>
</h4>   

<div id="tabla" class="table-responsive">
    <table class="table table-striped">
        <thead>
            <th style="font-size: 14px;">
                <center>TRIMESTRE</center>
            </th>
            <th style="font-size: 14px;">
                <center> AÑO </center>
            </th>
            <th style="font-size: 14px;">
                <center> CÓDIGO </center>
            </th>
            <th style="font-size: 14px;">
                <center> VER DETALLES </center>
            </th>
        </thead>
        <tbody>
            <logic:iterate name="rendimiento" id="RendimientoProf" >
                <tr>
                    <td>
                        <center>
                            <logic:equal name="RendimientoProf" 
                                         property="trimestre" value="EM">
                                Ene-Mar
                            </logic:equal>
                            <logic:equal name="RendimientoProf" 
                                         property="trimestre" value="AJ">
                                Abr-Jul
                            </logic:equal>
                            <logic:equal name="RendimientoProf" 
                                         property="trimestre" value="V">
                                Intensivo
                            </logic:equal>
                            <logic:equal name="RendimientoProf" 
                                         property="trimestre" value="SD">
                                Sep-Dic
                            </logic:equal>
                        </center>
                    </td>
                    <td>
                        <center>
                            <bean:write name="RendimientoProf" property="ano"/>
                        </center>
                    </td>
                    <td style="text-align: left;">
                        <bean:write name="RendimientoProf" property="codigo_materia"/>,
                        <bean:write name="RendimientoProf" property="nombre_materia"/>
                    </td>
                    <td>
                        <center>
                            <html:form action="/verPlanilla" style="margin: 0px;">
                                <html:hidden name="RendimientoProf" 
                                             property="codigo_materia"/>
                                <html:submit styleClass="btn btn-info"
                                             style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                    Ver detalles
                                </html:submit>
                            </html:form>
                        </center>
                    </td>
                </tr>
        </logic:iterate>
        </tbody>
    </table>
</div>