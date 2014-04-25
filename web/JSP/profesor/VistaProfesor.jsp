<%-- 
    Document   : VistaProfesor
    Created on : 06/06/2013, 10:04:58 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="actualizacion">
    <div class="alert alert-success" id="alert">
        Información actualizada exitosamente.
    </div>
</logic:present>
<logic:present name="eliminacion">
    <div class="alert alert-success" id="alert">
        Información eliminada exitosamente.
    </div>
</logic:present>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseInformacion">
                    Gestión de información
                </a>
                <a href="#" id="ayuda1" style="float: right" rel="popover"> 
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4> 
        </div>
        <div id="collapseInformacion" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/gestionarInformacion">
                    <h5 align ="center">Gestionar información personal</h5>
                </html:link>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseMaterial">
                    Gestión de material
                </a>
                <a href="#" id="ayuda3" style="float: right" rel="popover"> 
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseMaterial" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/irCargarDocProfesor">
                    <h5 align ="center">Agregar material</h5>
                </html:link>
                <html:link action="/irDescargarDocProfesor">
                    <h5 align ="center">Descargar material</h5>
                </html:link>
            </div>
        </div>
    </div>
</div>