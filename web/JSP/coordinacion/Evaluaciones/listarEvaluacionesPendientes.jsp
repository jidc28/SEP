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
        <title>Evaluaciones Pendientes</title>
    </head>
    <body>
        <h4>Evaluaciones Pendientes</h4>
        <div class="table-responsive">
            <table border="0" style="width: 98%;" class="table table-striped">
                <thead>
                    <tr>
                        <th>
                <center>
                    Materia
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
                    <logic:iterate name="evaluaciones_pendientes" id="evaluacion">
                        <tr>
                            <td>
                                <center>
                                    <bean:write name="evaluacion" property="codigoMateria"/>
                                </center>
                            </td>
                            <td>
                                <bean:write name="evaluacion" property="apellidoProfesor"/>,
                                <bean:write name="evaluacion" property="nombreProfesor"/>
                            </td>
                            <td>
                                <center>
                                    <button type="button" class="btn btn-primary" disabled="disabled" 
                                            style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                        Obtener Evaluacion
                                    </button>
                                </center>
                            </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </div>
    </body>
</html>
