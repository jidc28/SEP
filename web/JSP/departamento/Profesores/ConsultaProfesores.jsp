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

        <logic:present name="enviado_memo">
            <div class="alert alert-success" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Se ha enviado la solicitud para evaluar a los profesores.
            </div>
        </logic:present>

        <h4> Evaluar Profesores:</h4>

        <div class="table-responsive">
            <table border="0" style="width: 98%;" class="table table-striped">
                <thead>
                    <tr>
                        <th>
                        </th>
                        <th>
                <center>
                    usbid
                </center>
                </th>
                <th>
                <center>
                    Lapso contractual inicio
                </center>
                </th>
                <th width="38%">
                <center>
                    Profesor
                </center>
                </th>
                </tr>
                </thead>
                <html:form action="/multibox">
                    <logic:iterate id="profesor" name="profesores" property="profesores">
                        <tr>
                            <td>
                        <center>
                            <html:multibox  property="profesoresSeleccionados">
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
                    <html:submit styleClass="btn btn-primary">
                        Evaluar profesores seleccionados
                    </html:submit>
                </center>
            </html:form>
        </div>
    </body>
</html>