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
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Evaluar Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="scripts/bootstrap.min.js"></script>
        <script type="text/javascript" src="scripts/bootstrap.js"></script>

        <logic:present name="no_seleccionado">
            <div class="alert alert-danger" id="alert">
                Porfavor, seleccione una o más materias a asignar.
            </div>
        </logic:present>
        
        <h4> Asignar Materias a Profesores:</h4>

        <br style="font-size: 14px;">
        <strong> PROFESOR </strong> 
        <html:text disabled="true" name="profesor" property="nombre"
                   style="height: 30px; margin: 0px; text-align: center;"/>
        <strong> USBID </strong> 
        <html:text disabled="true" name="profesor" property="usbid"
                   style="height: 30px; margin: 0px; text-align: center;"/>
        </br>

    <center>
        <logic:notEmpty name="materias" property="items">
            <div id="tabla" class="table-responsive" style="margin-top: 20px;">
                <table id="tabla" class="table table-striped">
                    <thead>
                        <tr>
                            <th>
                            </th>
                            <th style="font-size: 14px;">
                    <center>
                        CODIGO
                    </center>
                    </th>
                    <th style="font-size: 14px;">
                    <center>
                        NOMBRE
                    </center>
                    </th>
                    <th style="font-size: 14px;">
                    <center>
                        CRÉDITOS
                    </center>
                    </th>
                    </tr>
                    </thead>
                    <html:form action="/asignarMateriaProfesor">
                        <logic:iterate id="materia" name="materias" property="items">
                            <tr>
                                <td>
                            <center>
                                <html:multibox  property="itemsSeleccionados">
                                    <bean:write name="materia" property="codigo"/>
                                </html:multibox>
                                </td>
                            </center>
                            <td>
                            <center>
                                <bean:write name="materia" property="codigo"/>
                            </center>
                            </td>
                            <td>
                                <bean:write name="materia" property="nombre"/> 
                            </td>
                            <td>
                            <center>
                                <bean:write name="materia" property="creditos"/>
                            </center>
                            </td>
                            </tr>
                        </logic:iterate>
                    </table>
                    <center>
                        <html:submit styleClass="btn btn-primary" style="type: button; data-loading-text: cargando;">
                            Asignar Materias Seleccionadas
                        </html:submit>
                    </center>
                </html:form>
            </div>
        </logic:notEmpty>
        <logic:empty name="materias" property="items">
            <div class="alert alert-warning alert-dismissable" 
                 id="alert-coord">
                <a href="#" id="ayuda1" style="color: #c09853; float: right" rel="popover" > 
                    <span style="color: #c09853;" class="glyphicon glyphicon-question-sign">     
                    </span> 
                </a>
                <!--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>-->
                <p>
                    No existen materias registradas en el sistema.
                </p>
            </div>
        </logic:empty>
    </center>
</body>
</html>
