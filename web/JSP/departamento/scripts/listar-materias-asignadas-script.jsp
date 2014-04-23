<%-- 
    Document   : listar-materias-asignadas-script
    Created on : Feb 27, 2014, 2:00:57 PM
    Author     : smaf
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sistema de Evaluación de Profesores</title>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script src="css/js/bootstrap.min.js"></script>

<script>
    $(function () { 
        $("#ayuda1").popover({
            'title': 'Ir a asignar materias',
            'content': 'Para asignarle materia a un profesor seleccione:'
                +'<html:form action="/irAsignarMateriaProfesor" style="margin: 0px;">'
                +'<html:hidden name="profesor" property="nombre"/>'
                +'<html:hidden name="profesor" property="apellido"/>'
                +'<html:hidden name="profesor" property="usbid"/>'
                +'<html:submit styleClass="link" style="padding:0px;">link</html:submit>'
                +'</html:form>',
            'placement': 'bottom',
            'html': 'true'
        });  
    });  
</script>

<logic:iterate name="materias" id="materia">
    <script>
        $(function () { 
            $("#${materia.getCodigo()}").popover({
                'content': 
                    '<html:form action="/modificarPeriodo">'
                    +'<p style="color: black; margin: 0px; font-size: 14px;">'
                    +'<html:checkbox style="margin: 0px; margin-right: 5px;" name="materia" property="periodoSD" value="SD"/>'
                    +'Septiembre-Diciembre </p> <br>'
                    +'<p style="color: black; margin: 0px; font-size: 14px;"">'
                    +'<html:checkbox style="margin: 0px; margin-right: 5px;" name="materia" property="periodoEM" value="EM"/>' 
                    +'Enero-Marzo </p> <br>'
                    +'<p style="color: black; margin: 0px; font-size: 14px;"">'
                    +'<html:checkbox style="margin: 0px; margin-right: 5px;" name="materia" property="periodoAJ" value="AJ"/>'
                    +'Abril-Julio </p> <br>'
                    +'<p style="color: black; margin: 0px; font-size: 14px;"">'
                    +'<html:checkbox style="margin: 0px; margin-right: 5px;" name="materia" property="periodoV" value="V"/>'
                    +'Intensivo </p>'
                    +'<html:hidden name="materia" property="codigo"/>'
                    +'<html:submit styleClass="btn btn-success" style="margin-bottom: 10px; margin-top: 10px; float: right; padding-bottom: 2px; padding-top: 3px; padding-left: 3px; padding-right: 3px;">'
                    +'Modificar'
                    +'</html:submit>'
                    +'</html:form>',
                'placement': 'bottom',
                'html': 'true'
            });  
        });  
        </script>
</logic:iterate>
