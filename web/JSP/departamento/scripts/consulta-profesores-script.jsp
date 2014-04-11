<%-- 
    Document   : consulta-profesores-script
    Created on : Feb 26, 2014, 7:32:00 PM
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
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.js"></script>
<script type="text/javascript">
    $(function () { 
        $("#ayuda1").popover({
            'title': 'Gestionar Profesores',
            'content': 'Seleccionando Eliminar se elimina al profesor deseado'+
                '. \nSeleccionando Asignar Materias se da la posibilidad de'+
                ' asignar una o más materias a un profesor',
            'placement':'left',
            'html': 'true'
        });  
    });  
</script>