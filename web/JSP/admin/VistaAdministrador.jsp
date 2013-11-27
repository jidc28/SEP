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
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>

        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            Gestión de Usuarios
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body">
                    <!--
                    <html:link action="/createUserA" >
                        <h5 align ="center">Crear Usuario</h5>
                    </html:link>
                    -->
                    <html:link action="/showUserA" >
                        <h5 align ="center">Consultar Usuarios</h5>
                    </html:link>
                    <!--
                    <html:link action="/listarProfesores" >
                        <h5 align ="center">Mostrar Profesores</h5>
                    </html:link>
                    -->
                    </div>
                </div>
            </div>
<!--            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                            Gestión de Carreras
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse">
                    <div class="panel-body">
                    <html:link action="/agregaCarreraA" >
                        <h5 align ="center">Agregar Carrera</h5>
                    </html:link>
                    <html:link action="/consultaCarreraA" >
                        <h5 align ="center">Consultar Carrera</h5>
                    </html:link>
                    </div>
                </div>
            </div> -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                            Gestión de Decanatos
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
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                            Gestión de Coordinaciones
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
                            Gestión de Núcleos Universitarios
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse">
                    <div class="panel-body">
                        <html:link action="/NucleoUnivA" >
                            <h5 align ="center">Agregar Núcleo Universitario</h5>
                        </html:link>
                        <html:link action="/consultaNucleoUniversitarioA" >
                            <h5 align ="center">Consultar Núcleo Universitario</h5>
                        </html:link>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
