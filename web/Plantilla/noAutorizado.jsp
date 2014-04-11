<%-- 
    Document   : noAutorizado
    Created on : Feb 27, 2014, 7:15:50 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <tiles:insert attribute="css"/>
        <tiles:insert attribute="script"/>
    </head>
    <body>
        <div id="body-content">
            <center>
                <div id="content">
                    <tiles:insert attribute="body"/>
                </div>
            </center>
        </div>
    </body>
</html>