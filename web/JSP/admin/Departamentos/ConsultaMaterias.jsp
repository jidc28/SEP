<%-- 
    Document   : ConsultaMaterias
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Gestión de Materias</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <logic:present name="success">
            <div class="alert alert-success" id="alert">
                Decanato registrado exitosamente.
            </div>
        </logic:present>
        <logic:present name="modificacion">
            <div class="alert alert-success" id="alert">
                Decanato modificado exitosamente.
            </div>
        </logic:present>
        <logic:present name="falla">
            <div class="alert alert-danger" id="alert">
                Decanato no fue modificado exitosamente.
            </div>
        </logic:present>
        <h4> Lista de Materias en el sistema:</h4>
        <div id="tabla">
            <table class="table table-responsive">
                <thead>
                    <tr>
                        <th align="center">
                            Codigo
                        </th>
                        <th width="38%" align="center">
                            Nombre Materia
                        </th>
                        <th align="center">
                            Modificar
                        </th>
                    </tr>
                </thead>
                <logic:iterate name="materias" id="Mat">
                    <tr>
                        <td width="20%" align="center">
                            <bean:write name="Mat" property="codigo"/>
                        </td>
                        <td width="20%" align="center">
                            <bean:write name="Mat" property="nombre"/>
                        </td>
                        <td width="20%" align="center">
                            Accion
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>

</body>
</html>

