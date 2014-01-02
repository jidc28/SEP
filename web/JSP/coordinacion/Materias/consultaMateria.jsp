<%-- 
    Document   : consultaMateria
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
        <title>Gestión de Materias</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        
        <logic:present name="materia_vinculada">
            <div class="alert alert-success" id="alert">
                Materia vinculada exitosamente.
            </div>
        </logic:present>
        <logic:present name="materia_desvinculada">
            <div class="alert alert-success" id="alert">
                Materia desvinculada exitosamente.
            </div>
        </logic:present>
        <logic:present name="materia_falla_vinculado">
            <div class="alert alert-danger" id="alert">
                Materia no fue vinculada exitosamente.
            </div>
        </logic:present>
        <logic:present name="materia_falla_desvinculado">
            <div class="alert alert-danger" id="alert">
                Materia no fue desvinculada exitosamente.
            </div>
        </logic:present>
        
        <h4> Lista de Materias  
            <logic:present name="dpto_seleccionado">
                <br> 
                <bean:write name="dpto_seleccionado" property="nombre"/>
            </logic:present>
            <logic:notPresent name="dpto_seleccionado">
                Vinculadas:
            </logic:notPresent>
        </h4>
        <div id="tabla" class="table-responsive">
            <table class="table table-striped">
                
                <logic:notPresent name="materias_vinculadas">
                    <thead>
                        <tr>
                            <th>
                    <center>Codigo</center>
                    </th>
                    <th>
                    <center>Nombre de la Materia</center>
                    </th>
                    <th>
                    <center>Vincular</center>
                    </th>
                    </thead>
                    <tbody>
                        <logic:iterate name="materias" id="mat">
                            <tr>
                                <td align="center">
                                    <bean:write name="mat" property="codigo"/>
                                </td>
                                <td  align="center">
                                    <bean:write name="mat" property="nombre"/>
                                </td>
                                <td>
                                    <html:form action="/vincularMateriaCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                        <html:hidden name="mat" property="codigo" />
                                        <center>
                                            <html:submit styleClass="btn btn-success" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                         onclick="javascript: return confirm('¿Está seguro de que desea vincular esta materia?')">
                                                Vincular
                                            </html:submit>
                                        </center>
                                  </html:form>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </logic:notPresent>
                <logic:present name="materias_vinculadas">
                    <thead>
                        <tr>
                            <th>
                    <center>Codigo</center>
                    </th>
                    <th width="30%">
                    <center>Nombre de la Materia</center>
                    </th>
                    <th>
                    <center>Desvincular</center>
                    </th>
                    </thead>
                    <tbody>
                        <logic:iterate name="materias" id="mat">
                            <tr>
                                <td width="20%" align="center">
                                    <bean:write name="mat" property="codigo"/>
                                </td>
                                <td width="20%" align="center">
                                    <bean:write name="mat" property="nombre"/>
                                </td>
                                <td>
                                    <html:form action="/desvincularMateriaCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                        <html:hidden name="mat" property="codigo" />
                                        <center>
                                            <html:submit styleClass="btn btn-danger" style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                         onclick="javascript: return confirm('¿Está seguro de que desea desvincular esta materia?')">
                                                Desvincular
                                            </html:submit>
                                        </center>
                                  </html:form>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </logic:present>
            </table>
        </div>
    </body>
</html>
