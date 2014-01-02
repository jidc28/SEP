<%-- 
    Document   : consultaDepartamento
    Created on : 08/12/2013, 12:10:31 AM
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
        <!--        <link rel="stylesheet" type="text/css" href="css/css/bootstrap-theme.css">
                <link rel="stylesheet" type="text/css" href="css/css/bootstrap-theme.min.css"> -->
        <title>Sistema de Evaluación de Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        
        <h4> Lista de Departamentos </h4>
        
        <div id="tabla" class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th width="15%">
                <center>
                    Codigo
                </center>
                </th>
                <th>
                <center>
                    Nombre Departamento
                </center>
                </th>
                <th width="15%">
                <center>
                    Materias
                </center>
                </th>
                </thead>
                <tbody>
                    <logic:iterate name="departamentos" id="dep">
                        <tr>
                            <td align="center">
                                <bean:write name="dep" property="codigo"/>
                            </td>
                            <td>
                                <bean:write name="dep" property="nombre"/>
                            </td>
                            <td>
                                <html:form action="/listarMateriasDepto" onsubmit="return(this)" style="margin: 0px;">
                                    <html:hidden name="dep" property="codigo" />
                                    <html:hidden name="dep" property="nombre" />
                                    <center>
                                        <html:submit styleClass="btn btn-default" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                            Listar
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
