<%-- 
    Document   : AsignarMateriaProfesor
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="no_seleccionado">
    <div class="alert alert-danger" id="alert">
        Porfavor, seleccione una o más materias a asignar.
    </div>
</logic:present>

<h4> Asignar Materias a Profesores:</h4>

<br style="font-size: 14px;">
<table style="margin-top: 0px;">
    <tr>
        <td>
            <strong style="color: #333;"> PROFESOR </strong>
        </td>
        <td>
            <div class="col-xs-4" style="width: 100%; height: 30px; margin: 0px; padding: 0px;">
                <input disabled="true" 
                       value='<bean:write name="profesor" property="apellido" />, <bean:write name="profesor" property="nombre"/>'
                       class="form-control" style="height: 30px; text-align: center;">
            </div>
        </td>
        <td>
            <strong style="color: #333;"> USBID </strong>
        </td>
        <td>
            <html:text disabled="true" name="profesor" property="usbid"
                       style="width: 100%; height: 30px; margin: 0px; text-align: center;"/>
        </td>
    </tr>
</table>
</br>

<center>
    <logic:notEmpty name="materias" property="items">
        <div id="tabla" class="table-responsive" style="margin-top: 20px;">
            <table id="tabla" class="table table-striped">
                <thead>
                    <tr>
                        <th>
                        </th>
                        <th style="font-size: 14px;">
                <center>
                    CODIGO
                </center>
                </th>
                <th style="font-size: 14px;">
                <center>
                    NOMBRE
                </center>
                </th>
                <th style="font-size: 14px;">
                <center>
                    CRÉDITOS
                </center>
                </th>
                </tr>
                </thead>
                <html:form action="/asignarMateriaProfesor">
                    <logic:iterate id="materia" name="materias" property="items">
                        <tr>
                            <td>
                        <center>
                            <html:multibox  property="itemsSeleccionados">
                                <bean:write name="materia" property="codigo"/>
                            </html:multibox>
                        </center>
                        </td>
                        <td>
                        <center>
                            <bean:write name="materia" property="codigo"/>
                        </center>
                        </td>
                        <td>
                            <bean:write name="materia" property="nombre"/> 
                        </td>
                        <td>
                        <center>
                            <bean:write name="materia" property="creditos"/>
                        </center>
                        </td>
                        </tr>
                    </logic:iterate>
                </table>
                <center>
                    <html:submit styleClass="btn btn-primary" 
                                 style="type: button; data-loading-text: cargando;">
                        Asignar Materias Seleccionadas
                    </html:submit>
                </center>
            </html:form>
        </div>
    </logic:notEmpty>
    
    <logic:empty name="materias" property="items">
        <div class="alert alert-warning alert-dismissable" 
             id="alert-coord"
             style="margin-top: 20px;">
            <a href="#" id="ayuda1" style="color: #c09853; float: right" rel="popover" > 
            </a>
            <p>
                El profesor dicta todas las materias ofertadas por este departamento.
            </p>
        </div>
    </logic:empty>
</center>