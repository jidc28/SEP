<%-- 
    Document   : verPlanilla
    Created on : 08/01/2014, 06:08:41 PM
    Author     : langtech
--%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="error_num_estudiantes">
    <div class="alert alert-danger" id="alert">
        <strong> 
            La información sobre el número de estudiantes es errónea. <br>
        </strong> 
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
</logic:present>

<logic:present name="numero_negativo">
    <div class="alert alert-danger" id="alert">
        Los datos insertados <strong> no </strong> pueden ser negativos.
    </div>  
</logic:present>

<logic:present name="agregar_informacion">
    <div class="alert alert-danger" id="alert">
        Debe colocar el número total de estudiantes.
    </div>  
</logic:present>

<h4>
    Modificar Planilla de: <bean:write name="rendimientoProf" property="codigo_materia"/>
    <div style="font-size: 14px; color: grey;">
        <bean:write name="rendimientoProf" property="nombre_materia"/>
    </div>
</h4>

<br style="font-size: 14px;">
<strong> PROFESOR </strong> 
<html:text disabled="true" name="profesor" property="nombre"
           style="height: 30px; margin: 0px; text-align: center;"/>
<strong> USBID </strong> 
<html:text disabled="true" name="profesor" property="usbid"
           style="height: 30px; margin: 0px; text-align: center;"/>
</br>

<html:form action="/modificarPlanilla" >
    <div id="tabla" class="table-responsive" style="margin-top: 20px;">
        <table class="table table-striped">
            <tbody>
                <tr>
                    <td style="text-align: right; width: 40%; font-size: 14px;">
                        <strong>TRIMESTRE</strong>
                    </td>
                    <td style="padding-left: 100px;">
                        <html:select name="rendimientoProf" property="trimestre" 
                                     style="width: 206px;">
                            <html:option value="EM">Ene-Mar</html:option>
                            <html:option value="AJ">Abr-Jul</html:option>
                            <html:option value="SD">Sep-Dic</html:option>
                            <html:option value="V">Intensivo</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 40%; font-size: 14px;">
                        <strong>AÑO</strong>
                    </td>
                    <td style="padding-left: 100px;">
                        <html:select name="rendimientoProf" disabled="false" property="ano" style="width: 206px;">
                            <logic:iterate name="anos" id="ano">
                                <html:option value="${ano}">
                                    <bean:write name="ano"/>
                                </html:option>
                            </logic:iterate>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 40%; font-size: 14px;">
                        <strong style="color: red">TOTAL ESTUDIANTES</strong>
                    </td>
                    <td style="padding-left: 100px;">
                        <html:text name="rendimientoProf" property="total_estudiantes"
                                   style="height: 30px; margin: 0px;"/>
                    </td>
                </tr>
                <tr>
                    <td id="planilla_head" colspan="4" 
                        style="color: #A80000; font-size: 12px;">
            <center>
                <strong> Los siguientes datos deben ser llenados con CANTIDADES
                    y no porcentajes.</strong>
            </center>
            </td>
            </tr>
            <tr>
                <td  id="planilla_head" 
                     style="text-align: right; width: 40%; font-size: 14px; color:black;">
                    <logic:present name="error_num_estudiantes" >
                        <strong style="color: red;"> ESTUDIANTES CON 1 </strong>
                    </logic:present>
                    <logic:notPresent name="error_num_estudiantes" >
                        <strong> ESTUDIANTES CON 1 </strong>
                    </logic:notPresent>
                </td>
                <td  id="planilla_head" style="padding-left: 100px;">
                    <html:text name="rendimientoProf" property="nota1"
                               style="height: 30px; margin: 0px;"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 40%; font-size: 14px;">
                    <logic:present name="error_num_estudiantes" >
                        <strong style="color: red;"> ESTUDIANTES CON 2 </strong>
                    </logic:present>
                    <logic:notPresent name="error_num_estudiantes" >
                        <strong> ESTUDIANTES CON 2 </strong>
                    </logic:notPresent>
                </td>
                <td style="padding-left: 100px;">
                    <html:text name="rendimientoProf" property="nota2"
                               style="height: 30px; margin: 0px;"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 40%; font-size: 14px;">
                    <logic:present name="error_num_estudiantes" >
                        <strong style="color: red;"> ESTUDIANTES CON 3</strong>
                    </logic:present>
                    <logic:notPresent name="error_num_estudiantes" >
                        <strong> ESTUDIANTES CON 3</strong>
                    </logic:notPresent>
                </td>
                <td style="padding-left: 100px;">
                    <html:text name="rendimientoProf" property="nota3"
                               style="height: 30px; margin: 0px;"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 40%; font-size: 14px;">
                    <logic:present name="error_num_estudiantes" >
                        <strong style="color: red;"> ESTUDIANTES CON 4</strong>
                    </logic:present>
                    <logic:notPresent name="error_num_estudiantes" >
                        <strong> ESTUDIANTES CON 4</strong>
                    </logic:notPresent>
                </td>
                <td style="padding-left: 100px;">
                    <html:text name="rendimientoProf" property="nota4"
                               style="height: 30px; margin: 0px;"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 40%; font-size: 14px;">
                    <logic:present name="error_num_estudiantes" >
                        <strong style="color: red;"> ESTUDIANTES CON 5</strong>
                    </logic:present>
                    <logic:notPresent name="error_num_estudiantes" >
                        <strong> ESTUDIANTES CON 5</strong>
                    </logic:notPresent>
                </td>
                <td style="padding-left: 100px;">
                    <html:text name="rendimientoProf" property="nota5"
                               style="height: 30px; margin: 0px;"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 40%; font-size: 14px;">
                    <logic:present name="error_num_estudiantes" >
                        <strong style="color: red;">RETIRADOS</strong>
                    </logic:present>
                    <logic:notPresent name="error_num_estudiantes" >
                        <strong>RETIRADOS</strong>
                    </logic:notPresent>
                </td>
                <td style="padding-left: 100px;">
                    <html:text name="rendimientoProf" property="retirados"
                               style="height: 30px; margin: 0px;"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <html:hidden name="rendimientoProf" property="viejoTrimestre"/>
    <html:hidden name="rendimientoProf" property="viejoAno"/>
    <html:hidden name="rendimientoProf" property="codigo_materia"/>
    <html:submit value="Modificar Planilla" 
                 styleClass="btn btn-info"/> 
</html:form>