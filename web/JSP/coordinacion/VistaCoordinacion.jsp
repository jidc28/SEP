<%-- 
    Document   : vistaCoordinador
    Created on : 06/12/2013, 02:00:46 PM
    Author     : jidc28
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Sistema de Evaluación de Profesores</title>
    </head>
    <body>

        <logic:present name="evaluaciones_pendientes">
            <div class="alert alert-info alert-dismissable" 
                 id="alert-coord">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <p>
                    <strong>Atención: </strong> <br> 
                    Se solicitó evaluación de profesores. Para más información
                    seleccionar <em>Gestionar Evaluaciones</em>.
                </p>
            </div>
        </logic:present>

        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseDepartamentos">
                            Gestión de Materias
                        </a>
                    </h4>
                </div>
                <div id="collapseDepartamentos" class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:link action="/listarMaterias" >
                            <h5 align ="center">Listar Materias Vinculadas</h5>
                        </html:link>
                        <html:link action="/listarDepartamentos" >
                            <h5 align ="center">Vincular Materias por Departamento</h5>
                        </html:link>  
                    </div>
                </div>
            </div>
            <logic:present name="evaluaciones_pendientes">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseEvaluaciones">
                                Gestionar Evaluaciones  
                            </a>
                        </h4>
                    </div>
                    <div id="collapseEvaluaciones" class="panel-collapse collapse">
                        <div class="panel-body">
                            <html:link action="/listarEvaluacionesPendientes">
                                <h5 align ="center">Listar evaluaciones pendientes                                
                                    <span class="badge">
                                        <bean:write name="evaluaciones_pendientes"/>
                                    </span>
                                </h5>
                            </html:link>
                        </div>
                    </div>
                </div>
            </logic:present>
        </div>
    </body>
</html>
