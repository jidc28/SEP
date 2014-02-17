<%-- 
    Document   : asignarPeriodo
    Created on : Feb 16, 2014, 11:51:43 AM
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

        <logic:present name="periodo_vacio">
            <script>
                $(function() {
                    $('#${periodo_vacio.getCodigo()}').addClass('in'); 
                });
            </script>
        </logic:present>

        <h4> Asignar períodos a materias:</h4>

        <br style="font-size: 14px;">
        <strong> PROFESOR </strong> 
        <html:text disabled="true" name="profesor" property="nombre"
                   style="height: 30px; margin: 0px; text-align: center;"/>
        <strong> USBID </strong> 
        <html:text disabled="true" name="profesor" property="usbid"
                   style="height: 30px; margin: 0px; text-align: center;"/>
        </br>

    <center style="margin-top: 20px;">
        <div class="panel-group" id="accordion">
            <logic:iterate name="materias" id="materia">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 id="izquierda" class="panel-title" style="text-align: left;">
                            <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#${materia.getCodigo()}">
                                <bean:write name="materia" property="codigo"/>
                                <div style="font-size: 14px; color: grey;"><bean:write name="materia" property="nombre"/></div>
                            </a>
                        </h4>
                    </div>
                    <div id='${materia.getCodigo()}' class="panel-collapse collapse">
                        <div class="panel-body">
                            <logic:present name="periodo_vacio">
                                <logic:equal name="materia" property="codigo" value="${periodo_vacio.getCodigo()}">
                                    <div class="alert alert-danger" id="alert">
                                        Seleccione el período en que el profesor dicta
                                        la materia.
                                    </div>  
                                </logic:equal>
                            </logic:present>
                            <table style="margin: 0px; width: 100%;">
                                <tbody>
                                    <html:form action="/asignarPeriodo">
                                        <html:hidden name="materia" property="codigo"/>
                                        <tr>
                                            <td text-align="left" style="padding: 5px">
                                                <html:checkbox style="margin: 0px; margin-right: 5px;" 
                                                               name="materia" property="periodoSD"
                                                               value="SD"/>
                                                Septiembre-Diciembre
                                            </td>
                                        </tr>
                                        <tr>
                                            <td text-align="left" style="padding: 5px">
                                                <html:checkbox style="margin: 0px; margin-right: 5px;" 
                                                               name="materia" property="periodoEM"
                                                               value="EM"/>
                                                Enero-Marzo
                                            </td>
                                        </tr>
                                        <tr>
                                            <td text-align="left" style="padding: 5px">
                                                <html:checkbox style="margin: 0px; margin-right: 5px;" 
                                                               name="materia" property="periodoAJ"
                                                               value="AJ"/>
                                                Abril-Julio
                                            </td>
                                        </tr>
                                        <tr>
                                            <td text-align="left" style="padding: 5px">
                                                <html:checkbox style="margin: 0px; margin-right: 5px;" 
                                                               name="materia" property="periodoV"
                                                               value="V"/>
                                                Intensivo
                                            </td>
                                            <td align="right">
                                                <button class="btn btn-info"
                                                        style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                                    Asignar
                                                </button>
                                            </td>
                                        </tr>
                                    </html:form>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </logic:iterate>
        </div>
    </center>
</body>
</html>
