<%-- 
    Document   : vista-coordinacion-script
    Created on : Feb 18, 2014, 11:04:46 AM
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
            'content': 'permite vincular, desvincular materias del pensum,' + 
                'consultar las materias ofertadas por los departamentos de la USB y' +
                'solicitar la apertura de una materia a un departamento.'
        });  
    });  
    $(function () { 
        $("#ayuda2").popover({
            'title': 'Gestión de Evaluaciones',
            'content': 'permite obtener la evaluación de los profesores por materia.'
        });  
    });  
</script>

<logic:present name="gestion_materias">
    <script>
        $(function() {
            $('#collapseDepartamentos').addClass('in'); 
        });
    </script>
</logic:present>
    
<logic:present name="gestion_evaluaciones">
    <script>
        $(function() {
            $('#collapseEvaluaciones').addClass('in'); 
        });
    </script>
</logic:present>

<logic:present name="gestion_profesores">
    <script>
        $(function() {
            $('#collapseProfesores').addClass('in'); 
        });
    </script>
</logic:present>