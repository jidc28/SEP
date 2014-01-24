<%-- 
    Document   : ConsultaProfesores
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Evaluar Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="scripts/bootstrap.min.js"></script>
        <script type="text/javascript" src="scripts/bootstrap.js"></script>
        <script type="text/javascript">
            $(function(){
                $('#cargando').click(function(){
                      var btn = $(this); 
                      btn.button('loading');
                });
            });
        </script>
        
        <script>  
            $(function () { 
                $("#ayuda1").popover({
                    'title': 'Ir a llenar las planillas',
                    'content': 'Haga click en este <html:link action="/ConsultaProfesores"> link </html:link> para llenar las planillas.',
                    'html': 'true'
                });  
            });  
        </script>

        <logic:present name="enviado_memo">
            <div class="alert alert-success" id="alert">
                Se ha enviado la solicitud para evaluar a los profesores.
            </div>
        </logic:present>
        <logic:present name="no_seleccionado">
            <div class="alert alert-danger" id="alert">
                No ha seleccionado ningun profesor a evaluar.
            </div>
        </logic:present>

        <h4> Evaluar Profesores:</h4>

    <center>
        <logic:notEmpty name="profesores" property="items">
            <div id="tabla" class="table-responsive">
                <table id="tabla" class="table table-striped">
                    <thead>
                        <tr>
                            <th>
                            </th>
                            <th style="font-size: 14px;">
                    <center>
                        USBID
                    </center>
                    </th>
                    <th style="font-size: 14px;">
                    <center>
                        LAPSO CONTRACTUAL INICIO
                    </center>
                    </th>
                    <th width="38%" style="font-size: 14px;">
                    <center>
                        PROFESOR
                    </center>
                    </th>
                    </tr>
                    </thead>
                    <html:form action="/EvaluarProfesores">
                        <logic:iterate id="profesor" name="profesores" property="items">
                            <tr>
                                <td>
                            <center>
                                <html:multibox  property="itemsSeleccionados">
                                    <bean:write name="profesor" property="usbid"/>
                                </html:multibox>
                                </td>
                            </center>
                            <td>
                            <center>
                                <bean:write name="profesor" property="usbid"/>
                            </center>
                            </td>
                            <td>
                            <center>
                                <bean:write name="profesor" property="lapso_contractual_inicio"/> 
                            </center>
                            </td>
                            <td>
                                <bean:write name="profesor" property="apellido"/>, 
                                <bean:write name="profesor" property="nombre"/>
                            </td>
                            </tr>
                        </logic:iterate>
                    </table>
                    <center>
                        <html:submit styleClass="btn btn-primary" style="type: button; data-loading-text: cargando;">
                            Evaluar profesores seleccionados
                        </html:submit>
                        <button type="button" id="cargando" data-loading-text="Cargando..." class="btn btn-primary">
                            <!--<html:submit styleClass="btn btn-primary" 
                                         style="border-color: #428bca; type: button; data-loading-text: cargando;
                                                padding: 0px;">
                                Evaluar profesores seleccionados
                            </html:submit>-->
                            Boton de cargando
                        </button>
                    </center>
                </html:form>
            </div>
        </logic:notEmpty>
        <logic:empty name="profesores" property="items">
            <div class="alert alert-warning alert-dismissable" 
                 id="alert-coord">
                <a href="#" id="ayuda1" style="color: #c09853; float: right" rel="popover" > 
                    <span style="color: #c09853;" class="glyphicon glyphicon-question-sign">     
                    </span> 
                </a>
                <!--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>-->
                <p>
                   Verifique haber llenado todas las planillas de los profesores del 
                   departamento.
                </p>
            </div>
        </logic:empty>
    </center>
</body>
</html>