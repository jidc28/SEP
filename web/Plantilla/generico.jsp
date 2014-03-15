<%-- 
    Document   : generico
    Created on : Feb 28, 2014, 9:17:06 AM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
    function login(){
        alert("Su sesión ha caducado, vaya a la página de inicio y vuelva a iniciar sesión");
        document.forms['sesion_invalida'].submit();
    }
</script>

<form action="loginCAS.do" name="sesion_invalida"></form>

<tiles:insert attribute="vista"/>