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

<h4>Evaluación del profesor: 
    <bean:write name="profesor" property="apellido"/>,
    <bean:write name="profesor" property="nombre"/>
</h4>

<div style="width: 95%">
    <ul class="nav nav-tabs" style="height: 39px;">
        <li class="active">
            <a href="#departamento" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                Información del departamento
            </a>
        </li>
        <li>
            <a href="#profesor" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                Información del profesor
            </a>
        </li>
    </ul>
</div>


<div class="tab-content">
    <div class="tab-pane" id="profesor">
        <center>
            <div id="cuerpo-principal">     
                <div class="alert alert-danger" 
                     id="alert-coord" style="width: 50%; margin-top: 50px;">
                    <p>
                        AREA EN CONSTRUCCIÓN
                    </p>
                </div>
            </div>
        </center>                
    </div>

    <div class="tab-pane active" id="departamento">
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

<div class="recomendar">
    <html:form action="/evaluarCoordinacion" onsubmit="return(this)" style="margin: 0px;">
        <html:hidden name="evaluacion" property="usbid_profesor" value="${profesor.getUsbid()}"/>
        <html:hidden name="evaluacion" property="codigo_materia"/>
    <table class="table" style="border-top: none; margin: 0px;">
        <tbody>
            <tr>
                <td style="border: none;">
                    <html:checkbox style="margin-right: 5px;" 
                                   name="evaluacion" property="recomendado"
                                   value="si"/>
                    Recomendado
                </td>
            </tr>
            <tr>
                <td style="border: none;">
                    <html:checkbox style="margin-right: 5px;" 
                                   name="evaluacion" property="recomendado"
                                   value="no"/>
                    No recomendado
                </td>
            </tr>
            <tr>
                <td style="border: none;">
                    <center>
                        <html:submit styleClass="btn btn-success">
                            Evaluar
                        </html:submit>
                    </center>
                </td>
            </tr>
        </tbody>
    </table>
    </html:form>
</div>