<%-- 
    Document   : listar_evaluaciones_enviadas_ano
    Created on : Mar 12, 2014, 3:56:01 PM
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
    if (tipousuario.equals("coordinacion") || tipousuario.equals("departamento")
            || tipousuario.equals("decanato")){
%>
    <tiles:insert definition="listarEvaluacionesEnviadasAno"/>
<%
   } else {
%>
    <tiles:insert definition="noAutorizado"/>
<%
   }
%>