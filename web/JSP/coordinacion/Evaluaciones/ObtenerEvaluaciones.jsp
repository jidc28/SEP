<%-- 
    Document   : obtenerEvaluaciones
    Created on : Feb 15, 2014, 9:25:21 AM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="recomendar">
    <div class="alert alert-danger" id="alert">
        Debe seleccionar si recomienda o no al profesor.
    </div>
</logic:present>

<h4>Evaluación del profesor: 
    <bean:write name="profesor" property="apellido"/>,
    <bean:write name="profesor" property="nombre"/>
</h4>

<div style="width: 95%">
    <ul class="nav nav-tabs" style="height: 55px;">
        <li>
            <a href="#departamento" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                Información proporcionada <br> 
                por el departamento
            </a>
        </li>
        <li>
            <a href="#profesor" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                Información proporcionada<br>
                por el profesor
            </a>
        </li>
        <logic:present name="evaluar_coordinacion">
            <li>
                <a href="#coordinacion" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                    Información proporcionada<br>
                    por la coordinación
                </a>
            </li>
        </logic:present>
        <logic:present name="evaluado_coordinacion">
            <li>
                <a href="#coordinacion" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                    Información proporcionada<br>
                    por la coordinación
                </a>
            </li>
        </logic:present>
        <logic:present name="evaluado_coordinacion">
            <li>
                <a href="#evaluacion_coordinacion" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                    Información sobre la <br>
                    evaluación
                </a>
            </li>
        </logic:present>
        <logic:present name="evaluacion_departamento">
            <li>
                <a href="#evaluacion_departamento" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                    Evaluaciones de las <br>
                    coordinaciones
                </a>
            </li>
        </logic:present>
    </ul>
</div>


