<%-- 
    Document   : sideLCoordinacion
    Created on : Feb 27, 2014, 5:39:10 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="glossymenu" >
    <a style="border-bottom: none;"/>
    <a class="menuitem" href="irInicio.do">
        Inicio
    </a>
    <html:form action="/ejecutarOpcion" style="margin: 0px;">
        <html:hidden name="Usuario" property="opcion" value="gestion_materias"/>
        <html:submit styleClass="link">
            Gestionar materias
        </html:submit>
    </html:form>
    <html:form action="/ejecutarOpcion" style="margin: 0px;">
        <html:hidden name="Usuario" property="opcion" value="gestion_evaluaciones"/>
        <html:submit styleClass="link">
            Gestionar evaluaciones
        </html:submit>
    </html:form>
    <a class="menuitem" href="cerrarSesion.do" onclick="return confirm('¿Está seguro que desea cerrar sesión?')" >
        Cerrar Sesión
    </a>
</div>