<%-- 
    Document   : sideLAdmin
    Created on : 15/10/2013, 06:38:44 PM
    Author     : jidc28
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="glossymenu">
        <a style="border-bottom: none;"/>
        <html:link styleClass="menuitem" action="irInicio.do">
            Inicio
        </html:link>
        <a class="menuitem" onclick="history.go(-1);">
            Volver
        </a>
        <html:form action="/ejecutarOpcion" style="margin: 0px;">
            <html:hidden name="Usuario" property="opcion" value="gestion_informacion"/>
            <html:submit styleClass="link">
                Gestión de información
            </html:submit>
        </html:form>
        <html:form action="/ejecutarOpcion" style="margin: 0px;">
            <html:hidden name="Usuario" property="opcion" value="gestion_material"/>
            <html:submit styleClass="link">
                Gestión de material
            </html:submit>
        </html:form>
        <a class="menuitem" href="cerrarSesion.do" onclick="return confirm('¿Está seguro que desea cerrar sesión?')" >
            Cerrar Sesión
        </a>
    </div>
</html>
