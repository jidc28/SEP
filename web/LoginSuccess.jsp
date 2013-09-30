<%-- 
    Document   : success
    Created on : 30/05/2013, 01:00:49 PM
    Author     : Langtech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Success</title>
    </head>
    <body>
        <h1>Congratulations!</h1>

        <p>You have successfully logged in.</p>

        <p>Your usbid is: <bean:write name="LoginForm" property="usbid" />.</p>

    </body>
</html>

