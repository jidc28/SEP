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
<logic:present name="solicitud_apertura_materia">
    <div class="alert alert-info alert-dismissable" 
         id="alert-coord">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
            &times;
        </button>
        <p>
            <strong>Atención: </strong> <br> 
            Se solicitó la apertura de 
            <strong> <bean:write name="solicitud_apertura_materia"/> </strong>
            materia(s). Para más información seleccionar 
            <em>Gestionar Materias, Listar solicitudes pendientes</em>.
        </p>
    </div>
</logic:present>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseMaterias">
                    Gestión de asignaturas
                </a>
                <a href="#" id="ayuda1" style="float: right" rel="popover"> 
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4> 
        </div>
        <div id="collapseMaterias" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/vistaAgregaMateria">
                    <h5 align ="center">Agregar asignatura</h5>
                </html:link>
                <html:link action="/consultaMateria">
                    <h5 align ="center">Consultar asignaturas</h5>
                </html:link>
                <logic:present name="solicitud_apertura_materia">
                    <html:link action="/consultaSolicitudesApertura"> 
                        <h5 align ="center"> Listar solicitudes creación pendientes
                            <span class="badge">
                                <bean:write name="solicitud_apertura_materia"/>
                            </span>
                        </h5>
                    </html:link>
                </logic:present>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseProfesor">
                    Gestión de profesores
                </a>
                <a href="#" id="ayuda3" style="float: right" rel="popover"> 
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseProfesor" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/irAgregarProfesor">
                    <h5 align ="center">Agregar profesor</h5>
                </html:link>
                <html:link action="/consultarProfesores">
                    <h5 align ="center">Consultar profesores</h5>
                </html:link>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 id="izquierda" class="panel-title">
                <a id="link-dropdown" class="accordion-toggle" 
                   data-toggle="collapse" 
                   data-parent="#accordion" href="#collapseEvaluacion">
                    Gestión de evaluaciones
                </a>
                <a href="#" id="ayuda2" style="float: right" rel="popover"> 
                    <span class="glyphicon glyphicon-question-sign"></span> 
                </a>
            </h4>
        </div>
        <div id="collapseEvaluacion" class="panel-collapse collapse">
            <div class="panel-body">
                <html:link action="/ConsultaEstadoPlanillas">
                    <h5 align ="center">
                        Gestión de planillas de evaluación
                    </h5>
                </html:link>
                <html:link action="/irEvaluarProfesores">
                    <h5 align ="center">Evaluar profesores</h5>
                </html:link>
            </div>
        </div>
    </div>
</div>