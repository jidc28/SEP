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

<h4>Evaluaciones Pendientes</h4>
<center>
    
<logic:empty name="evaluaciones_pendientes">
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
<logic:notEmpty name="evaluaciones_pendientes">
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
                            USBID
                        </center>
                    </th>
                    <th>
                        <center>
                            PROFESOR
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
                <logic:present name="evaluaciones_pendientes">
                    <logic:iterate name="evaluaciones_pendientes" id="evaluacion">
                        <tr>
                            <td rowspan="<bean:write name="evaluacion" 
                                        property="numeroMateria"/>">
                                <center>
                                    <bean:write name="evaluacion" 
                                                property="codigoMateria"/>
                                </center> 
                            </td>
                            <td>
                                <center>
                                    <bean:write name="evaluacion" 
                                                property="primerProfesor.usbid"/>
                                </center>
                            </td>
                            <td>
                                <bean:write name="evaluacion" 
                                            property="primerProfesor.apellido"/>,
                                <bean:write name="evaluacion" 
                                            property="primerProfesor.nombre"/>
                            </td>
                            <td>
                                <center>
                                    <html:form action="/graficarRendimiento"
                                               style="margin: 0px;">
                                        <html:hidden name="dicta" property="usbidProfesor"
                                                     value="${evaluacion.getPrimerProfesor().getUsbid()}"/>
                                        <html:hidden name="dicta" property="codigoMateria"
                                                     value="${evaluacion.getCodigoMateria()}"/>
                                        <html:submit styleClass="btn btn-default"
                                                     style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                                Ver rendimiento
                                        </html:submit>
                                    </html:form>
                                </center>
                            </td>
                            <td>
                                <center>
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
                                        <logic:notPresent name="solo_lectura">
                                            <html:submit styleClass="btn btn-info"
                                                         style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                                    Hacer evaluación
                                            </html:submit>
                                        </logic:notPresent>
                                        <logic:present name="solo_lectura">
                                            <html:submit styleClass="btn btn-info"
                                                     style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                                Ver evaluación
                                            </html:submit>
                                        </logic:present>
                                    </html:form>
                                </center>
                            </td>
                        </tr>
                    <logic:iterate name="evaluacion" property="profesores" 
                                   id="profesores">
                        <tr>
                        <td>
                        <center>
                            <bean:write name="profesores" property="usbid"/>
                        </center>
                        </td>
                        <td>
                            <bean:write name="profesores" property="apellido"/>,
                            <bean:write name="profesores" property="nombre"/>
                        </td>
                        <td>
                                <center>
                                    <html:form action="/graficarRendimiento"
                                               style="margin: 0px;">
                                        <html:hidden name="dicta" property="usbidProfesor"
                                                     value="${profesores.getUsbid()}"/>
                                        <html:hidden name="dicta" property="codigoMateria"
                                                     value="${evaluacion.getCodigoMateria()}"/>
                                        <html:submit styleClass="btn btn-default"
                                                     style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                                Ver rendimiento
                                        </html:submit>
                                    </html:form>
                                </center>
                        </td>
                        <td>
                            <center>
                                <html:form action="/hacerEvaluacion" 
                                    onsubmit="return(this)"
                                    style="margin: 0px;">
                                    <html:hidden name="dicta" 
                                        property="codigoMateria"
                                        value="${evaluacion.getCodigoMateria()}"/>
                                    <html:hidden name="dicta"
                                        property="usbidProfesor"
                                        value="${profesores.getUsbid()}"/>
                                    <html:hidden name="dicta"
                                                     property="opcion"
                                                     value="pendiente"/>
                                    <logic:notPresent name="solo_lectura">
                                        <html:submit styleClass="btn btn-info"
                                                     style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                                Hacer evaluación
                                        </html:submit>
                                    </logic:notPresent>
                                    <logic:present name="solo_lectura">
                                        <html:submit styleClass="btn btn-info"
                                                 style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                            Ver evaluación
                                        </html:submit>
                                    </logic:present>
                                </html:form>
                            </center>
                        </td>
                        </tr>
                    </logic:iterate>
                </logic:iterate>
            </logic:present>
            </tbody>
        </table>
    </div>
</logic:notEmpty>
</center>