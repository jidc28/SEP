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

<h4>    
    Listado de evaluaciones enviadas
    <div style="font-size: 14px; color: grey;">
        <bean:write name="profesor" property="apellido"/>,
        <bean:write name="profesor" property="nombre"/>
    </div>
</h4>
    
<center>
    <logic:empty name="evaluaciones_enviadas">
        <div class="alert alert-warning alert-dismissable" 
            id="alert-coord">
            <p>
                En este momento no existen evaluaciones enviadas.
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
                                        <html:form action="/graficarRendimiento"
                                                   style="margin: 0px;">
                                            <html:hidden name="dicta" property="opcion"
                                                         value="enviada"/>
                                            <html:hidden name="dicta" property="codigoMateria"
                                                         value="${evaluacion.getCodigo_materia()}"/>
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
                                                value="${evaluacion.getCodigo_materia()}"/>
                                            <html:hidden name="dicta"
                                                property="usbidProfesor"
                                                value="${evaluacion.getUsbid_profesor()}"/>
                                            <html:hidden name="dicta"
                                                         property="opcion"
                                                         value="enviada"/>
                                            <html:submit styleClass="btn btn-info"
                                                         style="margin: 0px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                                    Ver evaluación
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