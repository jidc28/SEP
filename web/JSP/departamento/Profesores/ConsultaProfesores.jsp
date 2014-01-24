<%-- 
    Document   : ConsultaProfesores
    Created on : Jan 11, 2014, 4:57:54 PM
    Author     : smaf
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
        <title>Gestión de Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="scripts/bootstrap.min.js"></script>
        <script type="text/javascript" src="scripts/bootstrap.js"></script>

        <h4> Llenar Planilla de Evaluación Profesores: </h4>

    <center>
        <div id="tabla" class="table-responsive">
            <table id="tabla" class="table table-striped">
                <thead>
                    <tr>
                        <th style="font-size: 14px; width: 15%;">
                <center>
                    USBID
                </center>
                </th>
                <th style="font-size: 14px;">
                <center>
                    NOMBRE
                </center>
                </th>
                <th style="font-size: 14px;">
                <center>
                    NIVEL
                </center>
                </th>
                <th style="font-size: 14px;">
                <center>
                    VER PLANILLAS
                </center>
                </th>
                <th style="font-size: 14px;">
                <center>
                    LLENAR PLANILLAS
                </center>
                </th>
                </tr>
                </thead>
                <logic:iterate id="profesor" name="profesores">
                    <tr>
                        <td>
                    <center>
                        <bean:write name="profesor" property="usbid"/>
                    </center>
                    </td>
                    <td>
                        <bean:write name="profesor" property="apellido"/>,
                        <bean:write name="profesor" property="nombre"/>
                    </td>
                    <td>
                        <center>
                            <bean:write name="profesor" property="nivel"/>
                        </center>
                    </td>
                    <td>
                    <center>
                        <html:form action="/verPlanillasLlenas" style="margin: 0px;">
                            <html:hidden name="profesor" property="nombre"/>
                            <html:hidden name="profesor" property="apellido"/>
                            <html:hidden name="profesor" property="usbid"/>
                            <html:submit styleClass="btn btn-default"
                                         style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                Ver Planillas
                            </html:submit>
                        </html:form>
                    </center>
                    </td>
                    <td>
                    <center>
                        <html:form action="/irLlenarPlanillas" style="margin: 0px;">
                            <html:hidden name="profesor" property="nombre"/>
                            <html:hidden name="profesor" property="apellido"/>
                            <html:hidden name="profesor" property="usbid"/>
                            <html:submit styleClass="btn btn-primary"
                                         style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                Llenar Planilla
                            </html:submit>
                        </html:form>
                    </center>
                    </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>
    </center>
</body>
</html>