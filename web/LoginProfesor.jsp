<%-- 
    Document   : success
    Created on : 30/05/2013, 01:00:49 PM
    Author     : Langtech
--%>

<% Object nombreusuario = session.getAttribute("nombreusuario");
    if (nombreusuario != "") {%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login PROFESOR</title>
    </head>
    <body>
        <h1>Congratulations!</h1>

        <p>You have successfully logged in.</p>

        <p>Your usbid is: <%=nombreusuario.toString()%>.</p>

        <html:link action="/cerrarSesion" onclick="return confirm('¿Está seguro que desea cerrar sesión?')">
        <p align ="center">Cerrar Sesión</p>
        </html:link>
    </body>
</html>

<%} else {%>
<html>

    <title> hello</title>
</html>
<% }%>

