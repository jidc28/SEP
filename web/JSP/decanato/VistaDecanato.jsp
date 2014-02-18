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

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseFour">
                    Gestión de Coordinaciones
                </a>
                <a href="#" id="ayuda1" style="float: right" rel="popover" >
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
</div>