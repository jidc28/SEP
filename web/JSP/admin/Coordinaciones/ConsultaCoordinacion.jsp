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
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="css/js/bootstrap.js"></script>
        <script type="text/javascript" src="css/js/bootstrap.min.js"></script>
        <title>Gestión de coordinaciones</title>
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
    
    <logic:present name="decanatos">
    <html:form action="/consultaCoordinacionA">
        <table>
        <!--<center>-->
            <tbody>
                <tr>
                    <td style="margin-left: 10px;"">
                        <html:select style="width: 350px; height: 34px; margin-right: 10px;" name="Decanato" property="nombre">
                            <logic:iterate name="decanatos" id="decanato">
                                <option>
                                    <bean:write name="decanato" property="nombre"/>
                                </option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td>
                        <html:submit styleClass="btn btn-info">
                            Consultar
                        </html:submit>
                    </td>
                </tr>
            </tbody>
        <!--</center>-->
        </table>
    </html:form>
    </logic:present>

    <logic:present name="coordinaciones">
        <div id="tabla" class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th style="font-size: 14px;">
                            <center>
                            CODIGO
                            </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center>
                            NOMBRE
                            </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center>
                            MODIFICAR
                            </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center>
                            ELIMINAR
                            </center>
                        </th>
                    </tr>
                </thead>
                <logic:iterate name="coordinaciones" id="Coord">
                    <tr>
                        <td>
                            <center>
                                <bean:write name="Coord" property="codigo"/>
                            </center>
                        </td>
                        <td>
                            <bean:write name="Coord" property="nombre"/>
                        </td>
                        <td>
                            <center>
                                <html:form action="/editarNombreCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                    <html:hidden name="Coord" property="codigo"/>
                                    <html:submit styleClass="btn btn-primary"
                                                 style="padding: 3px; padding-left: 5px; padding-right: 5px;">
                                        Modificar
                                    </html:submit>
                                </html:form>
                            </center>
                        </td>
                        <td>
                            <center>
                                <html:form action="/eliminaCoordinacionA" onsubmit="return(this)" style="margin: 0px;">
                                    <html:hidden name="Coord" property="codigo"/>
                                    <html:submit styleClass="btn btn-danger" style="padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea eliminar la Coordinación Docente: ${Coord.getNombre()}?')">
                                        Eliminar
                                    </html:submit>
                                </html:form>
                            </center>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>
    </logic:present>
    <logic:notPresent name="coordinaciones">
            <div class="alert alert-warning alert-dismissable" 
                 id="alert-coord">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <p>
                   Para consultar las coordinaciones escoja un decanato y seleccione CONSULTAR.
                </p>
            </div>
    </logic:notPresent>
    </body>
</html>