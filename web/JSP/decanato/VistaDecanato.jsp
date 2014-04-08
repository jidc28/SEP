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

<logic:present name="evaluaciones_pendientes">
    <div class="alert alert-info alert-dismissable" 
         id="alert-coord">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
            &times;
        </button>
        <p>
            <strong>Atención: </strong> <br> 
            se han realizado <bean:write name="evaluaciones_pendientes"/> 
            evaluación(nes). Para más información seleccionar 
            <em>Gestión de evaluaciones, Listar evaluaciones pendientes</em>.
        </p>
    </div>
</logic:present>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseCoordinaciones">
                    Gestión de coordinaciones
                </a>
                <a href="#" id="ayuda1" style="float: right" rel="popover" >
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseCoordinaciones" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/agregaCoordinacionA" >
                    <h5 align ="center">Agregar coordinación</h5>
                </html:link>
                <html:link action="/consultaCoordinacionA" >
                    <h5 align ="center">Consultar coordinación</h5>
                </html:link>
            </div>
        </div>
    </div>
    <logic:present name="evaluaciones_pendientes">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 id="izquierda" class="panel-title">
                    <a id="link-dropdown" class="accordion-toggle" 
                       data-toggle="collapse" 
                       data-parent="#accordion" href="#collapseEvaluaciones">
                        Gestión de evaluaciones
                    </a>
                    <a href="#" id="ayuda2" style="float: right" rel="popover" >
                        <span class="glyphicon glyphicon-question-sign"></span> 
                    </a>
                </h4>
            </div>
            <div id="collapseEvaluaciones" class="panel-collapse collapse">
                <div class="panel-body">
                    <html:form action="/listarCoordinaciones" style="margin: 0px;">
                        <html:hidden name="Coordinacion" property="opcion"
                                     value="listar_numero"/>
                        <html:submit styleClass="h5">
                            Listar Evaluaciones Pendientes
                        </html:submit>
                        <span class="badge">
                            <bean:write name="evaluaciones_pendientes"/>
                        </span>
                    </html:form>
                </div>
            </div>
        </div>
    </logic:present>
</div>