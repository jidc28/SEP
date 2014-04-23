<%-- 
    Document   : consultaMateria
    Created on : 08/12/2013, 12:10:31 AM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:present name="materia_vinculada">
    <div class="alert alert-success" id="alert">
        Materia vinculada exitosamente.
    </div>
</logic:present>
<logic:present name="materia_desvinculada">
    <div class="alert alert-success" id="alert">
        Materia desvinculada exitosamente.
    </div>
</logic:present>
<logic:present name="materia_falla_vinculado">
    <div class="alert alert-danger" id="alert">
        Materia no fue vinculada exitosamente.
    </div>
</logic:present>
<logic:present name="materia_falla_desvinculado">
    <div class="alert alert-danger" id="alert">
        Materia no fue desvinculada exitosamente.
    </div>
</logic:present>

<h4> Lista de Materias  
    <logic:present name="dpto_seleccionado">
        <br> 
        <bean:write name="dpto_seleccionado" property="nombre"/>
    </logic:present>
    <logic:notPresent name="dpto_seleccionado">
        Vinculadas:
    </logic:notPresent>
</h4>

<logic:notPresent name="vacio">
    <div id="tabla" class="table-responsive">
        <table class="table table-striped">
            <logic:notPresent name="materias_vinculadas">
                <thead>
                    <tr>
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
                                VINCULAR
                            </center>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="materias" id="mat">
                        <tr>
                            <td align="center">
                                <bean:write name="mat" property="codigo"/>
                            </td>
                            <td  align="center">
                                <bean:write name="mat" property="nombre"/>
                            </td>
                            <td>
                                <html:form action="/vincularMateriaCoordinacion" onsubmit="return(this)" style="margin: 0px;">
                                <html:hidden name="mat" property="codigo" />
                                <center>
                                    <html:submit styleClass="btn btn-success" 
                                                 style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                 onclick="javascript: return confirm('¿Está seguro de que desea vincular la materia: ${mat.getNombre()}?')">
                                            Vincular
                                    </html:submit>
                                </center>
                                </html:form>
                            </td>
                    </tr>
                </logic:iterate>
                </tbody>
            </logic:notPresent>
            <logic:present name="materias_vinculadas">
                <thead>
                    <tr>
                        <th style="font-size: 14px;">
                            <center>
                                CODIGO
                            </center>
                        </th>
                        <th width="30%" style="font-size: 14px;">
                            <center>    
                                NOMBRE
                            </center>
                        </th>
                        <th style="font-size: 14px;">
                            <center>
                                DESVINCULAR
                            </center>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="materias" id="mat">
                        <tr>
                            <td width="20%" align="center">
                                <bean:write name="mat" property="codigo"/>
                            </td>
                            <td width="20%" align="center">
                                <bean:write name="mat" property="nombre"/>
                            </td>
                            <td>
                                <html:form action="/desvincularMateriaCoordinacion" 
                                           onsubmit="return(this)" style="margin: 0px;">
                                    <html:hidden name="mat" property="codigo" />
                                    <center>
                                        <html:submit styleClass="btn btn-danger" 
                                                     style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;"
                                                     onclick="javascript: return confirm('¿Está seguro de que desea desvincular la materia: ${mat.getNombre()}?')">
                                            Desvincular
                                        </html:submit>
                                    </center>
                                </html:form>
                            </td>
                    </tr>
                </logic:iterate>
                </tbody>
            </logic:present>
        </table>
    </div>
</logic:notPresent>

<logic:present name="vacio">
    <logic:notPresent name="materias_vinculadas">
        <div class="alert alert-warning" id="alert" style="width: 60%">
            <p>
                No existen materias ofertadas por el departamento en este momento.
            </p>
        </div>
    </logic:notPresent>
    
    <logic:present name="materias_vinculadas">
        <div class="alert alert-warning" id="alert" style="width: 60%">
            <p>
                No existen materias vinculadas en este momento.
            </p>
        </div>
    </logic:present>
</logic:present>