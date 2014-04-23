<%-- 
    Document   : vista-administrador-script
    Created on : Feb 18, 2014, 10:31:22 AM
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
            'title': 'Gestión de Decanatos',
            'content': 'permite agregar, consultar, modificar y eliminar'
                + ' un decanato del sistema.'
        });  
    });  
    $(function () { 
        $("#ayuda2").popover({
            'title': 'Gestión de Departamentos',
            'content': 'permite agregar, consultar, modificar y eliminar'
                + ' un departamento del sistema.'
        });  
    });  
    $(function () { 
        $("#ayuda3").popover({
            'title': 'Gestión de Coordinaciones',
            'content': 'permite agregar, consultar, modificar y eliminar'
                + ' una coordinación, en base a un decanato, en el sistema.'
        });  
    }); 
</script>

<logic:present name="gestion_decanatos">
    <script>
        $(function() {
            $('#collapseThree').addClass('in'); 
        });
    </script>
</logic:present>
<logic:present name="gestion_departamentos">
    <script>
        $(function() {
            $('#collapseOne').addClass('in'); 
        });
    </script>
</logic:present>
<logic:present name="gestion_coordinaciones">
    <script>
        $(function() {
            $('#collapseFour').addClass('in'); 
        });
    </script>
</logic:present>
