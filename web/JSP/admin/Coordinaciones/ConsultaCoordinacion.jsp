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
            <div class="alert alert-success" id="alert">
                Coordinación registrada exitosamente.
            </div>
        </logic:present>

        <logic:present name="modificacion">
            <div class="alert alert-success" id="alert">
                Coordinacion modificada exitosamente.
            </div>
        </logic:present>
            
        <logic:present name="eliminado">
            <div class="alert alert-success" id="alert">
               Coordinacion eliminada exitosamente.
            </div>
        </logic:present>
            
        <logic:present name="noEliminado">
            <div class="alert alert-danger" id="alert">
               La coordinacion no pudo ser eliminada exitosamente.
            </div>
        </logic:present>
          
        <h4> Lista de Coordinaciones en el sistema:</h4>
        <div id="tabla" class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>
                            <center>
                            Codigo
                            </center>
                        </th>
                        <th>
                            <center>
                            Nombre Coordinacion
                            </center>
                        </th>
                        <th>
                            <center>
                            Modificar Nombre
                            </center>
                        </th>
                        <th>
                            <center>
                            Eliminar
                            </center>
                        </th>
                    </tr>
                </thead>
                <logic:iterate name="coordinaciones" id="Coord">
                    <tr>
                        <td align="center">
                            <bean:write name="Coord" property="codigo"/>
                        </td>
                        <td>
                            <bean:write name="Coord" property="nombre"/>
                        </td>
                        <td align="center">
                            <html:form action="/editarNombreCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="Coord" property="codigo"/>
                                <button type="button" class="btn btn-primary" 
                                        style="padding-bottom: 1px; padding-top: 1px; padding-left: 2px; padding-right: 2px;">
                                    <html:image src="imagenes/edit-img.png" value="" property="" style="height: 27px;"/>
                                </button>
                            </html:form>
                        </td>
                        <td>
                            <html:form action="/eliminaCoordinacionA" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="Coord" property="codigo"/>
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