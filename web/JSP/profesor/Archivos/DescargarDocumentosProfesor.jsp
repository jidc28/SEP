<%-- 
    Document   : descargar_documentos_profesor
    Created on : 24/04/2014, 07:04:46 PM
    Author     : jidc28
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h4> Descargar documentos:</h4>

<div>
    <table>
        <tbody>
            <tr>
                <td>
                    <select style="width: 100px;">
                        <option>2009</option>
                        <option>2010</option>
                        <option>2011</option>
                        <option>2012</option>
                        <option>2013</option>
                        <option>2014</option>
                        <option>2015</option>
                        <option>2016</option>
                    </select>
                </td>
                <td>
                    <select>
                        <option>
                            Septiembre-Diciembre
                        </option>
                        <option>
                            Enero-Marzo
                        </option>
                        <option>
                            Abril-Julio
                        </option>
                        <option>
                            Intensivo
                        </option>
                    </select>
                </td>
                <td>
                    <button class="btn btn-primary" 
                            style="padding-top: 4px; padding-bottom: 3px;">
                        Buscar
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</div>

<div id="tabla" class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th  style="font-size: 14px;">
                    PATH
                </th>
            </tr>
        </thead>
        <logic:iterate name="paths" id="path">
            <tr>
                <td align="center">
                    <bean:write name="path"/>
                </td>
            </tr>
        </logic:iterate>
    </table>
    <html:form  action="/descargarDocumentoSeleccionado" >
        <html:hidden name="usuario" property="usbid"/>
        <html:submit style="margin: 2.5px;" styleClass="btn btn-info" 
                     value="Descargar Documentos" />
    </html:form>
</div>
