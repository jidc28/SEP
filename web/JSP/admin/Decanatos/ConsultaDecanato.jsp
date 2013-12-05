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
        <title>Gestion de Decanatos</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <logic:present name="success">
            <div class="alert alert-success" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Decanato registrado exitosamente.
            </div>
        </logic:present>
        <logic:present name="modificacion">
            <div class="alert alert-success" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Decanato modificado exitosamente.
            </div>
        </logic:present>
        <logic:present name="falla">
            <div class="alert alert-danger" style="width: 760px;margin-bottom: 0px; margin-right: 0px;">
                Decanato no fue modificado exitosamente.
            </div>
        </logic:present>
        <h4> Lista de Decanatos en el sistema:</h4>
        <div id="testTable">
            <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th align="center">
                            Codigo
                        </th>
                        <th width="38%" align="center">
                            Nombre Decanato
                        </th>
                        <th align="center">
                            Modificar
                        </th>
                    </tr>
                </thead>
                <logic:iterate name="decanatos" id="Dec">
                    <tr>
                        <td width="20%" align="center">
                            <bean:write name="Dec" property="codigo"/>
                        </td>
                        <td width="20%" align="center">
                            <bean:write name="Dec" property="nombre"/>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/editarNombreDecanato" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="Dec" property="codigo"/>
                                <button type="button" class="btn btn-primary" 
                                        style="padding-bottom: 1px; padding-top: 1px; padding-left: 2px; padding-right: 2px;">
                                    <html:image src="imagenes/edit-img.png" value="" property="" style="height: 27px;"/>
                                </button>
                            </html:form>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>

</body>
</html>

