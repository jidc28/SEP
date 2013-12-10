<%-- 
    Document   : consultaMateriaCoord
    Created on : 10/12/2013, 04:33:22 PM
    Author     : jidc28
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
    $(function() {
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
        <title>Gestion de Materias</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <h4> Lista de Materias Vinculadas
        </h4>
        <div class="table-responsive">
            <table border="0" style="margin: auto" class="table-striped">
                <thead>
                    <tr>
                        <th>
                <center>Codigo</center>
                </th>
                <th width="30%">
                <center>Nombre de la Materia</center>
                </th>
                <th>
                <center>Vincular</center>
                </th>
                </thead>
                <tbody>
                    <logic:iterate name="materias" id="mat">
                        <tr>
                            <td width="20%" align="center">
                                <bean:write name="mat" property="codigo"/>
                            </td>
                            <td width="20%" align="center">
                                <bean:write name="mat" property="nombre"/>
                            </td>
                            <td>
                                <html:form action="/vincularMateriaCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                    <html:hidden name="mat" property="codigo" />
                                    <center>
                                        <html:submit styleClass="btn btn-default" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                            Vincular
                                        </html:submit>
                                    </center>
                              </html:form>
                            </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </div>
    </body>
</html>
