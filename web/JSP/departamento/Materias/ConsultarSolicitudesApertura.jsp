<%-- 
    Document   : ConsultarSolicitudesApertura
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="solicitud_procesada">
    <div class="alert alert-success" id="alert">
        La solicitud de apertura de materia ha sido procesada exitosamente.
    </div>
</logic:present>
<logic:present name="solicitud_no_procesada">
    <div class="alert alert-danger" id="alert">
        La solicitud de apertura de materia no pudo ser procesada exitosamente.
        Intente más tarde.
    </div>
</logic:present>

<h4> Solicitudes de creación de asignaturas </h4>

<logic:notPresent name="vacio">
    <div id="tabla" class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th  style="width: 40%; font-size: 14px;">
                        <center>
                            COORDINACION
                        </center>
                    </th>
                    <th style="font-size: 14px;">
                        <center>
                            NOMBRE
                        </center>
                    </th>
                    <th style="font-size: 14px;">
                        <center>
                            VER DETALLES
                        </center>
                    </th>
                </tr>
            </thead>
            <logic:iterate name="materias" id="Mat">
                <tr>
                    <td align="center">
                        <bean:write name="Mat" property="coordinacion"/>
                    </td>
                    <td>
                        <bean:write name="Mat" property="nombre"/>
                    </td>
                    <td align="center">
                        <html:form action="/verSolicitudApertura">
                            <html:hidden name="Mat" property="codigo"/>
                            <html:submit styleClass="btn btn-info"
                                         style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                Ver detalles
                            </html:submit>
                        </html:form>
                    </td>
                </tr>
            </logic:iterate>
        </table>
    </div>
</logic:notPresent>

<logic:present name="vacio">
    <div class="alert alert-warning" id="alert" style="width: 60%">
        <p>
            No existen solicitudes de apertura de materias en este momento.
        </p>
    </div>
</logic:present>