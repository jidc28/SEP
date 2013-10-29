<%-- 
    Document   : EditarStatusCarrera
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <div id="testTable">
            <table border="0" style="margin: auto" class="altrowstable" id="alternatecolor">
                <thead>
                    <tr>
                        <th width="155px" align="center">
                            Codigo
                        </th>
                        <th width="155px" align="center">
                            Estado
                        </th>
                        <th width="155px" align="center">
                            Modificar
                        </th>
                    </tr>
                </thead>
                <tr>
                    <html:form action="/cambiarStatusCarreraA" acceptCharset="ISO-8859-1" onsubmit="return(this)">
                        <td width="155px" align="center">
                            <html:hidden name="Carrera" property="codigo"/>
                            <bean:write name="Carrera" property="codigo"/>
                        </td>
                        <td width="155px" align="center">
                            <html:select property="estado">
                                <html:option value="visible">Visible</html:option>
                                <html:option value="oculta">Oculta</html:option>                                                  
                            </html:select>
                        </td>
                        <td>
                            <p style="text-align: center">
                                <html:submit>
                                    Modificar
                                </html:submit>
                            </p>
                        </td>
                    </html:form>
                </tr>
            </table>
        </div>
    </body>
</html>
