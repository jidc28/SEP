<%-- 
    Document   : ModificaSolicitudApertura
    Created on : Jan 8, 2014, 4:12:14 PM
    Author     : smaf
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
        <title>Gestión de Materias</title>
    </head>
    <body>
        <script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
        
        <logic:present name="codigo_incorrecto">
            <div class="alert alert-danger" id="alert">
                Codigo de materia <bean:write name="codigo_incorrecto"/> incorrecto intente de nuevo.
            </div>
        </logic:present>


        <h4>Registro de Materias</h4>
         <html:form action="/finalizarSolicitudApertura" method="POST" acceptCharset="ISO-8859-1" enctype="multipart/form-data" onsubmit="return(this)">
            <table border="0" style="margin-top: 50px;">
                <tbody>
                    <tr style="height: 35px;">
                        <td style="color: black" colspan="11">
                            <strong>Justificación</strong>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td colspan="11">
                            <p style="font-size: 14px;">
                                <bean:write name="materia" property="mensaje"/>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td style="color: black" colspan="11">
                            <strong>Codigo de la Materia</strong>
                        </td>
                    </tr>
                    <tr>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="cod1" value="BC">
                                    BC
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="BO">
                                    BO
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="CC">
                                    CC
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="CE">
                                    CE
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="CI">
                                    CI
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="CS">
                                    CS
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="CT">
                                    CT
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="DU">
                                    DU
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="EA">
                                    EA
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="EC">
                                    EC
                                </html:radio><br>
                            </td>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="cod1" value="EG">
                                    EG
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="EP">
                                    EP
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="ID">
                                    ID
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="FL">
                                    FL
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="FS">
                                    FS
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="LL">
                                    LL
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="MA">
                                    MA
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="MC">
                                    MC
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="MT">
                                    MT
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="PB">
                                    PB
                                </html:radio><br>
                            </td>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="cod1" value="PS">
                                    PS
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="QM">
                                    QM
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="TF">
                                    TF
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="EE">
                                    EE
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="1">
                                    1
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="GC">
                                    GC
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="DA">
                                    DA
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="PL">
                                    PL
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="CM">
                                    CM
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="CO">
                                    CO
                                </html:radio><br>
                            </td>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="cod1" value="TS">
                                    TS
                                </html:radio><br>
                                <html:radio name="materia" property="cod1" value="8">
                                    8
                                </html:radio><br>
                            </td>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="cod2" value="A">
                                    A
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="B">
                                    B
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="C">
                                    C
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="D">
                                    D
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="E">
                                    E
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="F">
                                    F
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="G">
                                    G
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="H">
                                    H
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="I">
                                    I
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="J">
                                    J
                                </html:radio><br>
                            </td>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="cod2" value="K">
                                    K
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="L">
                                    L
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="M">
                                    M
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="N">
                                    N
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="O">
                                    O
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="P">
                                    P
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="Q">
                                    Q
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="R">
                                    R
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="S">
                                    S
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="T">
                                    T
                                </html:radio><br>
                            </td>
                            <td style="padding-left: 5px;">
                                <html:radio name="materia" property="cod2" value="U">
                                    U
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="V">
                                    V
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="W">
                                    W
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="X">
                                    X
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="Y">
                                    Y
                                </html:radio><br>
                                <html:radio name="materia" property="cod2" value="Z">
                                    Z
                                </html:radio><br>
                            </td>
                        <td style="padding-left: 5px;">
                                <html:radio name="materia" property="num1" value="0">
                                    0
                                </html:radio><br>
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
                                <html:radio name="materia" property="num2" value="0">
                                    0
                                </html:radio><br>
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
                                <html:radio name="materia" property="num3" value="0">
                                    0
                                </html:radio><br>
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
                                <html:radio name="materia" property="num4" value="0">
                                    0
                                </html:radio><br>
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
                        <td colspan="11" style="color:firebrick">
                            <html:errors property="codigo"/>
                        </td>
                    </tr>

                    <tr  style="height: 35px;">
                        <td style="color: black" colspan="11">
                            <strong>Nombre de la Materia</strong>
                        </td>
                    </tr>
                    <tr  style="height: 35px;">
                        <td colspan="11">
                            <html:text property="nombre" name="materia" style="width: 100%; height: 100%;" maxlength="50" errorKey="org.apache.struts.action.ERROR"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" style="color:firebrick">
                            <html:errors property="nombre"/>
                        </td>
                    <tr>
                        <td colspan="11" style="color:firebrick">
                            <html:errors property="registro"/>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td colspan="11">
                            <strong> Número de créditos </strong>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td colspan="11">
                        <center>
                                <html:text property="creditos" name="materia" 
                                           style="width: 20%; height: 100%; text-align: center;" 
                                           maxlength="2" 
                                           errorKey="org.apache.struts.action.ERROR"/>
                        </center>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td colspan="11">
                            <strong> Comentarios: </strong>
                        </td>
                    </tr>
                    <tr style="height: 35px;">
                        <td colspan="11">
                        <center>
                            <html:textarea name="materia" property="comentarios"
                                       rows="5" style="width: 100%;">
                            </html:textarea>
                        </center>
                        <td>
                    </tr>
                    <tr style="height: 35px;">
                        <td colspan="11">
                        <center>
                            <html:radio name="materia" property="solicitud" value="no"
                                        style="margin: 3px; margin-top: 0px;">
                                Aprobada
                            </html:radio>
                            <html:radio name="materia" property="solicitud" value="si"
                                        style="margin: 3px; margin-top: 0px;">
                                Negada
                            </html:radio><br> 
                        </center>
                        <td>
                    </tr>
                </tbody>
            </table>
            <html:hidden name="materia" property="viejoCodigo"/>
            <html:submit styleClass="btn btn-info">
                Finalizar
            </html:submit>
         </html:form>
    </body>
</html>