<%-- 
    Document   : listar_evaluaciones_pendientes_pagina
    Created on : Feb 28, 2014, 10:19:27 AM
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
    if (tipousuario.equals("coordinacion") || 
            tipousuario.equals("departamento")){
%>
    <tiles:insert definition="listarEvaluacionesPendientes"/>
<%
   } else {
%>
    <tiles:insert definition="noAutorizado"/>
<%
   }
%>