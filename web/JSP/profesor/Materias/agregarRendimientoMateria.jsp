<%-- 
    Document   : agregarRendimientoMateria
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
                    <html:form action="/agregarRendimiento">
                        <tbody>
                            <tr>
                                <td>
                                    Trimestre
                                </td>
                                <td>
                                    <html:select name="rendimientoProf" property="trimestre">
                                        <html:option value="EM">Ene-Mar</html:option>
                                        <html:option value="AJ">Abr-Jul</html:option>
                                        <html:option value="SD">Sep-Dic</html:option>
                                        <html:option value="V">Intensivo</html:option>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Año
                                </td>
                                <td>
                                    <html:select disabled="false" property="ano" style="width: 100px;">
                                        <html:option value="2004">2004</html:option> 
                                        <html:option value="2005">2005</html:option> 
                                        <html:option value="2006">2006</html:option> 
                                        <html:option value="2007">2007</html:option> 
                                        <html:option value="2008">2008</html:option> 
                                        <html:option value="2009">2009</html:option> 
                                        <html:option value="2010">2010</html:option> 
                                        <html:option value="2011">2011</html:option> 
                                        <html:option value="2012">2012</html:option> 
                                        <html:option value="2013">2013</html:option> 
                                        <html:option value="2014">2014</html:option> 
                                        <html:option value="2015">2015</html:option> 
                                        <html:option value="2016">2016</html:option> 
                                        <html:option value="2017">2017</html:option> 
                                        <html:option value="2018">2018</html:option> 
                                        <html:option value="2019">2019</html:option> 
                                        <html:option value="2020">2020</html:option> 
                                        <html:option value="2021">2021</html:option> 
                                        <html:option value="2022">2022</html:option> 
                                        <html:option value="2023">2023</html:option> 
                                        <html:option value="2024">2024</html:option> 
                                        <html:option value="2025">2025</html:option> 
                                        <html:option value="2026">2026</html:option> 
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Código
                                </td>
                                <td>
                                    <bean:write name="RendimientoProf" property="codigo_materia"/>
                                </td>
                            </tr>
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
                        </tbody>
                    </html:form>
                </table>
            <html:link action="/agregarRendimientoProfesor">
                <button>
                    Agregar Información de Otra Materia
                </button>
            </html:link>
        </div>


    </body>
</html>
