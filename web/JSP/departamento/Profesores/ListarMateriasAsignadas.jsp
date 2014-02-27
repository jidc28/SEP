<%-- 
    Document   : ListarMateriasAsignadas
    Created on : Feb 27, 2014, 12:55:26 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4> Lista de Materias asignadas:</h4>

<br style="font-size: 14px;">
<strong> PROFESOR </strong> 
<html:text disabled="true" name="profesor" property="nombre"
           style="height: 30px; margin: 0px; text-align: center;"/>
<strong> USBID </strong> 
<html:text disabled="true" name="profesor" property="usbid"
           style="height: 30px; margin: 0px; text-align: center;"/>
</br>
<logic:notEmpty name="materias">
    <div id="tabla" class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th  style="font-size: 14px;">
            <center>
                CODIGO
            </center>
            </th>
            <th width="38%"  style="font-size: 14px;">
            <center>
                NOMBRE
            </center>
            </th>
            <th  style="font-size: 14px;">
            <center>
                ELIMINAR
            </center>
            </th>
            <th  style="font-size: 14px;">
            <center>
                MODIFICAR PERIODO
            </center>   
            </th>
            </tr>
            </thead>
            <tbody>
                <logic:iterate name="materias" id="materia">
                    <tr>
                        <td align="center">
                            <bean:write name="materia" property="codigo"/>
                        </td>
                        <td>
                            <bean:write name="materia" property="nombre"/>
                        </td>
                        <td align="center">
                <center>
                    <html:form action="/eliminarPeriodo" style="margin: 0px;">
                        <html:hidden name="materia" property="codigo"/>
                        <html:submit styleClass="btn btn-danger"
                                     style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;"
                                     onclick="javascript: return confirm('¿Está seguro de que desea eliminar la asociación con la materia: ${materia.getNombre()}?')">
                            Eliminar
                        </html:submit>
                    </html:form>
                </center>
                </td>
                <td>
                <center>
                    <button class="btn btn-primary" 
                            style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                        <a href="#" id="${materia.getCodigo()}" rel="popover"
                           style="text-decoration: none; color: white;">
                            Modificar <span class="caret" style="margin: 0px;"></span>
                        </a>
                    </button>
                </center>
                </td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>
    </div>
</logic:notEmpty>

<logic:empty name="materias">
    <div class="alert alert-warning alert-dismissable" 
         id="alert-coord"
         style="margin-top: 30px;">
        <a href="#" id="ayuda1" style="color: #c09853; float: right" rel="popover" > 
            <span style="color: #c09853;" class="glyphicon glyphicon-question-sign">     
            </span> 
        </a>
        <p>
            Verifique que el profesor tenga materias asignadas.
        </p>
    </div>
</logic:empty>