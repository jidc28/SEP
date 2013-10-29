<%-- 
    Document   : ConsultaNucleoUniversitarioSuccess
    Created on : 15/10/2013, 10:39:37 PM
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
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto">
                Nucleo Universitario modificado exitosamente.
            </p>
            
            <h4> Lista de Nucleos Universitarios en el sistema:</h4>
            <div id="testTable">
                <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                    <thead>
                        <tr>
                            <th width="155px" align="center">
                                Codigo
                            </th>
                            <th width="155px" align="center">
                                Nombre Nucleo
                            </th>
                            <th width="155px" align="center">
                                Estado
                            </th>
                            <th width="155px" align="center">
                                Modificar Nombre
                            </th>
                            <th width="155px" align="center">
                                Modificar Estado
                            </th>
                        </tr>
                    </thead>
                    <logic:iterate name="nucleos" id="Nucleos">
                        <tr>
                            <td width="150px" align="center">
                                <bean:write name="Nucleos" property="codigo"/>
                            </td>
                            <td width="150px" align="center">
                                <bean:write name="Nucleos" property="nombre"/>
                            </td>
                            <td width="150px" align="center">
                                <bean:write name="Nucleos" property="estado"/>
                            </td>
                            <td width="150px" align="center">
                                <html:form action="/editarNombreNucleoUniversitario" onsubmit="return(this)">
                                    <html:hidden name="Nucleos" property="codigo"/>
                                    <html:image src="imagenes/edit.png" value="" property=""/>
                                </html:form>
                            </td>
                            <td width="150px" align="center">
                                <html:form action="/cambiarStatusNucleoUniversitario" onsubmit="return(this)">
                                    <html:hidden name="Nucleos" property="codigo"/>
                                    <html:image src="imagenes/visibilidad.png" value="" property=""/>
                                </html:form>
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </div>

    </body>
</html>