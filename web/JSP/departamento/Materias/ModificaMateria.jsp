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
                        <td style="padding: 3px;" colspan="4">
                            <center>
                                <input class="form-control"
                                       value="<bean:write name='codigo_materias'/>" 
                                       disabled="true"
                                       style="width: 40%; text-align: center; height: 30px;">
                            </center>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-left: 5px;">
                                <html:radio name="materia" property="num1" value="1">
                                    1
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="2">
                                    2
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="3">
                                    3
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="4">
                                    4
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="5">
                                    5
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="6">
                                    6
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="7">
                                    7
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="8">
                                    8
                                </html:radio><br>
                                <html:radio name="materia" property="num1" value="9">
                                    9
                                </html:radio><br>
                            </td>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="num2" value="1">
                                    1
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="2">
                                    2
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="3">
                                    3
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="4">
                                    4
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="5">
                                    5
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="6">
                                    6
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="7">
                                    7
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="8">
                                    8
                                </html:radio><br>
                                <html:radio name="materia" property="num2" value="9">
                                    9
                                </html:radio><br>
                            </td>
                            <td>
                                <html:radio name="materia" property="num3" value="1">
                                    1
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="2">
                                    2
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="3">
                                    3
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="4">
                                    4
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="5">
                                    5
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="6">
                                    6
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="7">
                                    7
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="8">
                                    8
                                </html:radio><br>
                                <html:radio name="materia" property="num3" value="9">
                                    9
                                </html:radio><br>
                            </td>
                            <td>
                                <html:radio name="materia" property="num4" value="1">
                                    1
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="2">
                                    2
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="3">
                                    3
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="4">
                                    4
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="5">
                                    5
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="6">
                                    6
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="7">
                                    7
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="8">
                                    8
                                </html:radio><br>
                                <html:radio name="materia" property="num4" value="9">
                                    9
                                </html:radio><br>         
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
                    <tr style="height: 35px;">
                        <td colspan="4">
                            Número de créditos
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td colspan="4">
                        <center>
                                <html:text property="creditos" name="materia" style="width: 40%; height: 100%;" maxlength="2" errorKey="org.apache.struts.action.ERROR"/>
                        </center>
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