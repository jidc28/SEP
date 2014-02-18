<%-- 
    Document   : vistaCoordinador
    Created on : 06/12/2013, 02:00:46 PM
    Author     : jidc28
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
            Se solicitó evaluación de 
            <strong><bean:write name="evaluaciones_pendientes"/></strong> 
            profesor(es). Para más información seleccionar 
            <em>Gestionar Evaluaciones</em>.
        </p>
    </div>
</logic:present>

<logic:present name="solicitud_enviada">
    <div class="alert alert-success" id="alert-coord">
        La solicitud de apertura de la materia ha sido enviada.
    </div>
</logic:present>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseDepartamentos">
                    Gestión de Materias
                </a>
                <a href="#" id="ayuda1" style="float: right" rel="popover"> 
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseDepartamentos" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/listarMaterias" >
                    <h5 align ="center">Listar Materias Vinculadas</h5>
                </html:link>
                <html:link action="/listarDepartamentos" >
                    <h5 align ="center">Vincular Materias por Departamento</h5>
                </html:link> 
                <html:link action="/irSolicitarAperturaMateria">
                    <h5 align ="center">Solicitar Apertura de Materia</h5>
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
                        Gestionar Evaluaciones  
                    </a>
                    <a href="#" id="ayuda2" style="float: right" rel="popover"> 
                        <span class="glyphicon glyphicon-question-sign"></span> 
                    </a>
                </h4>
            </div>
            <div id="collapseEvaluaciones" class="panel-collapse collapse">
                <div class="panel-body">
                    <html:link action="/listarEvaluacionesPendientes">
                        <h5 align ="center">Listar evaluaciones pendientes                                
                            <span class="badge">
                                <bean:write name="evaluaciones_pendientes"/>
                            </span>
                        </h5>
                    </html:link>
                </div>
            </div>
        </div>
    </logic:present>
</div>
