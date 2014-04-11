<%-- 
    Document   : NoAutorizado
    Created on : Feb 27, 2014, 7:06:15 PM
    Author     : smaf
--%>

<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<center>
    <div class="alert alert-danger" 
         id="alert" style="margin-top: 50px;">
        <p>
            NO AUTORIZADO <br>
            Usted no está autorizado para acceder a esta funcionalidad. 
            Para volver a la página anterior presione 
            <a onclick="window.history.back()" style="color: #b94a48;">
                <span class="glyphicon glyphicon-circle-arrow-left"></span>
            </a>
        </p>
    </div>
</center>