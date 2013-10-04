<%-- 
    Document   : AgregarInfoP
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
--%>

<%  Object usbid = session.getAttribute("usbid");

    if (usbid != "") {%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/StylesheetEvalProf.css">
        <title>Gestion de Planillas de Evaluacion</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        <div id="contenedor">

            <div id="encabezado">
                <a title="Inicio" rel="home">
                    <img src="imagenes/logo.jpg" alt="Inicio">
                </a>
            </div>


            <div id="encabezado">
                <div id="menu-principal">          
                    <ul id="menu">
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        Bienvenidos, profesor <%=usbid.toString()%>.
                                        <html:link href="noimplementado.jsp">Perfil</html:link> |
                                        <html:link href="VistaProfesor.jsp">
                                            Inicio
                                        </html:link> |
                                        <html:link href="noimplementado.jsp">
                                            Contáctenos
                                        </html:link> |
                                        <html:link href="noimplementado.jsp">
                                            Ayuda
                                        </html:link> |
                                        <html:link action="/cerrarSesion" onclick="return confirm('¿Está seguro que desea cerrar sesión?')">
                                            Cerrar Sesión
                                        </html:link>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </ul>
                </div>
            </div>

            <div id="cuerpo-principal">

                <div id="contenido-der">
                    <h1>Gestión de información del profesor</h1>

                    <html:form action="/guardarModificarInfoP">
                        <div id="welcome">
                            <table border="0">
                                <tbody>
                                <td><html:hidden property="usbid" value="<%=usbid.toString()%>"/></td>
                                <tr>
                                    <td>USBID:</td>
                                    <td><html:text disabled="true" property="usbid" value="<%=usbid.toString()%>"/></td>
                                </tr>
                            </table>
                        </div>

                        <h1>Cursos dictados durante el período a evaluar</h1>
                        <div id="welcome">

                            <table border="0">
                                <tr>

                                    <td>Período a evaluar</td> 
                                    <td>
                                        <html:select value="trimestre" disabled="true" property="trimestre">
                                            <html:option value="EM">Enero-Marzo</html:option>
                                            <html:option value="AJ">Abril-Julio</html:option>
                                            <html:option value="INT">Intensivo</html:option>
                                            <html:option value="SD">Abril-Julio</html:option>
                                        </html:select>
                                        <html:select  value="ano" disabled="true" property="trimestreAno">
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



                                </tbody>
                            </table>
                        </div>

                        <h1>Cursos dictados en este período</h1>
                        <div id="welcome">

                            <table border="0">


                                <thead style='width:800px;'>
                                    <tr><th>Codigo de Asignatura</th>
                                        <th>Denominación</th>
                                        <th>Nota 1</th>
                                        <th>Nota 2</th>
                                        <th>Nota 3</th>
                                        <th>Nota 4</th>
                                        <th>Nota 5</th>
                                        <th>Retirados</th>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr>
                                        <td><input style='width:100px;' disabled="true" type="text" name="codigo" value="Codigo1"></td>
                                        <td><input style='width:100px;' disabled="true" type="text" name="denominacion" value="Denominacion1"></td>
                                        <td><input style='width:70px;' disabled="true" type="text" name="nota1" value="3"></td>
                                        <td><input style='width:70px;' disabled="true" type="text" name="nota2" value="3"></td>
                                        <td><input style='width:70px;' disabled="true" type="text" name="nota3" value="3"></td>
                                        <td><input style='width:70px;' disabled="true" type="text" name="nota4" value="3"></td>
                                        <td><input style='width:70px;' disabled="true" type="text" name="nota5" value="3"></td>
                                        <td><input style='width:70px;' disabled="true" type="text" name="retiradosCurso" value="10"></td>
                                    </tr>

                                </tbody>

                            </table>
                        </div>

                        <h1>Tutoría de Proyecto de Grado</h1>
                        <div id="welcome">



                            <table border="0">
                                <thead style='width:800px;'>
                                    <tr><th>Título del trabajo</th>
                                        <th>Estudiante(s)</th>
                                        <th>Carrera</th>
                                        <th>Fecha<i>(I-F)</i></th>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr >
                                        <td><input style='width:200px;' disabled="true" type="text" name="tituloProyecto" value="TituloProyecto1"></td>
                                        <td><input style='width:200px;' disabled="true" type="text" name="estudianteProyecto" value="EstudianteProyecto1"></td>
                                        <td>
                                            <select style='width:200px;' name="carreraProyecto" disabled="true">
                                                <option value="i electrica">Ingeniería Eléctrica</option>
                                            </select>
                                        </td>

                                        <td><input style='width:90px;' disabled="true" type="text" name="fechaIniProyecto" value="1991/02/01">
                                            <input style='width:90px;' disabled="true" type="text" name="fechaFinProyecto" value="1991/05/01"></td>
                                    </tr>


                                </tbody>

                            </table>
                        </div>

                        <h1>Tutoría de Cursos en Cooperación Largos</h1>
                        <div id="welcome">



                            <table id="titulos-obtenidos">
                                <thead style='width:800px;'>
                                    <tr><th>Título</th>
                                        <th>Estudiante</th>
                                        <th>Carrera</th>
                                        <th>Fecha<i>(I-F)</i></th>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr >
                                        <td><input style='width:200px;' disabled="true" type="text" name="tituloCoopLargo" value="TituloCoopLargo1"></td>
                                        <td><input style='width:200px;' disabled="true" disabled="true"type="text" name="estudianteCoopLargo" value="EstudianteCoopLargo1"></td>
                                        <td><select style='width:200px;' disabled="true" name="carreraCoopLargo">

                                                <option value="i computacion">Ingeniería de la Computación</option>

                                            </select></td>

                                        <td><input style='width:90px;' disabled="true" type="text" name="fechaIniCoopLargo" value="1993/02/01">
                                            <input style='width:90px;' disabled="true" type="text" name="fechaFinCoopLargo" value="1993/05/01"></td>
                                    </tr>


                                </tbody>

                            </table>
                        </div>

                        <h1>+ Tutoría de Cursos en Cooperación Cortos</h1>

                        <div id="welcome">

                            <table id="titulos-obtenidos">
                                <thead style='width:800px;'>
                                    <tr><th>Título</th>
                                        <th>Estudiante</th>
                                        <th>Carrera</th>
                                        <th>Fecha<i>(I-F)</i></th>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr >
                                        <td><input style='width:200px;' disabled="true" type="text" name="tituloCoopCorto" value="TituloCoopCorto1"></td>
                                        <td><input style='width:200px;' disabled="true" type="text" name="estudianteCoopCorto" value="EstudianteCoopCorto1"></td>
                                        <td><select style='width:200px;' disabled="true" name="carreraCoopCorto">

                                                <option value="lic matematicas">Licenciatura en Matemáticas</option>

                                            </select></td>

                                        <td><input style='width:90px;' disabled="true" type="text" name="fechaIniCoopCorto" value="1995/02/01">
                                            <input style='width:90px;' disabled="true" type="text" name="fechaFinCoopCorto" value="1995/05/01"></td>
                                    </tr>

                                </tbody>

                            </table>

                        </div>

                    </html:form>
                </div>

            </div>

    </body>
</html>

<%} else {%>
<html>

    <title> hello</title>
</html>
<% }%>

