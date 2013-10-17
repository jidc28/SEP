<%-- 
    Document   : AgregarInfoPDace
    Created on : 17/10/2013, 06:19:38 AM
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
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <div id="body-content">

            <div >
                <div >
                    <img id="banner" src="imagenes/logo.jpg" alt="Inicio">
                </div>
            </div>

            <div id="sidebarL">
                <div class="glossymenu" style="width: 190px">
                    <a style="border-bottom: none;"/>
                    <a class="menuitem" href="noimplementado.jsp">
                        Perfil
                    </a>
                    <a class="menuitem" href="VistaProfesor.jsp">
                        Inicio
                    </a>
                    <a class="menuitem" href="noimplementado.jsp">
                        Contáctenos
                    </a>
                    <a class="menuitem" href="noimplementado.jsp">
                        Ayuda
                    </a>
                    <a class="menuitem" href="cerrarSesion.do" onclick="return confirm('¿Está seguro que desea cerrar sesión?')" >
                        Cerrar Sesión
                    </a>

                </div>
            </div>

            <div id="sidebarR">
                <a href="http://www.usb.ve/">
                    <img width="150" height="50" src="imagenes/somosusb.gif"/>
                </a>
            </div>

            <h4> Gestión de información del profesor </h4>
            <div id="testTable">
                <table border ="0">
                    <tr>
                        <td>
                            UsbId 
                            <html:text disabled="true" name="profesor" property="usbid"/>
                        </td>
                    </tr>
                </table>
                <h4>SINAI</h4>
                <table border ="0" >
                    <thead> 
                        <tr>
                            <th width="155px" align="center">
                                Nombre
                            </th>
                            <th width="155px" align="center">
                                Fecha Inicio
                            </th>
                            <th width="155px" align="center">
                                Fecha Fin
                            </th>
                        </tr>
                    </thead>
                    <logic:iterate name="sinai" id="Sinai">
                        <td width="150px" align="center">
                            <bean:write name="Sinai" property="nombre"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Sinai" property="fecha_inic"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="Sinai" property="fecha_fin"/>
                        </td>
                    </logic:iterate>
                </table>
                <h4>CCT</h4>
                <table border ="0" >
                    <thead> 
                        <tr>
                            <th width="155px" align="center">
                                ID
                            </th>
                            <th width="155px" align="center">
                                Titulo
                            </th>
                            <th width="155px" align="center">
                                Fecha Inicio
                            </th>
                            <th width="155px" align="center">
                                Fecha Fin
                            </th>
                            <th width="155px" align="center">
                                Carrera
                            </th>
                            <th width="155px" align="center">
                                Tipo
                            </th>
                        </tr>
                    </thead>
                    <logic:iterate name="cct" id="CCT">
                        <td width="150px" align="center">
                            <bean:write name="CCT" property="ident"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="CCT" property="titulo"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="CCT" property="fecha_inic"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="CCT" property="fecha_fin"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="CCT" property="carrera"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="CCT" property="tipo"/>
                        </td>
                    </logic:iterate>
                </table>
                <h4>DACE</h4>
                <table border ="0" >
                    <tr>
                        <td>Período a evaluar</td> 
                        <td>
                            <html:form action="/consultaDace">
                                <html:select disabled="false" property="trimestre">
                                <html:option value="EM">Enero-Marzo</html:option>
                                <html:option value="AJ">Abril-Julio</html:option>
                                <html:option value="SD">Septiembre-Diciembre</html:option>
                            </html:select>
                            <html:select disabled="false" property="ano">
                                <html:option value="2003">2003</html:option> 
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
                            <html:hidden name="profesor" property="usbid"/>
                                <html:submit> Consultar </html:submit>
                            </html:form>
                        </td>
                    </tr>
                </table>
                <table border ="0" >
                    <thead>
                        <tr>
                            <th width="155px" align="center">
                                Codigo
                            </th>
                            <th width="155px" align="center">
                                Nombre
                            </th>
                            <th width="155px" align="center">
                                Uno
                            </th>
                            <th width="155px" align="center">
                                Dos
                            </th>
                            <th width="155px" align="center">
                                Tres
                            </th>
                            <th width="155px" align="center">
                                Cuatro
                            </th>
                            <th width="155px" align="center">
                                Cinco
                            </th>
                            <th width="155px" align="center">
                                Retirados
                            </th>
                        </tr>
                    </thead>
                    <logic:iterate name="dace" id="DACE">
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="codigo"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="nombre"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="uno"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="dos"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="tres"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="cuatro"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="cinco"/>
                        </td>
                        <td width="150px" align="center">
                            <bean:write name="DACE" property="retirados"/>
                        </td>
                    </logic:iterate>
                </table>
            </div>

    </body>
</html>