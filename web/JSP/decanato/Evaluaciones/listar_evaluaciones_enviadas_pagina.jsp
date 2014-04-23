<%-- 
    Document   : listar_evaluaciones_enviadas
    Created on : Apr 17, 2014, 5:10:15 PM
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
    <tiles:insert definition="listarEvaluacionesEnviadasCoordinaciones"/>
<%
   } else {
%>
    <tiles:insert definition="noAutorizado"/>
<%
   }
%>