<%-- 
    Document   : llenarPlanillas
    Created on : 08/01/2014, 06:08:41 PM
    Author     : jidc28
--%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Sistema de Evaluación de Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <script src="css/js/bootstrap.min.js"></script>

        <div class="alert alert-info alert-dismissable" 
             id="alert">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <p>
                <strong>Atención: </strong> <br> 
                Para realizar el llenado del formulario debe tener a mano
                <strong>actas del trimestre correspondiente.</strong>
            </p>
        </div>
        
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
                <bean:write name="planilla_guardada" property="codigo_materia"/>
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
        <div class="panel-group" id="accordion">
        <logic:iterate name="materias" id="materia">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 id="izquierda" class="panel-title" style="text-align: left;">
                        <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#${materia.getCodigo()}">
                            <bean:write name="materia" property="codigo"/>
                            <div style="font-size: 14px; color: grey;"><bean:write name="materia" property="nombre"/></div>
                        </a>
                    </h4>
                </div>
                <div id='${materia.getCodigo()}' class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:form action="/guardarPlanilla" style="margin: 0px;">
                            <div id="tabla" class="table-responsive" style="margin-top: 0px;">
                                <table class="table" style="margin-top: 0px; margin-bottom: 0px;">      
                                <tbody>
                                    <tr>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <strong>TRIMESTRE</strong>
                                        </td>
                                        <td id="planilla_body" style="width: 155px;">
                                            <html:select name="rendimientoProf" property="trimestre" style="width: 95px;">
                                                <html:option value="EM">Ene-Mar</html:option>
                                                <html:option value="AJ">Abr-Jul</html:option>
                                                <html:option value="SD">Sep-Dic</html:option>
                                                <html:option value="V">Intensivo</html:option>
                                            </html:select>
                                        </td>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <strong>AÑO</strong>
                                        </td>
                                        <td id="planilla_body">
                                            <html:select name="rendimientoProf" disabled="false" property="ano" style="width: 95px;">
                                                <html:option value="2004">2004</html:option> 
                                                <html:option value="2005">2005</html:option> 
                                                <html:option value="2006">2006</html:option> 
                                                <html:option value="2007">2007</html:option> 
                                                <html:option value="2008">2008</html:option> 
                                                <html:option value="2009">2009</html:option> 
                                                <html:option value="2010">2010</html:option> 
                                                <html:option value="2011">2011</html:option> 
                                                <html:option value="2012">2012</html:option> 
                                                <html:option value="2013">2013</html:option> 
                                                <html:option value="2014">2014</html:option> 
                                                <html:option value="2015">2015</html:option> 
                                                <html:option value="2016">2016</html:option> 
                                                <html:option value="2017">2017</html:option> 
                                                <html:option value="2018">2018</html:option> 
                                                <html:option value="2019">2019</html:option> 
                                                <html:option value="2020">2020</html:option> 
                                                <html:option value="2021">2021</html:option> 
                                                <html:option value="2022">2022</html:option> 
                                                <html:option value="2023">2023</html:option> 
                                                <html:option value="2024">2024</html:option> 
                                                <html:option value="2025">2025</html:option> 
                                                <html:option value="2026">2026</html:option> 
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="planilla_head" style="padding-top: 12px;">
                                            <strong style="color: red">TOTAL ESTUDIANTES</strong>
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
                                     styleClass="btn btn-info"/> 
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
    </body>
</html>
