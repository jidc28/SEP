<%-- 
    Document   : ConsultarDecanatos
    Created on : Jan 15, 2014, 3:34:28 PM
    Author     : smaf
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
        <title>Gestión de Decanatos</title>
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
        <h4> Lista de Decanatos en el sistema:</h4>
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
                            LISTAR COORDINACIONES
                            </center>
                        </th>
                    </tr>
                </thead>
                <logic:iterate name="decanatos" id="Dec">
                    <tr>
                        <td width="20%" align="center">
                            <bean:write name="Dec" property="codigo"/>
                        </td>
                        <td>
                            <bean:write name="Dec" property="nombre"/>
                        </td>
                        <td width="20%" align="center">
                            <html:form action="/editarNombreDecanato" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="Dec" property="codigo"/>
                                <html:submit styleClass="btn btn-default"
                                             style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                    Listar Coordinaciones
                                </html:submit>
                            </html:form>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>

</body>
</html>