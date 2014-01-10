<%-- 
    Document   : resultadosMaterias
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

        <div class="alert alert-info alert-dismissable" 
             id="alert">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <p>
                <strong>Atención: </strong> <br> 
                Para realizar el llenado del formulario debe tener a mano
                <strong>actas del trimestre correspondiente.</strong>
            </p>
        </div>
        
        <h4>
            Información de Materias
        </h4>
        <br style="font-size: 14px;">
            <strong> USBID </strong> 
            <html:text disabled="true" name="profesor" property="usbid"
                       style="height: 30px; margin: 0px;"/>
        </br>      
            
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
                            <center> MATERIA </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center> N. ESTUDIANTES </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center> PROMEDIO </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center> > 3 </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center> < 3 </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center> RETIRADOS </center>
                        </th>
                    </thead>
                    <tbody>
                        <logic:iterate name="rendimiento" id="RendimientoProf" >
                            <tr>
                                <td>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="EM">
                                        Ene-Mar
                                    </logic:equal>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="AJ">
                                        Abr-Jul
                                    </logic:equal>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="V">
                                        Intensivo
                                    </logic:equal>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="SD">
                                        Sep-Dic
                                    </logic:equal>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="ano"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="codigo_materia"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="nombre_materia"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="total_estudiantes"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="nota_prom"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="aprobados"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="aplazados"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="retirados"/>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </table>
            <html:link action="/irAgregarRendimiento">
                <button class="btn btn-primary">
                    Agregar Información de Otra Materia
                </button>
            </html:link>
        </div>


    </body>
</html>
