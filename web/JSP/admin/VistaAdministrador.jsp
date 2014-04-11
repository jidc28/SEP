<%-- 
    Document   : VistaAdministrador
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    if (session.getAttribute("usuario") == null) {
%>
<tiles:insert definition="baseAdmin"/>
<script>
    login()
</script>
<%    
    } else {
%>
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                    Gestión de Decanatos
                </a>
                <a href="#" id="ayuda1" style="float: right" rel="popover" >
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/agregaDecanatoA" >
                    <h5 align ="center">Agregar Decanato</h5>
                </html:link>
                <html:link action="/consultaDecanatoA" >
                    <h5 align ="center">Consultar Decanato</h5>
                </html:link>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                    Gestión de Departamentos
                </a>
                <a href="#" id="ayuda2" style="float: right" rel="popover" >
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/irAgregarDepartamento"> 
                    <h5> Agregar Departamento </h5>
                </html:link>
                <html:link action="/listarDepartamentos">
                    <h5>Consultar Departamentos</h5>
                </html:link>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                    Gestión de Coordinaciones
                </a>
                <a href="#" id="ayuda3" style="float: right" rel="popover" >
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseFour" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/agregaCoordinacionA" >
                    <h5 align ="center">Agregar Coordinación</h5>
                </html:link>
                <html:link action="/consultaCoordinacionA" >
                    <h5 align ="center">Consultar Coordinación</h5>
                </html:link>
            </div>
        </div>
    </div>
<% 
    }
%>