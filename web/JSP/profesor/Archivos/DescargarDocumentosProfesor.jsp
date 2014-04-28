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

<html:form action="/consultarDocumentos">
    <div>
        <table>
            <tbody>
                <tr>
                    <td>
                        <html:select name="Archivo" property="ano" style="width: 100px;">
                            <logic:iterate name="anos" id="ano">
                                <html:option value="${ano}">
                                    <bean:write name="ano"/>
                                </html:option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td>
                        <html:select name="Archivo" property="trimestre" style="width: 100%;">
                            <html:option value="SD">
                                Septiembre-Diciembre
                            </html:option>
                            <html:option value="EM">
                                Enero-Marzo
                            </html:option>
                            <html:option value="AJ">
                                Abril-Julio
                            </html:option>
                            <html:option value="V">
                                Intensivo
                            </html:option>
                        </html:select>
                    </td>
                    <td>
                        <html:submit styleClass="btn btn-primary"
                                     style="padding-top: 4px; padding-bottom: 3px;">
                            Buscar documentos
                        </html:submit>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form>

<logic:empty name="archivos">
    <div class="alert alert-warning alert-dismissable" 
         id="alert-coord">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
            &times;
        </button>
        <p>
            No existen archivos en este año y este trimestre.
        </p>
    </div>
</logic:empty>

<logic:notEmpty name="archivos">
    <div id="tabla" class="table-responsive" style="width: 85%;">
        <table class="table table-striped" style=" margin-top: 40px;">
            <tbody>
                <logic:iterate name="archivos" id="archivo">
                    <tr>
                        <td style="font-size: 14px; font-weight: bold;">
                            <span class="glyphicon glyphicon-file"></span>
                            <bean:write name="archivo" property="nombre"/>
                            <br>
                            <blockquote style="margin: 0px;">
                                <p style="font-size: 11px; color: gray;">
                                    <bean:write name="archivo" property="descripcion"/>
                                </p>
                            </blockquote>
                            <div style="text-align: right;">
                                <html:form  action="/descargarDocumentoSeleccionado" 
                                            style="margin:0px;">
                                    <html:hidden name="archivo" property="nombre"/>
                                    <html:hidden name="archivo" property="usbidProfesor"/>
                                    <html:hidden name="archivo" property="trimestre"/>
                                    <html:hidden name="archivo" property="ano"/>
                                    <html:submit styleClass="btn btn-info"
                                                 style="padding-top: 4px; padding-bottom: 3px;">
                                        Descargar
                                    </html:submit>
                                </html:form>
                            </div>
                        </td>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
    </div>
</logic:notEmpty>

<!--SI QUEREMOS IMPLEMENTAR UN VIEWER -->
<!--                    <iframe height="475" style="border: none;"
                            src="http://online.verypdf.com/app/reader/?url=http://online.verypdf.com/examples/pdfeditor.pdf" 
                            width="600">
                    </iframe>-->
