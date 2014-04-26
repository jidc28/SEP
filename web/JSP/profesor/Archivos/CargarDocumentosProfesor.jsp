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

<h4>Agregar material:</h4>

<html:form action="/fileUploadProfesor" method="post" enctype="multipart/form-data">
    <div style="width:auto;height:auto;">
        <table id="dataTable" width="350px" cellspacing="14px">
            <tbody id="dataBody">
                <tr>
                    <td></td>
                    <td>
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
            </tbody>
        </table>  
    </div>
    <!--<input type="button" value="Seleccionar Otro Documento" style="font-size:12px; padding:4px 6px" onclick="addRow('dataBody')">-->
    <!--<input type="button" value="Eliminar Documentos Seleccionados" style="font-size:12px; padding:4px 6px" onclick="deleteRow('dataTable')"/>-->
    <html:submit styleClass="btn btn-info" style="margin-top: 20px;"> 
        Cargar Documentos 
    </html:submit>
</html:form>