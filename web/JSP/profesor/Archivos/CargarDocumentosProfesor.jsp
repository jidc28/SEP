<%-- 
    Document   : cargar_documentos_profesor
    Created on : 24/04/2014, 07:04:46 PM
    Author     : jidc28
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<logic:present name="sin_archivos">
    <div class="alert alert-danger" id="alert">
        Debe seleccionar un archivo.
    </div>
</logic:present>

<logic:present name="archivo_invalido">
    <div class="alert alert-danger" id="alert">
        Extensión de archivo inválida. Solo se aceptan archivos pdf.
    </div>
</logic:present>

<logic:present name="archivo_muy_pesado">
    <div class="alert alert-danger" id="alert">
        El tamaño del archivo no puede ser mayor a 5MB.
    </div>
</logic:present>

<logic:present name="archivo_cargado">
    <div class="alert alert-success" id="alert">
        El archivo fue agregado exitosamente.
    </div>
</logic:present>

<h4>Agregar material:</h4>

<html:form action="/fileUploadProfesor" method="post" enctype="multipart/form-data">
    <div style="width:auto;height:auto;">
        <table width="350px" cellspacing="14px">
            <tbody>
                    <tr>
                        <td style="width: 40px;">
                            <strong style="font-size: 14px; width: 100%;">
                                AÑO
                            </strong>
                        </td>
                        <td>
                            <html:select name="fileUploadForm" property="ano" style="width: 100%;">
                                <logic:iterate name="anos" id="ano">
                                    <html:option value="${ano}">
                                        <bean:write name="ano"/>
                                    </html:option>
                                </logic:iterate>
                            </html:select>
                        </td>
                        <td style="padding-left: 10px; width: 100px;">
                            <strong style="font-size: 14px;">
                                TRIMESTRE
                            </strong>
                        </td>
                        <td>
                            <html:select name="fileUploadForm" property="trimestre" style="width: 100%;">
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
                    </tr>
                <tr>
                    <td colspan="4" style="height: 30px;">
                        <strong style="font-size: 14px;">
                            ARCHIVO:
                        </strong>  
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <div class="jumbotron" 
                             style="font-size: 12px; padding: 20px; margin: 0px;
                             padding-top: 30px; width: 600px;">
                            <div class="fileinput fileinput-new input-group" data-provides="fileinput">
                                <div class="form-control" data-trigger="fileinput">
                                    <i class="glyphicon glyphicon-file fileinput-exists"></i> 
                                    <span class="fileinput-filename"></span>
                                </div>
                                <span class="input-group-addon btn btn-default btn-file">
                                    <span class="fileinput-new">
                                        Select file
                                    </span>
                                    <span class="fileinput-exists">
                                        Change
                                    </span>
                                    <html:file value="cargar" onchange="Sumar()" 
                                               property="archivo[0]" style="width: 100%;">  
                                    </html:file>
                                </span>
                                <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">
                                    Remove
                                </a>
                            </div>
                        </div>
                    </td> 
                </tr>
                <tr>
                    <td style="height: 30px;" colspan="4">
                        <strong style="font-size: 14px;">
                            DESCRIPCIÓN:
                        </strong>  
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="height: 30px;">
                        <html:textarea name="fileUploadForm" property="descripcion" style="width: 100%;" rows="4" value=""/>
                    </td>
                </tr>
            </tbody>
        </table>  
    </div>
    <!--<input type="button" value="Seleccionar Otro Documento" style="font-size:12px; padding:4px 6px" onclick="addRow('dataBody')">-->
    <!--<input type="button" value="Eliminar Documentos Seleccionados" style="font-size:12px; padding:4px 6px" onclick="deleteRow('dataTable')"/>-->
    <html:submit styleClass="btn btn-info" style="margin-top: 20px;"> 
        Cargar Documentos 
    </html:submit>
</html:form>