<%-- 
    Document   : plantilla
    Created on : 15/10/2013, 05:39:21 PM
    Author     : jidc28
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
        <tiles:insert attribute="css"/>
        <tiles:insert attribute="script"/>
    </head>
    <body>
        <div id="body-content">
            <div>
                <tiles:insert attribute="banner"/>
            </div>

            <div id="sidebarL">
                <tiles:insert attribute="sidebarL"/>
            </div>

            <tiles:insert attribute="sidebarR"/>

            <center>
                <div id="content">
                    <tiles:insert attribute="body"/>
                </div>
            </center>

            <div id="footer" style="padding: 0px;">
                <tiles:insert attribute="footer"/> 
            </div>
        </div>
    </body>
</html>