<%-- 
    Document   : ConsultaCoordinacionDepartamento
    Created on : 05/12/2013, 12:31:29 PM
    Author     : jidc28
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
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
</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Gestion de Departamentos</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <logic:present name="success">
            <div class="alert alert-success" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Departamento registrado exitosamente.
            </div>
        </logic:present>
        <logic:present name="modificacion">
            <div class="alert alert-success" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Departamento modificado exitosamente.
            </div>
        </logic:present>
        <logic:present name="falla">
            <div class="alert alert-danger" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Departamento no fue modificado exitosamente.
            </div>
        </logic:present>
        
        <h4 style="margin-top: 50px;"> Lista de Departamentos del 
            <logic:present name="decanato_actual">
                <bean:write name="decanato_actual" property="nombre"/>:
            </logic:present>
        </h4>