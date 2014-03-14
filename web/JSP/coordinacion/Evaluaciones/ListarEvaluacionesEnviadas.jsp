<%-- 
    Document   : ListarEvaluacionesEnviadas
    Created on : Mar 14, 2014, 8:12:55 AM
    Author     : smaf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<h4>Evaluaciones Pendientes</h4>
<center>
    
<logic:empty name="evaluaciones_enviadas">
    <div class="alert alert-warning alert-dismissable" 
        id="alert-coord">
<!--        <a href="#" id="ayuda1" style="color: #c09853; float: right" rel="popover" > 
                <span style="color: #c09853;" class="glyphicon glyphicon-question-sign">     
                </span> 
        </a>-->
        <p>
            En este momento no existen evaluaciones pendientes.
        </p>
    </div>     
</logic:empty>
<logic:notEmpty name="evaluaciones_enviadas">
    <div class="table-responsive" id="tabla">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>
                        <center>
                            MATERIA
                        </center>
                    </th>
                    <th>
                        <center>
                            NOMBRE
                        </center>
                    </th>
                    <th>
                        <center>
                            POR MATERIA
                        </center>
                    </th>
                    <th>
                        <center>
                            POR PROFESOR
                        </center>
                    </th>
                </tr>
            </thead>
            <tbody>
                <logic:present name="evaluaciones_enviadas">
                    <logic:iterate name="evaluaciones_enviadas" id="evaluacion">
                        <tr>
                            <td>
                                <bean:write name="evaluacion" property="codigo_materia" />
                            </td>
                            <td>
                                <bean:write name="evaluacion" property="nombre_materia" />
                            </td>
                            <td>
                                <center>
                                    <html:link action="/graficarRendimiento">
                                            Ver rendimiento
                                    </html:link>
                                </center>
                            </td>
                            <td>
                                <center>
                                    <html:form action="/hacerEvaluacion" 
                                        onsubmit="return(this)"
                                        style="margin: 0px;">
                                        <%--<html:hidden name="dicta" 
                                            property="codigoMateria"
                                            value="${evaluacion.getCodigoMateria()}"/>
                                        <html:hidden name="dicta"
                                            property="usbidProfesor"
                                            value="${evaluacion.getPrimerProfesor().getUsbid()}"/>--%>
                                        <html:submit styleClass="btn btn-info"
                                                     style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                                Hacer evaluación
                                        </html:submit>
                                    </html:form>
                                </center>
                            </td>
                        </tr>
                </logic:iterate>
            </logic:present>
            </tbody>
        </table>
    </div>
</logic:notEmpty>
</center>