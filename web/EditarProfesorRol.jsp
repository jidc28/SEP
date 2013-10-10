<%-- 
    Document   : EditarUserRol
    Created on : 10/06/2013, 07:43:54 PM
    Author     : admin
--%>

<%@page import="Clases.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%
    Object usbid = session.getAttribute("usbid");
    Object tipousuario = session.getAttribute("tipousuario");
    Object departamento = session.getAttribute("departamento");
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
                                        Bienvenido, administrador
                                        <html:link href="noimplementado.jsp">Perfil</html:link> |
                                        <html:link href="VistaAdministrador.jsp">
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
                    <h1 style="background-color: cornflowerblue;width: 200px;margin-left: auto; margin-right: auto">Cambiar rol de usuario</h1>
                    <div id="content">


                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Usbid</h1>	
                                    </td>
                                    <td>
                                        <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Departamento</h1>	
                                    </td>
                                    <td>
                                        <h1 style="width: 150px;margin-left: auto; margin-right: auto;color: darkblue">Tipo de Usuario</h1>	
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <p style="color: green"> <%=usbid.toString()%></p>	
                                    </td>
                                    <td>
                                        <html:form  action="/cambiarRolProfesor">
                                            <html:hidden property="usbid" value="<%=usbid.toString()%>"/>

                                            <html:select property="departamento">
                                                <html:option value="Administracion de Personal">Administracion de Personal</html:option>
                                                <html:option value="Administracion y Almacen">Administracion y Almacen</html:option>
                                                <html:option value="Admision y Control de Estudios">Admision y Control de Estudios</html:option>
                                                <html:option value="Admision y Control de Estudios (Litoral)">Admision y Control de Estudios (Litoral)</html:option>
                                                <html:option value="Adquisicion y Reproduccion">Adquisicion y Reproduccion</html:option>
                                                <html:option value="Adquisicion y Reproduccion (Litoral)">Adquisicion y Reproduccion (Litoral)</html:option>
                                                <html:option value="Archivo y Estadistica (Litoral)">Archivo y Estadistica (Litoral)</html:option>
                                                <html:option value="Atencion al Usuario">Atencion al Usuario</html:option>
                                                <html:option value="Bienes Nacionales">Bienes Nacionales</html:option>
                                                <html:option value="Bioingenieria">Bioingenieria</html:option>
                                                <html:option value="Biologia Celular">Biologia Celular</html:option>
                                                <html:option value="Biologia Celular y Organismos">Biologia Celular y Organismos</html:option>
                                                <html:option value="Biologia de Organismos">Biologia de Organismos</html:option>
                                                <html:option value="Ciencias de la Tierra">Ciencias de la Tierra</html:option>
                                                <html:option value="Ciencias de los Materiales">Ciencias de los Materiales</html:option>
                                                <html:option value="Ciencias Economicas y Administrativas">Ciencias Economicas y Administrativas</html:option>
                                                <html:option value="Ciencias Politicas">Ciencias Politicas</html:option>
                                                <html:option value="Ciencias Sociales">Ciencias Sociales</html:option>
                                                <html:option value="Ciencia y Tecnologia del Comportamiento">Ciencia y Tecnologia del Comportamiento</html:option>
                                                <html:option value="Compras y Suministros">Compras y Suministros</html:option>
                                                <html:option value="Computacion (Litoral)">Computacion (Litoral)</html:option>
                                                <html:option value="Computacion y Tecnologia de la Informacion">Computacion y Tecnologia de la Informacion</html:option>
                                                <html:option value="Computo Cientifico y Estadistica">Computo Cientifico y Estadistica</html:option>
                                                <html:option value="Contabilidad">Contabilidad</html:option>
                                                <html:option value="Conversion y Transporte de Energia">Conversion y Transporte de Energia</html:option>
                                                <html:option value="Correspondencia">Correspondencia</html:option>
                                                <html:option value="Desarrollo de Personal">Desarrollo de Personal</html:option>
                                                <html:option value="Diseño Arquitectura y Artes Plasticas">Diseño Arquitectura y Artes Plasticas</html:option>
                                                <html:option value="Ejecucion">Ejecucion</html:option>
                                                <html:option value="Electronica y Circuitos">Electronica y Circuitos</html:option>
                                                <html:option value="Estudios Ambientales">Estudios Ambientales</html:option>
                                                <html:option value="Filosofia">Filosofia</html:option>
                                                <html:option value="Finanzas">Finanzas</html:option>
                                                <html:option value="Finanzas (Litoral)">Finanzas (Litoral)</html:option>
                                                <html:option value="Fisica">Fisica</html:option>
                                                <html:option value="Formacion General (Litoral)">Formacion General (Litoral)</html:option>
                                                <html:option value="Formacion General y Ciencias Basicas">Formacion General y Ciencias Basicas</html:option>
                                                <html:option value="Gestion del Capital Humano">Gestion del Capital Humano</html:option>
                                                <html:option value="Idiomas">Idiomas</html:option>
                                                <html:option value="Informacion y Documentacion">Informacion y Documentacion</html:option>
                                                <html:option value="Informacion y Medios">Informacion y Medios</html:option>
                                                <html:option value="Ingenieria Electrica">Ingenieria Electrica</html:option>
                                                <html:option value="Ingenieria y Mantenimiento">Ingenieria y Mantenimiento</html:option>
                                                <html:option value="Ingenieria y Mantenimiento (Litoral)">Ingenieria y Mantenimiento (Litoral)</html:option>
                                                <html:option value="Lengua y Literatura">Lengua y Literatura</html:option>
                                                <html:option value="Matematicas Puras y Aplicadas">Matematicas Puras y Aplicadas</html:option>
                                                <html:option value="Mecanica">Mecanica</html:option>
                                                <html:option value="Musica">Musica</html:option>
                                                <html:option value="Planificacion Urbana">Planificacion Urbana</html:option>
                                                <html:option value="Planta Fisica">Planta Fisica</html:option>
                                                <html:option value="Procesos Biologicos y Bioquimicos">Procesos Biologicos y Bioquimicos</html:option>
                                                <html:option value="Procesos y Sistemas">Procesos y Sistemas</html:option>
                                                <html:option value="Produccion de Impresos">Produccion de Impresos</html:option>
                                                <html:option value="Quimica y Procesos">Quimica y Procesos</html:option>
                                                <html:option value="Recursos Humanos">Recursos Humanos</html:option>
                                                <html:option value="Recursos Humanos (Litoral)">Recursos Humanos (Litoral)</html:option>
                                                <html:option value="Redes">Redes</html:option>
                                                <html:option value="Registro y Control Administrativo">Registro y Control Administrativo</html:option>
                                                <html:option value="Registro y Control Financiero">Registro y Control Financiero</html:option>
                                                <html:option value="Relaciones Laborales">Relaciones Laborales</html:option>
                                                <html:option value="Seguridad y Servicios">Seguridad y Servicios</html:option>
                                                <html:option value="Servicios Audiovisuales">Servicios Audiovisuales</html:option>
                                                <html:option value="Servicios de Red">Servicios de Red</html:option>
                                                <html:option value="Servicios Generales">Servicios Generales</html:option>
                                                <html:option value="Servicios Telefonicos">Servicios Telefonicos</html:option>
                                                <html:option value="Servicos Generales">Servicos Generales</html:option>
                                                <html:option value="Soporte de Operaciones y Sistemas">Soporte de Operaciones y Sistemas</html:option>
                                                <html:option value="Tecnologia de Procesos Biologicos y Bioquimicos">Tecnologia de Procesos Biologicos y Bioquimicos</html:option>
                                                <html:option value="Tecnologia de Servicios">Tecnologia de Servicios</html:option>
                                                <html:option value="Tecnologia Industrial">Tecnologia Industrial</html:option>
                                                <html:option value="Tecnologia Informatica">Tecnologia Informatica</html:option>
                                                <html:option value="Termodinamica y Fenomenos de Transferencia">Termodinamica y Fenomenos de Transferencia</html:option>
                                                <html:option value="Tesoreria">Tesoreria</html:option>
                                                <html:option value="Urbanismo">Urbanismo</html:option>                                           
                                            </html:select>
                                        </td>
                                        <td>
                                            <html:select property="tipousuario">
                                                <html:option value="profesor">Profesor</html:option>
                                                <html:option value="administrador">Administrador</html:option>
                                                <html:option value="departamento">Departamento</html:option>
                                                <html:option value="coordinacion">Coordinación</html:option>
                                                <html:option value="decanato">Decanato</html:option>                                                  
                                            </html:select>
                                        </td>
                                        <td>
                                            <html:submit value="Guardar Cambios"/>
                                        </html:form>	
                                    </td>

                                </tr>

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>

    </div>

</body>
</html>

<%} else {%>
<html>

    <title> hello</title>
</html>
<% }%>

