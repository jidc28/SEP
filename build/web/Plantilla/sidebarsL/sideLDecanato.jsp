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
    <div class="glossymenu" >
        <a style="border-bottom: none;"/>
        <a class="menuitem" href="IrInicio.do">
            Inicio
        </a>
        <a class="menuitem" href="noimplementado.jsp">
            Perfil
        </a>
        <a class="menuitem" href="noimplementado.jsp">
            Contáctenos
        </a>
        <a class="menuitem" href="noimplementado.jsp">
            Ayuda
        </a>
        <a class="menuitem" href="cerrarSesion.do" onclick="return confirm('¿Está seguro que desea cerrar sesión?')" >
            Cerrar Sesión
        </a>

    </div>
</html>
