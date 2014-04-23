<%-- 
    Document   : Login
    Created on : 30/05/2013, 08:14:08 PM
    Author     : Langtech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Login.css">
        <title>Bienvenidos a Sistema de Evaluacion</title>
    </head>
    <body>     
        <div>
            <img id="encabezado" src="imagenes/CAS-img.png" alt="Inicio">
        </div>

    <center>
        <div id="contenido">
            <table style="margin-right: 310px;">
                <tbody>
                    <tr>
                        <td>
                            <div class="box">
                                <h2>Enter your USBID and Password</h2>
                                <html:form action="/login" acceptCharset="ISO=8859-1" onsubmit="return(this)">
                                    <table style="width: 260px;">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <label>USBID:</label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><html:text styleClass="texto" property="usbid" /></td>
                                            </tr>
                                            <tr>
                                                <td style="color: red">
                                                    <label style="color: red;">
                                                        <html:errors property="usbid" />
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label>Password:</label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><html:password styleClass="texto" property="contrasena" /></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label style="color: red;">
                                                        <html:errors property="contrasena" />
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" id="check"><label>Warn me before logging me into other sites.</label>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="bottom" align="left">
                                        <html:submit styleClass="login">
                                            LOGIN
                                        </html:submit>
                                        <html:reset styleClass="clear" value="clear" />
                                    </div>
                                </html:form>
                            </div>
                        </td>
                        <td style="padding-bottom: 120px; padding-left: 20px;">
                            <div id="side">
                                <p>For security reasons, please Log Out and Exit your web browser 
                                    when you are done accessing services that require 
                                    authentication!
                                </p>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </center>

    <div align="center">
        <div style="margin-top: 50px; width: 1000px; margin-bottom: 0px; border-bottom: 1px solid #ddd;">
            <table style="margin-top: 20px;">
                <tbody>
                    <tr>
                        <td> 
                            <div style="padding-right: 15px;">
                                <img style="margin-bottom: 20px;" src="imagenes/cebolla.jpg"/>
                            </div>
                        </td>
                        <td align="left">
                            <p style="margin: 0px;">
                                <a href="http://www.usb.ve/home/node/68">e-virtual</a> | 
                                <a href="https://webmail.usb.ve/">Correo</a> | 
                                <a href="https://esopo.usb.ve/">esopo</a> | 
                                <a href="http://www.youtube.com/canalusb">canalUSB</a> | 
                                <a href="http://www.usb.ve/agenda.php">Agenda Cultural</a> | 
                                <a href="http://usbnoticias.info/">USBnoticias</a> | 
                                <a href="http://www.usb.ve/home/node/55">Calendario</a> |
                            </p>
                            <p style="margin: 0px;"> 
                                Sede Sartenejas, Baruta, Edo. Miranda - 
                                Apartado 89000 - Cable Unibolivar - 
                                Caracas Venezuela. Teléfono +58 0212-9063111
                            </p>
                            <p style="margin: 0px;"> 
                                Sede Litoral, Camurí Grande, Edo. Vargas Parroquia Naiguatá. 
                                Teléfono +58 0212-9069000 
                            </p>
                            <p style="margin: 0px;"> 
                                Diseñada y desarrollada por la Dirección de 
                                Servicios Multimedia 
                                <a href="mailto:webmaster@usb.ve">webmaster@usb.ve</a>
                            </p>  
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div style="width: 1000px; margin-bottom: 30px;">
            <p style="margin-top: 5px; text-align: right; color: #999"> 
                Copyright © 2013 Langtech. All rights reserved.
            </p>
        </div>
    </div>
</body>
</html>
