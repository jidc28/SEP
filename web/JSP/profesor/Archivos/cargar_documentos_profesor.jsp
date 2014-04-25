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

<div align=center>
    <html:form action="/fileUploadProfesor" method="post" enctype="multipart/form-data">
        <br />
        <div style="width:auto;height:auto;">
            <table id="dataTable" width="350px" cellspacing="14px">
                <tbody id="dataBody">
                    <tr>
                        <td></td>
                        <td>Foto</td>
                        <td valign="left">
                            <div id="archivos" >
                                <div id="elarchivo" class="feed">  
                                    <html:file value="cargar" onchange="Sumar()" property="archivo[0]"></html:file>
                                        <br />
                                </div>
                            </div>

                        </td> 
                    </tr>
                </tbody>
            </table>  
        </div>
        <!--<input type="button" value="Seleccionar Otro Documento" style="font-size:12px; padding:4px 6px" onclick="addRow('dataBody')">-->
        <!--<input type="button" value="Eliminar Documentos Seleccionados" style="font-size:12px; padding:4px 6px" onclick="deleteRow('dataTable')"/>-->
        <p  align=center>
            <html:submit>Cargar Documentos </html:submit>
        </p>
    </html:form>
</div>

