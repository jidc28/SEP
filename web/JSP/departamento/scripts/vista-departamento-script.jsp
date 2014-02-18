<%-- 
    Document   : vista-departamento-script
    Created on : Feb 18, 2014, 11:15:13 AM
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
            'title': 'Gestión de Materias',
            'content': 'permite agregar materias a la oferta y consultar'
                + ' las materias ofertadas por el departamento.'
        });  
    });  
    $(function () { 
        $("#ayuda2").popover({
            'title': 'Gestión de Evaluaciones',
            'content': 'permite evaluar un grupo de profesores y llenar'
                +' las planillas correspondientes a las materias '
                +'dictadas por cada uno de ellos.'
        });  
    });
    $(function () { 
        $("#ayuda3").popover({
            'title': 'Gestión de Profesores',
            'content': 'permite agregar, consultar y eliminar un '
                + 'profesor y agregar las materias que dicta'
        });  
    });
</script>

