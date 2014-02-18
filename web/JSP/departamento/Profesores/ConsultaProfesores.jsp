<%-- 
    Document   : ConsultaProfesores
    Created on : Jan 11, 2014, 4:57:54 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4> Profesores registrados en el sistema: </h4>

<center>
    <logic:notPresent name="vacio">
        <div id="tabla" class="table-responsive">
            <table id="tabla" class="table table-striped">
                <thead>
                    <tr>
                        <th style="font-size: 14px; width: 15%;">
                            <center>
                                USBID
                            </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center>
                                NOMBRE
                            </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center>
                                ELIMINAR
                            </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center>
                                ASIGNAR MATERIAS
                            </center>
                        </th>
                    </tr>
                </thead>
                <logic:iterate id="profesor" name="profesores">
                    <tr>
                        <td>
                    <center>
                        <bean:write name="profesor" property="usbid"/>
                    </center>
                    </td>
                    <td>
                        <bean:write name="profesor" property="apellido"/>,
                        <bean:write name="profesor" property="nombre"/>
                    </td>
                    <td>
                    <center>
                        <html:form action="/eliminarProfesor" style="margin: 0px;">
                            <html:hidden name="profesor" property="usbid"/>
                            <html:submit styleClass="btn btn-danger"
                                         style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;"
                                         onclick="javascript: return confirm
                                         ('¿Está seguro de que desea eliminar al profesor: ${profesor.getApellido()}, ${profesor.getNombre()}?')">
                                Eliminar
                            </html:submit>
                        </html:form>
                    </center>
                    </td>
                    <td>
                    <center>
                        <html:form action="/irAsignarMateriaProfesor" style="margin: 0px;">
                            <html:hidden name="profesor" property="usbid"/>
                            <html:submit styleClass="btn btn-default"
                                         style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                Asignar Materias
                            </html:submit>
                        </html:form>
                    </center>
                    </td>
                    </tr>
                </logic:iterate>
            </table>
        </div>
    </logic:notPresent>
    <logic:present name="vacio">
        <div class="alert alert-warning" id="alert" style="width: 60%">
            <p>
                No existen profesores registrados en este momento.
            </p>
        </div>
    </logic:present>
</center>