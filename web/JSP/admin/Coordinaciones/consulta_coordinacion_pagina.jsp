<%-- 
    Document   : consulta_coordinacion_pagina
    Created on : Feb 28, 2014, 12:35:29 PM
    Author     : smaf
--%>

<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("usuario") == null) {
%>
<tiles:insert definition="base"/>
<script>
    login()
</script>
<%
    } else {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();
        if (tipousuario.equals("administrador")) {
%>
<tiles:insert definition="consultaCoordinacionAdmin"/>
<%
        } else if (tipousuario.equals("decanato")) {
%>
<tiles:insert definition="consultaCoordinacionDecanato"/>
<%
        } else {
%>
<tiles:insert definition="noAutorizado"/>
<%        
        }
    }
%>