<%-- 
    Document   : ConsultaDecanato
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
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
<script type="text/javascript">
    function altRows(id) {
        if (document.getElementsByTagName) {

            var table = document.getElementById(id);
            var rows = table.getElementsByTagName("tr");

            for (i = 0; i < rows.length; i++) {
                if (i % 2 === 0) {
                    rows[i].className = "evenrowcolor";
                } else {
                    rows[i].className = "oddrowcolor";
                }
            }
        }
    }
    $(document).ready(function() {
        $('table').tablePagination({});
    });
    window.onload = function() {
        altRows('alternatecolor');
    }
    $(function () {
        $('#todos').tooltip();
    });
</script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <!--        <link rel="stylesheet" type="text/css" href="css/css/bootstrap-theme.css">
                <link rel="stylesheet" type="text/css" href="css/css/bootstrap-theme.min.css"> -->
        <title>Gestion de Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <logic:present name="enviado_memo_profesor">
            <div class="alert alert-success" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Se ha enviado la solicitud para evaluar a 
                <bean:write name="enviado_memo_profesor" property="nombre"/>
                <bean:write name="enviado_memo_profesor" property="apellido"/>
            </div>
        </logic:present>

        <logic:present name="enviado_memo">
            <div class="alert alert-success" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Se ha enviado la solicitud para evaluar a los profesores.
            </div>
        </logic:present>

        <h4> Evaluar Profesores:</h4>

        <div style="margin-top: 20px;" align="center">
                <button id="todos" type="button" class="btn btn-primary" data-toggle="tooltip" data-placement="bottom" title="Evaluar a todos los profesores del departamento" >
                    <html:link action="/enviarMemoEvaluar" style="text-decoration:none; color: #FFFFFF">
                        Evaluar Profesores
                    </html:link>
                </button>
            <br>
        </div>
        <div class="table-responsive">
            <table border="0" style="width: 98%;" class="table table-striped">
                <thead>
                    <tr>
                        <th>
                <center>
                    usbid
                </center>
                </th>
                <th width="38%">
                <center>
                    Profesor
                </center>
                </th>
                <th>
                <center>
                    Evaluar
                </center>
                </th>
                <th>
                <center>
                    Evaluacion
                </center>
                </th>
                </tr>
                </thead>
                <logic:iterate name="profesores" id="Prof">
                    <tr>
                        <td align="center">
                            <bean:write name="Prof" property="usbid"/>
                        </td>
                        <td>
                            <bean:write name="Prof" property="nombre"/>,
                            <bean:write name="Prof" property="apellido"/>
                        </td>
                        <td align="center">
                            <html:form action="/enviarMemoEvaluarProfesor">
                                <html:hidden name="Prof" property="usbid"/>
                                <html:submit styleClass="btn btn-success" 
                                             style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                    Evaluar
                                </html:submit>
                            </html:form>
                        </td>
                        <td align="center">
                            <button type="button" class="btn btn-info" disabled="disabled"
                                    style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                Ver evaluacion
                            </button>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>

    </body>
</html>