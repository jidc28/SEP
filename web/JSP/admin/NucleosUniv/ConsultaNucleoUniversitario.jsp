<%-- 
    Document   : ConsultaNucleoUniversitario
    Created on : 01/10/2013, 10:28:41 PM
    Author     : Langtech
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
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <logic:present name="success">
            <br>
            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto">
                Núcleo universitario registrado exitosamente.
            </p>
        </logic:present>
        <logic:present name="modificacion">
            <br>
            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto">
                Núcleo universitario modificado exitosamente.
            </p>
        </logic:present>
        
        <h4> Lista de Nucleos Universitarios en el sistema:</h4>
        <div id="testTable">
            <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th width="20%" align="center">
                            Localización
                        </th>
                        <th width="20%" align="center">
                            Nombre
                        </th>
                        <th width="20%" align="center">
                            Estado
                        </th>
                        <th width="20%" align="center">
                            Modificar
                        </th>
                    </tr>
                </thead>
                <logic:iterate name="nucleos_visibles" id="NucV">
                    <tr>
                        <td width="20%" align="center">
                            <bean:write name="NucV" property="codigo"/>
                        </td>
                        <td width="20%" align="center">
                            <bean:write name="NucV" property="nombre"/>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/ocultarNucleoUniversitario" onsubmit="return(this)" style="margin: 0px;">
                                <center>
                                    <html:hidden name="NucV" property="codigo"/>
                                    <html:submit styleClass="btn btn-warning" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea ocultar el núcleo universitario?')">
                                        Ocultar
                                    </html:submit>
                                </center>
                            </html:form>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/editarNombreNucleoUniversitario" onsubmit="return(this)">
                                <html:hidden name="NucV" property="codigo"/>
                                <html:image src="imagenes/edit.png" value="" property=""/>
                            </html:form>
                        </td>
                    </tr>
                </logic:iterate>
                <logic:iterate name="nucleos_ocultos" id="NucO">
                    <tr>
                        <td width="20%" align="center">
                            <bean:write name="NucO" property="codigo"/>
                        </td>
                        <td width="20%" align="center">
                            <bean:write name="NucO" property="nombre"/>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/mostrarNucleoUniversitario" onsubmit="return(this)" style="margin: 0px;">
                                <center>
                                    <html:hidden name="NucO" property="codigo"/>
                                    <html:submit styleClass="btn btn-success" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea ocultar el decanato?')">
                                        Mostrar
                                    </html:submit>
                                </center>
                            </html:form>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/editarNombreNucleoUniversitario" onsubmit="return(this)">
                                <html:hidden name="NucO" property="codigo"/>
                                <html:image src="imagenes/edit.png" value="" property=""/>
                            </html:form>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>

    </body>
</html>