<%-- 
    Document   : llenarPlanillas
    Created on : 08/01/2014, 06:08:41 PM
    Author     : jidc28
--%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="alert alert-info alert-dismissable" 
     id="alert">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
        &times;
    </button>
    <p>
        <strong>Atención: </strong> <br> 
        Para realizar el llenado del formulario debe tener a mano
        <strong>actas del trimestre correspondiente.</strong>
    </p>
</div>

<logic:present name="numero_negativo">
    <div class="alert alert-danger" id="alert">
        Los datos insertados <strong> no </strong> pueden ser negativos.
    </div>  
</logic:present>

<logic:present name="planilla_guardada">
    <div class="alert alert-success" id="alert">
        La planilla de la materia
        <bean:write name="planilla_guardada" property="codigo_materia"/>
        se ha guardado exitosamente.
    </div>  
</logic:present>        

<logic:present name="planilla_no_guardada">
    <div class="alert alert-danger" id="alert">
        La planilla de la materia
        <bean:write name="planilla_no_guardada" property="codigo_materia"/>
        no se ha podido guardado correctamente.
    </div>  
</logic:present> 

<h4>
    Llenar Planillas:
</h4>

<br style="font-size: 14px;">
<strong> PROFESOR </strong> 
<html:text disabled="true" name="profesor" property="nombre"
           style="height: 30px; margin: 0px; text-align: center;"/>
<strong> USBID </strong> 
<html:text disabled="true" name="profesor" property="usbid"
           style="height: 30px; margin: 0px; text-align: center;"/>
</br>

