<%-- 
    Document   : vista-decanato-script
    Created on : Feb 18, 2014, 11:09:54 AM
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
            'title': 'Gestión de coordinaciones',
            'content': 'permite agregar, consultar, modificar y eliminar'
                + ' una coordinación en el sistema.'
        });  
    });  
</script>

<script>  
    $(function () { 
        $("#ayuda2").popover({
            'title': 'Gestión de evaluaciones',
            'content': 'permite consultar las evaluaciones realizadas por las'
                + ' coordinaciones.'
        });  
    });  
</script>

<logic:present name="gestion_coordinaciones">
    <script>
        $(function() {
            $('#collapseCoordinaciones').addClass('in'); 
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