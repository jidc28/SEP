<%-- 
    Document   : ConsultaMateria
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
<script type="text/javascript" src="scripts/bootstrap.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
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
        <logic:present name="materia_agregada">
            <div class="alert alert-success" id="alert">
                La materia fue agregada exitosamente.
            </div>
        </logic:present>
        <logic:present name="materia_no_agregada">
            <div class="alert alert-danger" id="alert">
                La materia no pudo ser agregada, intentelo mas tarde.
            </div>
        </logic:present>
        <logic:present name="materia_eliminada">
            <div class="alert alert-success" id="alert">
                La materia fue eliminada exitosamente.
            </div>
        </logic:present>
        <logic:present name="materia_no_eliminada">
            <div class="alert alert-danger" id="alert">
                La materia no pudo ser eliminada, intentelo mas tarde.
            </div>
        </logic:present>
        <logic:present name="materia_modificada">
            <div class="alert alert-success" id="alert">
                La materia fue modificada exitosamente.
            </div>
        </logic:present>
        <logic:present name="materia_no_modificada">
            <div class="alert alert-danger" id="alert">
                La materia no pudo ser modificada, intentelo mas tarde.
            </div>
        </logic:present>

        <h4> Lista de Materias en el sistema:</h4>

        <div id="tabla" class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>
                <center>
                    Codigo
                </center>
                </th>
                <th width="38%">
                <center>
                    Nombre Materia
                </center>
                </th>
                <th>
                <center>
                    Modificar
                </center>
                </th>
                <th>
                <center>
                    Eliminar
                </center>
                </th>
                </tr>
                </thead>
                <logic:iterate name="materias" id="Mat">
                    <tr>
                        <td align="center">
                            <bean:write name="Mat" property="codigo"/>
                        </td>
                        <td>
                            <bean:write name="Mat" property="nombre"/>
                        </td>
                        <td align="center">
                            <html:form action="/irModificarMateria">
                                <html:hidden name="Mat" property="codigo"/>
                                <html:submit styleClass="btn btn-info"
                                             style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                    Modificar
                                </html:submit>
                            </html:form>
                        </td>
                        <td align="center">
                            <html:form action="/eliminaMateria">
                                <html:hidden name="Mat" property="codigo"/>
                                <html:submit styleClass="btn btn-danger"
                                             style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;"
                                             onclick="javascript: return confirm('¿Está seguro de que desea eliminar esta materia?')">
                                    Desactivar
                                </html:submit>
                            </html:form>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>

    </body>
</html>