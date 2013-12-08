<%-- 
    Document   : AgregarCoordinacion
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
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
        <title>Registro de Materias</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>

        <h4>Registro de Materias</h4>

        <html:form action="/modificaMateria" method="POST" acceptCharset="ISO-8859-1" enctype="multipart/form-data" onsubmit="return(this)">
            <table border="0" style="margin-top: 50px;">
                <tbody>
                    <tr style="height: 35px;">
                        <td style="color: black" colspan="7">Codigo de la Materia</td>
                    </tr>
                    <tr>
                        <td style="padding-bottom: 55px; padding-left: 5px;">
                            <input type="radio" name="1"> A <br>
                            <input type="radio" name="1"> B <br>
                            <input type="radio" name="1"> C <br>
                            <input type="radio" name="1"> D <br>
                            <input type="radio" name="1"> E <br>
                            <input type="radio" name="1"> F <br>
                        </td>
                        <td style="padding-bottom: 55px; padding-left: 5px;">
                            <input type="radio" name="2"> A <br>
                            <input type="radio" name="2"> B <br>
                            <input type="radio" name="2"> C <br>
                            <input type="radio" name="2"> D <br>
                            <input type="radio" name="2"> E <br>
                            <input type="radio" name="2"> F <br>
                        </td>
                        <td style="padding-bottom: 55px; padding-left: 5px;">
                            <input type="radio" name="3"> A <br>
                            <input type="radio" name="3"> B <br>
                            <input type="radio" name="3"> C <br>
                            <input type="radio" name="3"> D <br>
                            <input type="radio" name="3"> E <br>
                            <input type="radio" name="3"> F <br>
                        </td>
                        <td style="padding-left: 5px;">
                            <input type="radio" name="4"> 1 <br>
                            <input type="radio" name="4"> 2 <br>
                            <input type="radio" name="4"> 3 <br>
                            <input type="radio" name="4"> 4 <br>
                            <input type="radio" name="4"> 5 <br>
                            <input type="radio" name="4"> 6 <br>
                            <input type="radio" name="4"> 7 <br>
                            <input type="radio" name="4"> 8 <br>
                            <input type="radio" name="4"> 9 <br>
                        </td>
                        <td style="padding-left: 5px;">
                            <input type="radio" name="5"> 1 <br>
                            <input type="radio" name="5"> 2 <br>
                            <input type="radio" name="5"> 3 <br>
                            <input type="radio" name="5"> 4 <br>
                            <input type="radio" name="5"> 5 <br>
                            <input type="radio" name="5"> 6 <br>
                            <input type="radio" name="5"> 7 <br>
                            <input type="radio" name="5"> 8 <br>
                            <input type="radio" name="5"> 9 <br>
                        </td>
                        <td>
                            <input type="radio" name="6"> 1 <br>
                            <input type="radio" name="6"> 2 <br>
                            <input type="radio" name="6"> 3 <br>
                            <input type="radio" name="6"> 4 <br>
                            <input type="radio" name="6"> 5 <br>
                            <input type="radio" name="6"> 6 <br>
                            <input type="radio" name="6"> 7 <br>
                            <input type="radio" name="6"> 8 <br>
                            <input type="radio" name="6"> 9 <br>
                        </td>
                        <td style="padding-left: 5px;">
                            <input type="radio" name="4"> 1 <br>
                            <input type="radio" name="4"> 2 <br>
                            <input type="radio" name="4"> 3 <br>
                            <input type="radio" name="4"> 4 <br>
                            <input type="radio" name="4"> 5 <br>
                            <input type="radio" name="4"> 6 <br>
                            <input type="radio" name="4"> 7 <br>
                            <input type="radio" name="4"> 8 <br>
                            <input type="radio" name="4"> 9 <br>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7" style="color:firebrick">
                            <html:errors property="codigo"/>
                        </td>
                    </tr>

                    <tr  style="height: 35px;">
                        <td style="color: black" colspan="7">Nombre de la Materia</td>
                    </tr>
                    <tr  style="height: 35px;">
                        <td colspan="7">
                            <html:text property="nombre" name="materia" style="width: 100%; height: 100%;" maxlength="50" errorKey="org.apache.struts.action.ERROR"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color:firebrick">
                            <html:errors property="nombre"/>
                        </td>
                    <tr>
                        <td colspan="2" style="color:firebrick">
                            <html:errors property="registro"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <p style="text-align: center; margin-top: 10px;">
                <html:hidden name="materia" property="viejoCodigo"/>
                <html:submit styleClass="btn btn-success">
                    Modificar
                </html:submit>
                <html:reset styleClass="btn btn-default" value="Limpiar"/>
            </p>
        </html:form>

    </body>
</html>