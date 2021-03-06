<%-- 
    Document   : consultaDepartamento
    Created on : 08/12/2013, 12:10:31 AM
    Author     : Langtech
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4> Lista de Departamentos </h4>

<div id="tabla" class="table-responsive">
    <table class="table table-striped">
        <thead>
            <th width="15%" style="font-size: 14px;">
                <center>
                    CODIGO
                </center>
            </th>
            <th style="font-size: 14px;">
                <center>
                    NOMBRE DEPARTAMENTO
                </center>
            </th>
            <th width="15%" style="font-size: 14px;">
                <center>
                    MATERIA
                </center>
            </th>
        </thead>
        <tbody>
            <logic:iterate name="departamentos" id="dep">
                <tr>
                    <td align="center">
                        <bean:write name="dep" property="codigo"/>
                    </td>
                    <td>
                        <bean:write name="dep" property="nombre"/>
                    </td>
                    <td>
                        <html:form action="/listarMateriasDepto" 
                                   onsubmit="return(this)" style="margin: 0px;">
                            <html:hidden name="dep" property="codigo" />
                            <html:hidden name="dep" property="nombre" />
                            <center>
                                <html:submit styleClass="btn btn-default" 
                                             style="margin: 5px; padding: 3px; padding-left: 5px; padding-right: 5px;">
                                    Listar
                                </html:submit>
                            </center>
                        </html:form>
                    </td>
                </tr>
        </logic:iterate>
        </tbody>
    </table>
</div>