<logic:present name="materias">
    <div class="panel-group" id="accordion" style="width: 90%">
        <logic:iterate name="materias" id="materia">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <table style="margin: 0px; width: 100%">
                        <tbody>
                            <tr>
                                <td>
                                    <h4 id="izquierda" class="panel-title" style="text-align: left; width: 100%">
                                        <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#${materia.getCodigo()}_${materia.getPeriodo()}">
                                            <bean:write name="materia" property="codigo"/>
                                            <div style="font-size: 14px; color: grey;">
                                                <bean:write name="materia" property="nombre"/>
                                            </div>
                                        </a>
                                    </h4>
                                </td>
                                <td>
                                    <h4 id="izquierda" class="panel-title" style="text-align: left; width: 100%">
                                        <div style="font-size: 14px; color: grey; text-align: right;">
                                            (<bean:write name="materia" property="periodo"/>)
                                        </div>
                                    </h4>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id='${materia.getCodigo()}_${materia.getPeriodo()}' class="panel-collapse collapse">
                    <div class="panel-body">
                        <logic:present name="error_num_estudiantes">
                            <logic:equal name="error_num_estudiantes" property="codigo_materia" value="${materia.getCodigo()}">
                                <logic:equal name="error_num_estudiantes" property="trimestre" value="${materia.getPeriodo()}">
                                    <div class="alert alert-danger" id="alert" style="font-size: 12px;"> 
                                        El número total de estudiantes
                                        (<bean:write name="error_num_estudiantes" property="total_estudiantes" />)
                                        no coincide con la suma del número de estudiantes aplazados, el 
                                        número de estudiantes aprobados y el número de estudiantes
                                        retirados 
                                        <br>
                                        (<bean:write name="error_num_estudiantes" property="nota1" /> +
                                        <bean:write name="error_num_estudiantes" property="nota2" /> + 
                                        <bean:write name="error_num_estudiantes" property="nota3" /> +
                                        <bean:write name="error_num_estudiantes" property="nota4" /> +
                                        <bean:write name="error_num_estudiantes" property="nota5" /> +
                                        <bean:write name="error_num_estudiantes" property="retirados" />)
                                    </div>
                                </logic:equal>
                            </logic:equal>
                        </logic:present>
                        <logic:present name="agregar_informacion">
                            <logic:equal name="agregar_informacion" property="codigo_materia" value="${materia.getCodigo()}">
                                <logic:equal name="agregar_informacion" property="trimestre" value="${materia.getPeriodo()}">
                                    <div class="alert alert-danger" id="alert" style="font-size: 12px;">
                                        Debe colocar el número total de estudiantes.
                                    </div>  
                                </logic:equal>
                            </logic:equal>
                        </logic:present>
                        <html:form action="/guardarPlanilla" style="margin: 0px;">
                            <html:hidden name="rendimientoProf" property="trimestre" value="${materia.getPeriodo()}"/>
                            <div id="tabla" class="table-responsive" style="margin-top: 0px;">
                                <table class="table" style="margin-top: 0px; margin-bottom: 0px;">      
                                    <tbody>
                                        <tr>
                                            <td id="planilla_head" style="padding-top: 12px;">
                                                <strong>AÑO</strong>
                                            </td>
                                            <td id="planilla_body">
                                                <html:select name="rendimientoProf" disabled="false" property="ano" style="width: 95px;">
                                                    <logic:iterate name="anos" id="ano">
                                                        <html:option value="${ano}">
                                                            <bean:write name="ano"/>
                                                        </html:option>
                                                    </logic:iterate>
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="planilla_head" style="padding-top: 12px;">
                                                <logic:present name="error_num_estudiantes" >
                                                    <strong style="color: red">TOTAL ESTUDIANTES</strong>
                                                </logic:present>
                                                <logic:notPresent name="error_num_estudiantes">
                                                    <strong>TOTAL ESTUDIANTES</strong>
                                                </logic:notPresent>
                                            </td>
                                            <td id="planilla_body">
                                                <html:text name="rendimientoProf" property="total_estudiantes"
                                                           style="height: 30px; margin: 0px; width: 95px;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="planilla_head" colspan="4" style="color: #A80000;">
                                    <center>
                                        <strong> Los siguientes datos deben ser llenados con CANTIDADES
                                            y no porcentajes.</strong>
                                    </center>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <logic:present name="error_num_estudiantes" >
                                                <strong style="color: red">ESTUDIANTES CON 1</strong>
                                            </logic:present>
                                            <logic:notPresent name="error_num_estudiantes">
                                                <strong> ESTUDIANTES CON 1 </strong>
                                            </logic:notPresent>
                                        </td>
                                        <td id="planilla_body">
                                            <html:text name="rendimientoProf" property="nota1"
                                                       style="height: 30px; margin: 0px; width: 95px;"/>
                                        </td>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <logic:present name="error_num_estudiantes" >
                                                <strong style="color: red">ESTUDIANTES CON 2</strong>
                                            </logic:present>
                                            <logic:notPresent name="error_num_estudiantes">
                                                <strong> ESTUDIANTES CON 2 </strong>
                                            </logic:notPresent>
                                        </td>
                                        <td id="planilla_body">
                                            <html:text name="rendimientoProf" property="nota2"
                                                       style="height: 30px; margin: 0px; width: 95px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <logic:present name="error_num_estudiantes" >
                                                <strong style="color: red">ESTUDIANTES CON 3</strong>
                                            </logic:present>
                                            <logic:notPresent name="error_num_estudiantes">
                                                <strong> ESTUDIANTES CON 3 </strong>
                                            </logic:notPresent>
                                        </td>
                                        <td id="planilla_body">
                                            <html:text name="rendimientoProf" property="nota3"
                                                       style="height: 30px; margin: 0px; width: 95px;"/>
                                        </td>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <logic:present name="error_num_estudiantes" >
                                                <strong style="color: red">ESTUDIANTES CON 4</strong>
                                            </logic:present>
                                            <logic:notPresent name="error_num_estudiantes">
                                                <strong> ESTUDIANTES CON 4 </strong>
                                            </logic:notPresent>
                                        </td>
                                        <td id="planilla_body">
                                            <html:text name="rendimientoProf" property="nota4"
                                                       style="height: 30px; margin: 0px; width: 95px;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <logic:present name="error_num_estudiantes" >
                                                <strong style="color: red">ESTUDIANTES CON 5</strong>
                                            </logic:present>
                                            <logic:notPresent name="error_num_estudiantes">
                                                <strong> ESTUDIANTES CON 5 </strong>
                                            </logic:notPresent>
                                        </td>
                                        <td id="planilla_body">
                                            <html:text name="rendimientoProf" property="nota5"
                                                       style="height: 30px; margin: 0px; width: 95px;"/>
                                        </td>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <logic:present name="error_num_estudiantes" >
                                                <strong style="color: red">RETIRADOS</strong>
                                            </logic:present>
                                            <logic:notPresent name="error_num_estudiantes">
                                                <strong> RETIRADOS </strong>
                                            </logic:notPresent>
                                        </td>
                                        <td id="planilla_body">
                                            <html:text name="rendimientoProf" property="retirados"
                                                       style="height: 30px; margin: 0px; width: 95px;"/>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <html:hidden name="rendimientoProf" property="codigo_materia" value='${materia.getCodigo()}'/>
                            <html:submit value="Guardar" style="margin-top: 10px;"
                                         styleClass="btn btn-info"
                                         onclick="javascript: return confirm('¿Está seguro de haber llenado correctamente la planilla?')"/> 
                        </html:form>
                    </div>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:present>
<logic:empty name="materias">
    <h4>Ya se han llenado todas las planillas</h4>
</logic:empty>