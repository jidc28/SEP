<%-- 
    Document   : resultadosMaterias
    Created on : 08/01/2014, 06:08:41 PM
    Author     : jidc28
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
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap2.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/css/bootstrap.min.css">
        <title>Sistema de Evaluación de Profesores</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <h4>
            Información de Materias
        </h4>
        <br>
        UsbID <html:text disabled="true" name="profesor" property="usbid"/>
        </br>      
            
        <div id="tabla" class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <th>
                            Trimestre
                        </th>
                        <th>
                            Año
                        </th>
                        <th>
                            Código
                        </th>
                        <th>
                            Nombre Asignatura
                        </th>
                        <th>
                            N. Estudiantes
                        </th>
                        <th>
                            Nota Prom.
                        </th>
                        <th>
                            N. Aprobados
                        </th>
                        <th>
                            N. Aplazados
                        </th>
                        <th>
                            N. Retirados
                        </th>
                    </thead>
                    <tbody>
                        <logic:iterate name="rendimiento" id="RendimientoProf" >
                            <tr>
                                <td>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="EM">
                                        Ene-Mar
                                    </logic:equal>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="AJ">
                                        Abr-Jul
                                    </logic:equal>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="V">
                                        Intensivo
                                    </logic:equal>
                                    <logic:equal name="rendimientoProf" property="trimestre" value="SD">
                                        Sep-Dic
                                    </logic:equal>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="ano"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="codigo_materia"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="nombre_materia"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="total_estudiantes"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="nota_prom"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="aprobados"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="aplazados"/>
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="retirados"/>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </table>
            <html:link action="/irAgregarRendimiento">
                <button>
                    Agregar Información de Otra Materia
                </button>
            </html:link>
        </div>


    </body>
</html>
