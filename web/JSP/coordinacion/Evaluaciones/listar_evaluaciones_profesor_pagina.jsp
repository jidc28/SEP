<%-- 
    Document   : listar_evaluaciones_profesor_pagina
    Created on : Apr 28, 2014, 1:39:19 PM
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
    if (tipousuario.equals("coordinacion")) {
%>
    <tiles:insert definition="listarEvaluacionesProfesorCoordinacion"/>
    
<% 
    } else if (tipousuario.equals("departamento")) {
%>
    <tiles:insert definition="listarEvaluacionesProfesorDepartamento"/>
<%
    } else if (tipousuario.equals("decanato")) {
%>
    <tiles:insert definition="listarEvaluacionesProfesorDecanato"/>
<% 
   } else {
%>
    <tiles:insert definition="noAutorizado"/>
<%
    }
%>
