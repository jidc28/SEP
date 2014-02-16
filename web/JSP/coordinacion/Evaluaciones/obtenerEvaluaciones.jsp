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
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="css/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Evaluaciones</title>
    </head>
    <body>
        <h4>Evaluación de la materia:
            <bean:write name="evaluacion" property="codigo_materia"/>
        </h4>
        
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
<!--                <ul class="nav nav-pills" align="center">
                    <li class="dropdown" style="width: 30%;">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle"
                           style="font-size: 14px;">
                            Ver porcentajes por nota
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu" style="width: 100%;">
                            <li><a href="#" style="font-size: 13px;">
                                    <strong>SACARON 1: </strong>
                                    <bean:write name="porcentaje1"/>%
                                </a>
                            </li>
                            <li><a href="#" style="font-size: 13px;">
                                    <strong>SACARON 2: </strong>
                                    <bean:write name="porcentaje2"/>%
                                </a>
                            </li>
                            <li><a href="#" style="font-size: 13px;">
                                    <strong>SACARON 3: </strong>
                                    <bean:write name="porcentaje3"/>%
                                </a>
                            </li>
                            <li><a href="#" style="font-size: 13px;">
                                    <strong>SACARON 4: </strong>
                                    <bean:write name="porcentaje4"/>%
                                </a>
                            </li>
                            <li><a href="#" style="font-size: 13px;">
                                    <strong>SACARON 5: </strong>
                                    <bean:write name="porcentaje5"/>%
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="#">Trash</a></li>
                        </ul>
                    </li>
                </ul>-->
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
                </tbody>
            </table>
        </div>
               
        <h3 style="text-align: left; font-size: 18px; margin-left: 30px; margin-bottom: 20px;">
            Porcentaje de profesores evaluados: 
        </h3>
                        
        <div class="progress" style="width: 60%">
            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<bean:write name="porcentajeProf"/>" aria-valuemin="0" aria-valuemax="100" style="width: <bean:write name="porcentajeProf"/>%">
                <strong><bean:write name="porcentajeProf"/>%</strong>
            </div>
        </div>
                     
        <h3 style="text-align: left; font-size: 18px; margin-left: 30px;">
            Departamentos que no han enviado el memo para evaluar:  
        </h3>
        
        <div id="tabla" class="table-responsive" style="margin-top: 20px; width: 50%">
            <table class="table">
            <tbody>
                <logic:iterate name="departamentosFaltantes" id="departamento">
                    <tr>
                        <td style="align: left;">
                            <bean:write name="departamento"/>
                        </td>
                        <td style="align: left;">
                                <span class="glyphicon glyphicon-envelope" style="color: green;"></span>
                        </td>
                    </tr>
                </logic:iterate>
            </tbody>
            </table>
        </div>
    </body>
</html>
