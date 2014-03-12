<%-- 
    Document   : ConsultarProfesores
    Created on : Mar 12, 2014, 11:04:58 AM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h4>  
    Profesores registrados en el sistema:
</h4>

<center>
    <logic:notPresent name="vacio">
        <div id="tabla" class="table-responsive">
            <table id="tabla" class="table table-striped">
                <thead>
                    <tr>
                        <th style="font-size: 14px;">
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
                                INFORMACIÓN
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
                        <table class="table" style="margin: 0px; background: transparent;">
                        <tbody>
                            <tr>
                                <td style="border: none; background: transparent; padding: 0px;" >
                        <select style="margin: 0px; width: 100%">
                            <option value="1">Opción 1</option>
                            <option value="2">Opción 2</option>
                            <option value="3">Opción 3</option>
                            <option value="4">Opción 4</option>
                            <option value="Ninguno">Ninguno</option>
                        </select>
                                </td>
                                <td style="border: none; background: transparent; padding: 0px; padding-left: 5px; width: 61px">
                        <html:form action="/eliminarProfesor" style="margin: 0px;">
                            <html:hidden name="profesor" property="usbid"/>
                            <html:submit styleClass="btn btn-success"
                                         style="padding-bottom: 2px; padding-top: 4.5px; padding-left: 4.5px; padding-right: 3px;"
                                         onclick="javascript: return confirm
                                         ('¿Está seguro de que desea modificar la información del profesor: ${profesor.getApellido()}, ${profesor.getNombre()}?')">
                                Guardar
                            </html:submit>
                        </html:form>
                        </td>
                        </tr>
                        </tbody>
                        </table>
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
