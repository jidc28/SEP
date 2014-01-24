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
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Sistema de Evaluación de Profesores</title>
    <body>
    <script>  
        $(function () { 
                $("#ayuda1").popover({
                    'title': 'Gestión de Decanatos',
                    'content': 'permite agregar, consultar, modificar y eliminar'
                        + ' un decanato del sistema.'
                });  
            });  
        $(function () { 
                $("#ayuda2").popover({
                    'title': 'Gestión de Departamentos',
                    'content': 'permite agregar, consultar, modificar y eliminar'
                        + ' un departamento del sistema.'
                });  
            });  
        $(function () { 
                $("#ayuda3").popover({
                    'title': 'Gestión de Coordinaciones',
                    'content': 'permite agregar, consultar, modificar y eliminar'
                        + ' una coordinación, en base a un decanato, en el sistema.'
                });  
            }); 
    </script>
        <div class="panel-group" id="accordion">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 id="izquierda" class="panel-title">
                        <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                            Gestión de Decanatos
                        </a>
                        <a href="#" id="ayuda1" style="float: right" rel="popover" >
                            <span class="glyphicon glyphicon-question-sign"></span> 
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:link action="/agregaDecanatoA" >
                            <h5 align ="center">Agregar Decanato</h5>
                        </html:link>
                        <html:link action="/consultaDecanatoA" >
                            <h5 align ="center">Consultar Decanato</h5>
                        </html:link>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 id="izquierda" class="panel-title">
                        <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            Gestión de Departamentos
                        </a>
                        <a href="#" id="ayuda2" style="float: right" rel="popover" >
                            <span class="glyphicon glyphicon-question-sign"></span> 
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:link action="/irAgregarDepartamento"> 
                            <h5> Agregar Departamento </h5>
                        </html:link>
                        <html:link action="/listarDepartamentos">
                            <h5>Consultar Departamentos</h5>
                        </html:link>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 id="izquierda" class="panel-title">
                        <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                            Gestión de Coordinaciones
                        </a>
                        <a href="#" id="ayuda3" style="float: right" rel="popover" >
                            <span class="glyphicon glyphicon-question-sign"></span> 
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:link action="/agregaCoordinacionA" >
                            <h5 align ="center">Agregar Coordinación</h5>
                        </html:link>
                        <html:link action="/consultaCoordinacionA" >
                            <h5 align ="center">Consultar Coordinación</h5>
                        </html:link>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
