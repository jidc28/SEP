<%-- 
    Document   : listar_coordinaciones_pagina
    Created on : Mar 18, 2014, 1:43:48 PM
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
    if (tipousuario.equals("decanato")){
%>
    <tiles:insert definition="listarCoordinaciones"/>
<%
   } else {
%>
    <tiles:insert definition="noAutorizado"/>
<%
   }
%>