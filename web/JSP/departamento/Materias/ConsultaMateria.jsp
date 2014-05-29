<%-- 
    Document   : ConsultaMateria
    Created on : 10/06/2013, 07:43:54 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="materia_agregada">
    <div class="alert alert-success" id="alert">
        La materia fue agregada exitosamente.
    </div>
</logic:present>
<logic:present name="materia_no_agregada">
    <div class="alert alert-danger" id="alert">
        La materia no pudo ser agregada, intentelo mas tarde.
    </div>
</logic:present>
<logic:present name="materia_eliminada">
    <div class="alert alert-success" id="alert">
        La materia fue eliminada exitosamente.
    </div>
</logic:present>
<logic:present name="materia_no_eliminada">
    <div class="alert alert-danger" id="alert">
        La materia no pudo ser eliminada, intentelo mas tarde.
    </div>
</logic:present>
<logic:present name="materia_modificada">
    <div class="alert alert-success" id="alert">
        La materia fue modificada exitosamente.
    </div>
</logic:present>
<logic:present name="materia_no_modificada">
    <div class="alert alert-danger" id="alert">
        La materia no pudo ser modificada, intentelo mas tarde.
    </div>
</logic:present>

<h4> Lista de Materias:</h4>
<logic:notPresent name="vacio">
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
                            MODIFICAR
                        </center>
                    </th>
                    <th  style="font-size: 14px;">
                        <center>
                            ELIMINAR
                        </center>   
                    </th>
                </tr>
            </thead>
            <logic:iterate name="materias" id="Mat">
                <tr>
                    <td align="center">
                        <bean:write name="Mat" property="codigo"/>
                    </td>
                    <td>
                        <bean:write name="Mat" property="nombre"/>
                    </td>
                    <td align="center">
                        <html:form action="/irModificarMateria" style="margin: 0px;">
                            <html:hidden name="Mat" property="codigo"/>
                            <html:submit styleClass="btn btn-info"
                                         style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">
                                Modificar
                            </html:submit>
                        </html:form>
                    </td>
                    <td align="center">
                        <html:form action="/eliminaMateria" style="margin: 0px;">
                            <html:hidden name="Mat" property="codigo"/>
                            <html:submit styleClass="btn btn-danger"
                                         style="padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;"
                                         onclick="javascript: return confirm('¿Está seguro de que desea desactivar la materia: ${Mat.getNombre()}?')">
                                Eliminar
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
            No existen materias ofertadas en este momento.
        </p>
    </div>
</logic:present>