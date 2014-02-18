<%-- 
    Document   : AgregarCoordinacion
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<logic:present name="codigo_incorrecto">
    <div class="alert alert-danger" id="alert">
        Codigo de materia <bean:write name="codigo_incorrecto"/> incorrecto intente de nuevo.
    </div>
</logic:present>

<logic:present name="campos_vacios">
    <div class="alert alert-danger" id="alert">
        Los campos <strong>nombre de la materia</strong> y <strong>número de créditos</strong> 
        son obligatorios.
    </div>
</logic:present>

<logic:present name="creditos_incorrecto">
    <div class="alert alert-danger" id="alert">
        Introduzca un número de créditos correcto. 
        <strong><bean:write name="creditos_incorrecto"/></strong> no es un número.
    </div>
</logic:present>

<h4>Registro de Materias</h4>
<html:form action="/modificaMateria" method="POST" acceptCharset="ISO-8859-1" enctype="multipart/form-data" onsubmit="return(this)">
    <table border="0" style="margin-top: 50px;">
        <tbody>
            <tr style="height: 35px;">
                <td style="color: black" colspan="11">
                    <strong>Codigo de la Materia </strong>
                </td>
            </tr>
            <tr>
                <td style="padding-left: 5px;">
                    <html:radio name="materia" property="cod1" value="BC"
                                style="margin: 0px;">
                        BC
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="BO"
                                style="margin: 0px;">
                        BO
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="CC"
                                style="margin: 0px;">
                        CC
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="CE"
                                style="margin: 0px;">
                        CE
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="CI"
                                style="margin: 0px;">
                        CI
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="CS"
                                style="margin: 0px;">
                        CS
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="CT"
                                style="margin: 0px;">
                        CT
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="DU"
                                style="margin: 0px;">
                        DU
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="EA"
                                style="margin: 0px;">
                        EA
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="EC"
                                style="margin: 0px;">
                        EC
                    </html:radio><br>
                </td>
                <td style="padding-left: 5px;">
                    <html:radio name="materia" property="cod1" value="EG"
                                style="margin: 0px;">
                        EG
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="EP"
                                style="margin: 0px;">
                        EP
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="ID"
                                style="margin: 0px;">
                        ID
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="FL"
                                style="margin: 0px;">
                        FL
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="FS"
                                style="margin: 0px;">
                        FS
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="LL"
                                style="margin: 0px;">
                        LL
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="MA"
                                style="margin: 0px;">
                        MA
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="MC"
                                style="margin: 0px;">
                        MC
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="MT"
                                style="margin: 0px;">
                        MT
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="PB"
                                style="margin: 0px;">
                        PB
                    </html:radio><br>
                </td>
                <td style="padding-left: 5px;">
                    <html:radio name="materia" property="cod1" value="PS"
                                style="margin: 0px;">
                        PS
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="QM"
                                style="margin: 0px;">
                        QM
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="TF"
                                style="margin: 0px;">
                        TF
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="EE"
                                style="margin: 0px;">
                        EE
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="GC"
                                style="margin: 0px;">
                        GC
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="DA"
                                style="margin: 0px;">
                        DA
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="PL"
                                style="margin: 0px;">
                        PL
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="CM"
                                style="margin: 0px;">
                        CM
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="CO"
                                style="margin: 0px;">
                        CO
                    </html:radio><br>
                    <html:radio name="materia" property="cod1" value="TS"
                                style="margin: 0px;">
                        TS
                    </html:radio><br>
                </td>
                <td style="padding-left: 5px;">
                    <html:radio name="materia" property="cod2" value="A"
                                style="margin: 0px;">
                        A
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="B"
                                style="margin: 0px;">
                        B
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="C"
                                style="margin: 0px;">
                        C
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="D"
                                style="margin: 0px;">
                        D
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="E"
                                style="margin: 0px;">
                        E
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="F"
                                style="margin: 0px;">
                        F
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="G"
                                style="margin: 0px;">
                        G
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="H"
                                style="margin: 0px;">
                        H
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="I"
                                style="margin: 0px;">
                        I
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="J"
                                style="margin: 0px;">
                        J
                    </html:radio><br>
                </td>
                <td style="padding-left: 5px;">
                    <html:radio name="materia" property="cod2" value="K"
                                style="margin: 0px;">
                        K
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="L"
                                style="margin: 0px;">
                        L
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="M"
                                style="margin: 0px;">
                        M
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="N"
                                style="margin: 0px;">
                        N
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="O"
                                style="margin: 0px;">
                        O
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="P"
                                style="margin: 0px;">
                        P
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="Q"
                                style="margin: 0px;">
                        Q
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="R"
                                style="margin: 0px;">
                        R
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="S"
                                style="margin: 0px;">
                        S
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="T"
                                style="margin: 0px;">
                        T
                    </html:radio><br>
                </td>
                <td style="padding-left: 5px; padding-bottom: 80px;">
                    <html:radio name="materia" property="cod2" value="U"
                                style="margin: 0px;">
                        U
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="V"
                                style="margin: 0px;">
                        V
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="W"
                                style="margin: 0px;">
                        W
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="X"
                                style="margin: 0px;">
                        X
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="Y"
                                style="margin: 0px;">
                        Y
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="Z"
                                style="margin: 0px;">
                        Z
                    </html:radio><br>
                </td>
                <td style="padding-left: 5px;">
                    <html:radio name="materia" property="cod2" value="0"
                                style="margin: 0px;">
                        0
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="1"
                                style="margin: 0px;">
                        1
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="2"
                                style="margin: 0px;">
                        2
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="3"
                                style="margin: 0px;">
                        3
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="4"
                                style="margin: 0px;">
                        4
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="5"
                                style="margin: 0px;">
                        5
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="6"
                                style="margin: 0px;">
                        6
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="7"
                                style="margin: 0px;">
                        7
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="8"
                                style="margin: 0px;">
                        8
                    </html:radio><br>
                    <html:radio name="materia" property="cod2" value="9"
                                style="margin: 0px;">
                        9
                    </html:radio><br>
                </td>
                <td style="padding-left: 5px;">
                    <html:radio name="materia" property="num2" value="0"
                                style="margin: 0px;">
                        0
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="1"
                                style="margin: 0px;">
                        1
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="2"
                                style="margin: 0px;">
                        2
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="3"
                                style="margin: 0px;">
                        3
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="4"
                                style="margin: 0px;">
                        4
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="5"
                                style="margin: 0px;">
                        5
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="6"
                                style="margin: 0px;">
                        6
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="7"
                                style="margin: 0px;">
                        7
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="8"
                                style="margin: 0px;">
                        8
                    </html:radio><br>
                    <html:radio name="materia" property="num2" value="9"
                                style="margin: 0px;">
                        9
                    </html:radio><br>
                </td>
                <td>
                    <html:radio name="materia" property="num3" value="0"
                                style="margin: 0px;">
                        0
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="1"
                                style="margin: 0px;">
                        1
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="2"
                                style="margin: 0px;">
                        2
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="3"
                                style="margin: 0px;">
                        3
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="4"
                                style="margin: 0px;">
                        4
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="5"
                                style="margin: 0px;">
                        5
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="6"
                                style="margin: 0px;">
                        6
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="7"
                                style="margin: 0px;">
                        7
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="8"
                                style="margin: 0px;">
                        8
                    </html:radio><br>
                    <html:radio name="materia" property="num3" value="9"
                                style="margin: 0px;">
                        9
                    </html:radio><br>
                </td>
                <td>
                    <html:radio name="materia" property="num4" value="0"
                                style="margin: 0px;">
                        0
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="1"
                                style="margin: 0px;">
                        1
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="2"
                                style="margin: 0px;">
                        2
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="3"
                                style="margin: 0px;">
                        3
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="4"
                                style="margin: 0px;">
                        4
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="5"
                                style="margin: 0px;">
                        5
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="6"
                                style="margin: 0px;">
                        6
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="7"
                                style="margin: 0px;">
                        7
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="8"
                                style="margin: 0px;">
                        8
                    </html:radio><br>
                    <html:radio name="materia" property="num4" value="9"
                                style="margin: 0px;">
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
                    <strong> Nombre de la Materia </strong> 
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
</tbody>
</table>
<p style="text-align: center; margin-top: 10px;">
    <html:hidden name="materia" property="viejoCodigo"/>
    <html:hidden name="materia" property="comentarios" value="null"/>
    <html:submit styleClass="btn btn-success">
        Modificar
    </html:submit>
    <html:reset styleClass="btn btn-default" value="Limpiar"/>
</html:form>
</p>