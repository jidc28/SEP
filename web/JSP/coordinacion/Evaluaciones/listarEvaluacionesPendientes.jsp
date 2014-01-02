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

<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script src="css/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href="css/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Gestión de Evaluaciones</title>
    </head>
    <body>
        <h4>Evaluaciones Pendientes</h4>
    <center>
        <div class="table-responsive" id="tabla">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>
                <center>
                    Materia
                </center>
                </th>
                <th>
                <center>
                    usbid
                </center>
                </th>
                <th>
                <center>
                    Profesor
                </center>
                </th>
                <th>
                <center>
                    Obtener Evaluacion
                </center>
                </th>
                </tr>
                </thead>
                <tbody>
                    <logic:present name="evaluaciones_pendientes">
                        <logic:iterate name="evaluaciones_pendientes" id="evaluacion">
                            <tr>
                                <td rowspan="<bean:write name="evaluacion" property="numeroMateria"/>">
                        <center>
                            <bean:write name="evaluacion" property="codigoMateria"/>
                        </center> 
                        </td>
                        <td>
                        <center>
                            <bean:write name="evaluacion" property="primerProfesor.usbid"/>
                        </center>
                        </td>
                        <td>
                            <bean:write name="evaluacion" property="primerProfesor.apellido"/>,
                            <bean:write name="evaluacion" property="primerProfesor.nombre"/>
                        </td>
                        <td rowspan="<bean:write name="evaluacion" property="numeroMateria"/>">
                        <center>
                            <button type="button" class="btn btn-primary" disabled="disabled">
                                Obtener evaluacion
                            </button>
                        </center>
                        </td>
                        </tr>
                        <logic:iterate name="evaluacion" property="profesores" id="profesores">
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
                            </tr>
                        </logic:iterate>
                    </logic:iterate>
                </logic:present>
                </tbody>
            </table>
        </div>
    </center>
</body>
</html>