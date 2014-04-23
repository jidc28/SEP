<%-- 
    Document   : ConsultaDepartamentoDecanato
    Created on : 03/12/2013, 11:01:38 PM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="success">
    <div class="alert alert-success" id="alert">
        Departamento registrado exitosamente.
    </div>
</logic:present>
<logic:present name="modificacion">
    <div class="alert alert-success" id="alert">
        Departamento modificado exitosamente.
    </div>
</logic:present>
<logic:present name="falla">
    <div class="alert alert-danger" id="alert">
        Departamento no fue modificado exitosamente.
    </div>
</logic:present>
<logic:present name="departamento_desactivado">
    <div class="alert alert-success" id="alert">
        Departamento desactivado exitosamente.
    </div>
</logic:present>
<logic:present name="departamento_no_desactivado">
    <div class="alert alert-danger" id="alert">
        El departamento no fue desactivado exitosamente. Intente mas tarde.
    </div>
</logic:present>

<h4 style="margin-top: 50px;"> Lista de Departamentos
    <logic:present name="decanato_actual">
        del <bean:write name="decanato_actual" property="nombre"/>:
    </logic:present>
</h4>
<br>
<div id="tabla" class="table-responsive">
    <table border="0" style="margin: auto" class="table table-striped">
        <thead>
            <tr>
                <th style="font-size: 14px;">
                    <center>USBID</center>
                </th>
                <th width='30%' style="font-size: 14px;">
                    <center>NOMBRE</center>
                </th>
                <th style="font-size: 14px;">
                    <center>EDITAR</center>
                </th>
                <th style="font-size: 14px;">
                    <center>DESACTIVAR</center>
                </th>
            </tr>
        </thead>
        <tbody>
            <logic:iterate name="departamentos" id="Dep">
                <tr>
                    <td width="20%" align="center">
                        <bean:write name="Dep" property="codigo"/>
                    </td>
                    <td width="20%" align="center">
                        <bean:write name="Dep" property="nombre"/>
                    </td>
                    <td>
                        <html:form  action="/irEditarNombreDepartamento" 
                                    onsubmit="return(this)" style="margin: 0px;">
                            <html:hidden name="Dep" property="codigo" />
                <center>
                    <html:submit styleClass="btn btn-primary" 
                                 style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                        Modificar
                    </html:submit>
                </center>
            </html:form>
            </td>
            <td>
                <html:form action="/desactivarDepartamento" 
                           onsubmit="return(this)" style="margin: 0px;">
                    <html:hidden name="Dep" property="codigo"/>
                <center>
                    <html:submit styleClass="btn btn-danger" 
                                 style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                 onclick="javascript: return confirm('¿Está seguro de que desea desactivar el Departamento ${Dep.getNombre()} ?')">
                        Desactivar
                    </html:submit>
                </center>
            </html:form>
            </td>
            </tr>
        </logic:iterate>
        </tbody>
    </table>
</div>
