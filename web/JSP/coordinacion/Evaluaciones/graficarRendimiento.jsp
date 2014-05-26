<%-- 
    Document   : graficarRendimiento
    Created on : Mar 11, 2014, 6:12:23 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4>
    Rendimiento de la materia: <bean:write name="materia_evaluar"/>
        <div style="font-size: 14px; color: grey;">
        <bean:write name="profesor" property="apellido"/>,
        <bean:write name="profesor" property="nombre"/>
    </div>
</h4>

<div style="width: 95%">
    <ul class="nav nav-tabs" style="height: 55px;">
        <li>
            <a href="#profesor" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                Rendimiento <br> 
                del profesor
            </a>
        </li>
        <li>
            <a href="#general" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                Rendimiento con respecto <br>
                a los demás profesores
            </a>
        </li>
        <li>
            <a href="#por_nota" data-toggle="tab" style="font-size: 12px; margin: 0px;">
                Rendimiento con respecto <br>
                a las notas de los demás profesores
            </a>
        </li>
    </ul>
</div>

<div class="tab-content" style="overflow-y: hidden;">
    <div class="tab-pane" id="profesor">
        <div id="pie-chart-profesor" style="width: 95%; height: 300px;"></div>
        <div id="pie-chart-nota" style="width: 95%; height: 300px;"></div>
    </div>
    <div class="tab-pane" id="general">
        <div id="combo-chart-general" style="width: 95%;"></div>
        <strong>
            Nota promedio de estudiantes de 
            <bean:write name="profesor" property="nombre"/> <bean:write name="profesor" property="apellido"/>:
        </strong> 
        <bean:write name="EP" property="nota_prom"/><br>
        <strong>
            Nota promedio de estudiantes en general:
        </strong> 
        <bean:write name="nota_promedio_general"/>
    </div>
    <div class="tab-pane" id="por_nota">
        <div id="combo-chart-por-nota" style="width: 95%;"></div>
    </div>
</div>