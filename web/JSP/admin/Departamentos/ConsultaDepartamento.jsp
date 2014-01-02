<%-- 
    Document   : ConsultaDepartamentoDecanato
    Created on : 03/12/2013, 11:01:38 PM
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
        <title>Gestión de Departamentos</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <logic:present name="success">
            <div class="alert alert-success" id="alert">
                Departamento registrado exitosamente.
            </div>
        </logic:present>
        <logic:present name="modificacion">
            <div class="alert alert-success" id="alert">
                Departamento modificado exitosamente.
            </div>
        </logic:present>
        <logic:present name="falla">
            <div class="alert alert-danger" id="alert">
                Departamento no fue modificado exitosamente.
            </div>
        </logic:present>

        <h4 style="margin-top: 50px;"> Lista de Departamentos
            <logic:present name="decanato_actual">
                del <bean:write name="decanato_actual" property="nombre"/>:
            </logic:present>
        </h4>
        <br>
        <div class="table-responsive">
            <table border="0" style="margin: auto" class="table-striped">
                <thead>
                    <tr>
                        <th>
                        <center>Usbid</center>
                        </th>
                        <th width='30%'>
                        <center>Nombre</center>
                        </th>
                        <th>
                        <center>Editar</center>
                        </th>
                        <th>
                        <center>Eliminar</center>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="departamentos" id="Dep">
                        <tr>
                            <td width="20%" align="center">
                                <bean:write name="Dep" property="codigo"/>
                            </td>
                            <td width="20%" align="center">
                                <bean:write name="Dep" property="nombre"/>
                            </td>
                            <td>
                                <html:form  action="/irEditarNombreDepartamento" onsubmit="return(this)" style="margin: 0px;">
                                    <html:hidden name="Dep" property="codigo" />
                                    <center>
                                        <button type="button" class="btn btn-primary" 
                                                style="padding-bottom: 1px; padding-top: 1px; padding-left: 2px; padding-right: 2px;">
                                            <html:image src="imagenes/edit-img.png" style="height: 27px;"/>
                                        </button>
                                    </center>
                                </html:form>
                            </td>
                            <td>
                                <html:form action="/irEditarNombreDepartamento" onsubmit="return(this)" style="margin: 0px;">
                                    <html:hidden name="Dep" property="codigo"/>
                                    <center>
                                        <html:submit styleClass="btn btn-danger" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                     onclick="javascript: return confirm('¿Está seguro de que desea eliminar el departamento de este decanato?')"
                                                     disabled="true">
                                            Eliminar
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
