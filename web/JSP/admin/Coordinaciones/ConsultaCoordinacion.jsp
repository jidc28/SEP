<%-- 
    Document   : ConsultaCoordinacion
    Created on : 01/10/2013, 12:16:56 AM
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

        <logic:present name="success">
            <br>
            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto">
                Coordinación registrada exitosamente.
            </p>
        </logic:present>

        <logic:present name="modificacion">
            <br>
            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto">
                Coordinacion modificada exitosamente.
            </p>
        </logic:present>
            
        <logic:present name="eliminado">
            <br>
            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto"> 
               Coordinacion eliminada exitosamente.
            </p>
        </logic:present>
            
        <logic:present name="noEliminado">
            <br>
            <p align ="center" style="background-color: springgreen;
               width: 300px; margin-left: auto; margin-right: auto"> 
               La coordinacion no pudo ser eliminada exitosamente.
            </p>
        </logic:present>
          
        <h4> Lista de Coordinaciones en el sistema:</h4>
        <div id="testTable">
            <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th width="20%" align="center">
                            Codigo
                        </th>
                        <th width="38%" align="center">
                            Nombre Coordinacion
                        </th>
                        <th width="20%" align="center">
                            Estado
                        </th>
                        <th width="20%" align="center">
                            Modificar Nombre
                        </th>
                        <th width="20%" align="center">
                            Eliminar
                        </th>
                    </tr>
                </thead>
                <logic:iterate name="coordinaciones_visibles" id="CoordV">
                    <tr>
                        <td width="20%" align="center">
                            <bean:write name="CoordV" property="codigo"/>
                        </td>
                        <td width="20%" align="center">
                            <bean:write name="CoordV" property="nombre"/>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/ocultarCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                <center>
                                    <html:hidden name="CoordV" property="codigo"/>
                                    <html:submit styleClass="btn btn-warning" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea ocultar la coordinacion?')">
                                        Ocultar
                                    </html:submit>
                                </center>
                            </html:form>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/editarNombreCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="CoordV" property="codigo"/>
                                <button type="button" class="btn btn-primary" 
                                        style="padding-bottom: 1px; padding-top: 1px; padding-left: 2px; padding-right: 2px;">
                                    <html:image src="imagenes/edit-img.png" value="" property="" style="height: 27px;"/>
                                </button>
                            </html:form>
                        </td>
                        <td>
                            <html:form action="/eliminaCoordinacionA" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="CoordV" property="codigo"/>
                                <html:submit styleClass="btn btn-danger" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea eliminar la coordinacion?')">
                                    Eliminar
                                </html:submit>
                            </html:form>
                        </td>
                    </tr>
                </logic:iterate>
                <logic:iterate name="coordinaciones_ocultas" id="CoordO">
                    <tr>
                        <td width="20%" align="center">
                            <bean:write name="CoordO" property="codigo"/>
                        </td>
                        <td width="20%" align="center">
                            <bean:write name="CoordO" property="nombre"/>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/mostrarCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                <center>
                                    <html:hidden name="CoordO" property="codigo"/>
                                    <html:submit styleClass="btn btn-success" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea ocultar la coordinacion?')">
                                        Mostrar
                                    </html:submit>
                                </center>
                            </html:form>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/editarNombreCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="CoordO" property="codigo"/>
                                <button type="button" class="btn btn-primary" 
                                        style="padding-bottom: 1px; padding-top: 1px; padding-left: 2px; padding-right: 2px;">
                                    <html:image src="imagenes/edit-img.png" value="" property="" style="height: 27px;"/>
                                </button>
                            </html:form>
                        </td>
                        <td>
                            <html:form action="/eliminaCoordinacionA" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="CoordO" property="codigo"/>
                                <html:submit styleClass="btn btn-danger" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea eliminar la coordinacion?')">
                                    Eliminar
                                </html:submit>
                            </html:form>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>

    </body>
</html>