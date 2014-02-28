<%-- 
    Document   : agrega_coordinacion_pagina
    Created on : Feb 28, 2014, 12:30:37 PM
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
    if (tipousuario.equals("administrador")){
%>
    <tiles:insert definition="agregaCoordinacionAdmin"/>
<%
       } else if (tipousuario.equals("decanato")) {
%>
    <tiles:insert definition="agregaCoordinacionDecanato"/>
<%
   } else {
%>
    <tiles:insert definition="noAutorizado"/>
<%
   }
%>