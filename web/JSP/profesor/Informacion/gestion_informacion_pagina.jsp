<%-- 
    Document   : gestion_informacion_pagina
    Created on : Apr 25, 2014, 10:51:51 AM
    Author     : smaf
--%>

<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String tipousuario = usuario.getTipousuario();
    if (tipousuario.equals("profesor")){
%>
    <tiles:insert definition="gestionarInformacion"/>
<%
   } else {
%>
    <tiles:insert definition="noAutorizado"/>
<%
   }
%>