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

<h4> Lista de PATH:</h4>
    <div id="tabla" class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th  style="font-size: 14px;">
                        <center>
                            PATH
                        </center>
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
