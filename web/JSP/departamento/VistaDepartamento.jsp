<%-- 
    Document   : VistaAdministrador
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
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
        <!--<link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">-->
        <!--<link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">-->
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Sistema de Evaluación de Profesores</title>
    </head>
    <body>
    <script>  
        $(function () { 
                $("#ayuda1").popover({
                    'title': 'Gestión de Materias',
                    'content': 'permite agregar materias a la oferta y consultar'
                        + ' las materias ofertadas por el departamento.'
                });  
            });  
        $(function () { 
                $("#ayuda2").popover({
                    'title': 'Gestión de Profesores',
                    'content': 'permite evaluar un grupo de profesores y llenar'
                        +' las planillas correspondientes a las materias '
                        +'dictadas por cada uno de ellos.'
                });  
            });
    </script>
        <logic:present name="solicitud_apertura_materia">
            <div class="alert alert-info alert-dismissable" 
                 id="alert-coord">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <p>
                    <strong>Atención: </strong> <br> 
                    Se solicitó la apertura de 
                    <strong> <bean:write name="solicitud_apertura_materia"/> </strong>
                    materia(s). Para más información seleccionar 
                    <em>Gestionar Materias, Listar solicitudes pendientes</em>.
                </p>
            </div>
        </logic:present>
        
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 id="izquierda" class="panel-title">
                        <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseMaterias">
                            Gestión de Materias
                        </a>
                        <a href="#" id="ayuda1" style="float: right" rel="popover"> 
                            <span class="glyphicon glyphicon-question-sign"></span> 
                        </a>
                    </h4> 
                </div>
                <div id="collapseMaterias" class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:link action="/vistaAgregaMateria">
                            <h5 align ="center">Agregar Materia</h5>
                        </html:link>
                        <html:link action="/consultaMateria">
                            <h5 align ="center">Consultar Materias</h5>
                        </html:link>
                        <logic:present name="solicitud_apertura_materia">
                            <html:link action="/consultaSolicitudesApertura"> 
                                <h5 align ="center"> Listar Solicitudes Pendientes                                
                                    <span class="badge">
                                        <bean:write name="solicitud_apertura_materia"/>
                                    </span>
                                </h5>
                            </html:link>
                        </logic:present>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 id="izquierda" class="panel-title">
                        <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseProfesor">
                            Gestión de Profesores
                        </a>
                        <a href="#" id="ayuda2" style="float: right" rel="popover"> 
                            <span class="glyphicon glyphicon-question-sign"></span> 
                        </a>
                    </h4>
                </div>
                <div id="collapseProfesor" class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:link action="/irEvaluarProfesores">
                            <h5 align ="center">Evaluar Profesores</h5>
                        </html:link>
                        <html:link action="/ConsultaProfesores">
                            <h5 align ="center">Gestionar Planillas de Evaluación de Profesores</h5>
                        </html:link>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>