<div class="tab-content">
    <div class="tab-pane" id="profesor">
        <logic:empty name="archivos">
            <div class="alert alert-danger alert-dismissable" 
                 id="alert-coord" style="text-align: left; margin-top: 30px;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                    &times;
                </button>
                <p style="font-weight: normal;">
                    <strong>Atención:</strong> <br> 
                    El profesor no ha subido ningún archivo. Presione <em>
                        Enviar notificación </em> si desea notificarle que debe 
                    cargar el material realizado al <em>Sistema de Evaluación
                        de Profesores</em>
                </p>
                <button class="btn btn-danger"style="margin-top: 5px;">
                    Enviar notificación
                </button>
            </div>
        </logic:empty>
        <logic:notEmpty name="archivos">
            <div id="tabla" class="table-responsive" style="width: 85%;">
                <table class="table table-striped" style=" margin-top: 40px;">
                    <tbody>
                        <logic:iterate name="archivos" id="archivo">
                            <tr>
                                <td style="font-size: 14px; font-weight: bold;">
                                    <span class="glyphicon glyphicon-file"></span>
                                    <bean:write name="archivo" property="nombre"/>
                                    <br>
                                    <blockquote style="margin: 0px;">
                                        <p style="font-size: 11px; color: gray;">
                                            <bean:write name="archivo" property="descripcion"/>
                                        </p>
                                    </blockquote>
                                    <div style="text-align: right;">
                                        <html:form  action="/descargarDocumentoSeleccionado" 
                                                    style="margin:0px;">
                                            <html:hidden name="archivo" property="nombre"/>
                                            <html:hidden name="archivo" property="usbidProfesor"/>
                                            <html:hidden name="archivo" property="trimestre"/>
                                            <html:hidden name="archivo" property="ano"/>
                                            <html:submit styleClass="btn btn-info"
                                                         style="padding-top: 4px; padding-bottom: 3px;">
                                                Descargar
                                            </html:submit>
                                        </html:form>
                                    </div>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </table>
            </div>
        </logic:notEmpty>            
    </div>

    <logic:present name="evaluar_coordinacion">    
        <div class="tab-pane" id="coordinacion" style="margin-bottom: 40px;">
            <center>
                <form id="informacion_coordinacion" name="informacion_coordinacion" 
                      action="guardarInformacionCoordinacion.do">
                    <table  style="width: 90%;">
                        <tbody>
                        <div class="form-group">
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        ¿HA SIDO MIEMBRO DE CONSEJO ASESOR?
                                    </strong>
                                </td>
                                <td>
                                    <div style="padding-left: 15px;" >
                                        <div class="radio">
                                            <html:radio name="informacion" property="consejoAsesor"
                                                        value="si" onchange="update()">
                                                Si
                                            </html:radio>
                                        </div>
                                        <div class="radio" style="margin-bottom: 0px;">
                                            <html:radio name="informacion" property="consejoAsesor"
                                                        value="no" onchange="update()">
                                                No
                                            </html:radio>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PROYECTOS DE GRADO, TRABAJOS DE GRADO O TESIS DOCTORALES TUTOREADOS
                                    </strong>
                                </td>
                                <td>
                                    <div class="col-xs-6">
                                        <html:text styleClass="form-control" name="informacion" property="tesisTutoria" onchange="update()">
                                        </html:text>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PROYECTOS DE GRADO, TRABAJOS DE GRADO O TESIS DOCTORALES EVALUADAS COMO JURADO
                                    </strong>
                                </td>
                                <td>
                                    <div class="col-xs-6">
                                        <html:text styleClass="form-control" name="informacion" property="tesisJurado" onchange="update()">
                                        </html:text>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PASANTÍAS CORTAS TUTOREADAS
                                    </strong>
                                </td>
                                <td>
                                    <div class="col-xs-6">
                                        <html:text styleClass="form-control" name="informacion" property="pasantiaCorta" onchange="update()">
                                        </html:text>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PASANTÍAS LARGAS E INTERMEDIAS TUTOREADAS
                                    </strong>
                                </td>
                                <td>
                                    <div class="col-xs-6">
                                        <html:text styleClass="form-control" name="informacion" property="pasantiaLargaTutor" onchange="update()">
                                        </html:text>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PASANTÍAS LARGAS E INTERMEDIAS EVALUADAS COMO JURADO
                                    </strong>
                                </td>
                                <td>
                                    <div class="col-xs-6">
                                        <html:text styleClass="form-control" name="informacion" property="pasantiaLargaJurado" onchange="update()">
                                        </html:text>
                                    </div>
                                </td>
                            </tr>
                        </div>
                        </tbody>
                    </table>
                </form>
            </center>                
        </div>
    </logic:present>

    <logic:present name="evaluado_coordinacion">
        <div class="tab-pane" id="coordinacion" style="margin-bottom: 40px;">
            <center>
                <form id="informacion_coordinacion" name="informacion_coordinacion" 
                      action="guardarInformacionCoordinacion.do">
                    <table  style="width: 90%;">
                        <tbody>
                        <div class="form-group">
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        ¿HA SIDO MIEMBRO DE CONSEJO ASESOR?
                                    </strong>
                                </td>
                                <td>
                                    <div style="padding-left: 15px; font-size: 14px; color: #444444">
                                        <strong>
                                            <bean:write name="informacion" property="consejoAsesor"/>
                                        </strong>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PROYECTOS DE GRADO, TRABAJOS DE GRADO O TESIS DOCTORALES TUTOREADOS
                                    </strong>
                                </td>
                                <td style="padding-left: 15px; font-size: 14px; color: #444444;">
                                    <strong>
                                        <bean:write name="informacion" property="tesisTutoria"/>
                                    </strong>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PROYECTOS DE GRADO, TRABAJOS DE GRADO O TESIS DOCTORALES EVALUADAS COMO JURADO
                                    </strong>
                                </td>
                                <td style="padding-left: 15px; font-size: 14px; color: #444444">
                                    <strong>
                                        <bean:write name="informacion" property="tesisJurado"/>
                                    </strong>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PASANTÍAS CORTAS TUTOREADAS
                                    </strong>
                                </td>
                                <td style="padding-left: 15px; font-size: 14px; color: #444444">
                                    <strong>
                                        <bean:write name="informacion" property="pasantiaCorta"/>
                                    </strong>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PASANTÍAS LARGAS E INTERMEDIAS TUTOREADAS
                                    </strong>
                                </td>
                                <td style="padding-left: 15px; font-size: 14px; color: #444444">
                                    <strong>
                                        <bean:write name="informacion" property="pasantiaLargaTutor"/>
                                    </strong>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 70%; font-size: 14px; height: 40px;">
                                    <strong>
                                        NÚMERO DE PASANTÍAS LARGAS E INTERMEDIAS EVALUADAS COMO JURADO
                                    </strong>
                                </td>
                                <td style="padding-left: 15px; font-size: 14px; color: #444444">
                                    <strong>
                                        <bean:write name="informacion" property="pasantiaLargaJurado"/>
                                    </strong>
                                </td>
                            </tr>
                        </div>
                        </tbody>
                    </table>
                </form>
            </center>                
        </div>
    </logic:present>

    <logic:present name="evaluado_coordinacion">
        <div class="tab-pane" id="evaluacion_coordinacion">
            <table style="width: 80%; word-break: break-all;">
                <tbody>
                    <tr>
                        <td style="width: 70%; font-size: 14px; height: 40px;">
                            <strong>
                                <logic:equal name="evaluado_coordinacion" property="recomendado" value="no">
                                    El profesor no fue recomendado.
                                </logic:equal>
                                <logic:equal name="evaluado_coordinacion" property="recomendado" value="si">
                                    El profesor fue recomendado.
                                </logic:equal>
                            </strong>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 70%; font-size: 14px; height: 40px;">
                            <strong>
                                Observaciones
                            </strong>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>
                                <blockquote>
                                    <p style="font-size: 12px;">
                                        <bean:write name="evaluado_coordinacion" property="observaciones_c"/>
                                    </p>
                                </blockquote>
                            </strong>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </logic:present>

    <logic:present name="evaluacion_departamento">
        <div class="tab-pane" id="evaluacion_departamento">
            <table style="word-break: break-all; width: 80%">
                <tbody>
                    <logic:iterate name="evaluacion_departamento" id="evaluacion_coordinacion">
                        <tr>
                            <td>
                                <p style="font-size: 14px;">
                                    La
                                    <strong>
                                        <bean:write name="evaluacion_coordinacion" 
                                                    property="observaciones_d"/>
                                    </strong>
                                    <logic:equal name="evaluacion_coordinacion" 
                                                 property="recomendado"
                                                 value="si">
                                        recomendó al profesor
                                    </logic:equal>
                                    <logic:equal name="evaluacion_coordinacion" 
                                                 property="recomendado"
                                                 value="no">
                                        no recomendó al profesor
                                    </logic:equal>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <blockquote>
                                    <p style="font-size: 12px;">
                                        <bean:write name="evaluacion_coordinacion" property="observaciones_c"/>
                                    </p>
                                </blockquote>
                            </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </div>
    </logic:present>

    <div class="tab-pane" id="departamento">
        <h3 style="text-align: left; font-size: 18px; margin-left: 30px;">
            Información general: 
        </h3>
        <div id="tabla" class="table-responsive" style="margin-top: 20px;">
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>TOTAL ESTUDIANTES</strong>
                        </td>
                        <td>
                            <bean:write name="evaluacion" property="total_estudiantes"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>NOTA PROMEDIO</strong>
                        </td>
                        <td>
                            <bean:write name="evaluacion" property="nota_prom"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE APLAZADOS</strong>
                        </td>
                        <td>
                            <bean:write name="aplazados"/>%
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE APROBADOS</strong>
                        </td>
                        <td>
                            <bean:write name="aprobados"/>%
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE RETIRADOS  </strong>
                        </td>
                        <td>
                            <bean:write name="retirados"/>%
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <h3 style="text-align: left; font-size: 18px; margin-left: 30px;">
            Información específica: 
        </h3>
        <div id="tabla" class="table-responsive" style="margin-top: 20px;">
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE ESTUDIANTES CON 1</strong>
                        </td>
                        <td>
                            <bean:write name="porcentaje1"/>%
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE ESTUDIANTES CON 2</strong>
                        </td>
                        <td>
                            <bean:write name="porcentaje2"/>%
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 40%; font-size: 14px;">
                            <strong>PORCENTAJE ESTUDIANTES CON 3</strong>
                        </td>
                        <td>
                            <bean:write name="porcentaje3"/>%
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE ESTUDIANTES CON 4</strong>
                        </td>
                        <td>
                            <bean:write name="porcentaje4"/>%
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE ESTUDIANTES CON 5</strong>
                        </td>
                        <td>
                            <bean:write name="porcentaje5"/>%
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 50%; font-size: 14px;">
                            <strong>PORCENTAJE ESTUDIANTES RETIRADOS</strong>
                        </td>
                        <td>
                            <bean:write name="retirados"/>%
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<logic:present name="evaluar">
    <div class="recomendar">
        <html:form action="/evaluar">
            <html:hidden name="evaluacion" property="usbid_profesor" value="${profesor.getUsbid()}"/>
            <html:hidden name="evaluacion" property="codigo_materia"/>
            <table class="table" style="border-top: none; margin: 0px;">
                <tbody>
                    <tr>
                        <td style="border: none; font-size: 14px;">
                            <html:checkbox style="margin-right: 5px;" 
                                           name="evaluacion" property="recomendado"
                                           value="si"/>
                            Recomendado
                        </td>
                    </tr>
                    <tr>
                        <td style="border: none;  font-size: 14px;">
                            <html:checkbox style="margin-right: 5px;" 
                                           name="evaluacion" property="recomendado"
                                           value="no"/>
                            No recomendado
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="border: none;">
                            <html:textarea name="evaluacion" property="observaciones_c"
                                           styleClass="form-control" rows="5">
                            </html:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="border: none;">
                <center>
                    <html:submit onclick="guardar_informacion()" 
                                 styleClass="btn btn-success">
                        Evaluar
                    </html:submit>
                </center>
                </td>
                </tr>
                </tbody>
            </table>
        </html:form>
    </div>
</logic:present>

<logic:present name="revisar">
    <html:form action="/revisarEvaluacion">
        <html:hidden name="evaluacion" property="usbid_profesor" value="${profesor.getUsbid()}"/>
        <html:hidden name="evaluacion" property="codigo_materia"/>
        <html:submit styleClass="btn btn-success">
            Revisado
        </html:submit>
    </html:form>
</logic:present>