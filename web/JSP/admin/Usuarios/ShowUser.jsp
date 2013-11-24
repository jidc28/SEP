<%-- 
    Document   : ShowUser
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
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.js"></script>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>SEP - Gestion de Planillas de Evaluacion</title>
    </head>
    <body>

        <h4>Lista de usuarios del sistema:</h4>
        
        <ul class="nav nav-tabs" style="height: 41px;">
            <li><a href="#decanato" data-toggle="tab">Decanatos</a></li>
            <li><a href="#coordinaciones" data-toggle="tab">Coordinaciones</a></li>
            <li><a href="#departamentos" data-toggle="tab">Departamentos</a></li>
            <li><a href="#profesores" data-toggle="tab">Profesores</a></li>
        </ul>
        
        <div class="tab-content">
            <div class="tab-pane" id="decanato">
                <table border="0" style="margin: 0px; margin-top: 10px; width: 750px;" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th width="155px" align="center">
                            <font color = "white">
                                UsbID
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Nombre
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Estado
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Modificar
                            <font/>
                        </th>
                    </tr>
                </thead>

                <logic:iterate name="decanatos" id="Decanatos">
                    <tr>
                        <td width="150px" align="center">
                            <bean:write name="Decanatos" property="usbid"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Decanatos" property="departamento"/>
                        </td>
                        <td width="150px" align="center">
                            Visible
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Decanatos" property="tipousuario"/>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
            </div>
            <div class="tab-pane" id="coordinaciones">
                 <table border="0" style="margin: 0px; margin-top: 10px; width: 750px;" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th width="155px" align="center">
                            <font color = "white">
                                UsbID
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Nombre
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Estado
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Modificar
                            <font/>
                        </th>
                    </tr>
                </thead>

                <logic:iterate name="coordinaciones" id="Coordinaciones">
                    <tr>
                        <td width="150px" align="center">
                            <bean:write name="Coordinaciones" property="usbid"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Coordinaciones" property="departamento"/>
                        </td>
                        <td width="150px" align="center">
                            Visible
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Coordinaciones" property="tipousuario"/>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
            </div>
            <div class="tab-pane" id="departamentos">
                <table border="0" style="margin: 0px; margin-top: 10px; width: 750px;" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th width="155px" align="center">
                            <font color = "white">
                                UsbID
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Nombre
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Estado
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Modificar
                            <font/>
                        </th>
                    </tr>
                </thead>

                <logic:iterate name="departamentos" id="Departamentos">
                    <tr>
                        <td width="150px" align="center">
                            <bean:write name="Departamentos" property="usbid"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Departamentos" property="departamento"/>
                        </td>
                        <td width="150px" align="center">
                            Visible
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Departamentos" property="tipousuario"/>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
            </div>
            <div class="tab-pane" id="profesores">
            <table border="0" style="margin: 0px; margin-top: 10px; width: 750px;" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th width="155px" align="center">
                            <font color = "white">
                                UsbID
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Nombre
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Departamento
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Estado
                            <font/>
                        </th>
                        <th width="155px" align="center">
                            <font color = "white">
                                Modificar
                            <font/>
                        </th>
                    </tr>
                </thead>

                <logic:iterate name="profesores" id="Profesores">
                    <tr>
                        <td width="150px" align="center">
                            <bean:write name="Profesores" property="usbid"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Profesores" property="tipousuario"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Profesores" property="departamento"/>
                        </td>
                        <td width="150px" align="center">
                            Visible
                        </td>
                        <td width="150px" align="center">
                            
                        </td>
                    </tr>
                </logic:iterate>
            </table>
            </div>
        </div>
</body>
</html>