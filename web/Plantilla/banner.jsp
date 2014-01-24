<%-- 
    Document   : banner
    Created on : 15/10/2013, 06:26:58 PM
    Author     : jidc28
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<div>
    <img id="banner" src="imagenes/logo.jpg" alt="Inicio">

    <p style="text-align: right; margin-top: 5px;">
        Usted se ha identificado como
        <a href="#"> <bean:write name="autenticado" property="nombre"/> 
            <logic:present name="autenticado" property="apellido">
                <bean:write name="autenticado" property="apellido"/>
            </logic:present>
        </a>
    </p>
</div>