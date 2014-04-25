<%-- 
    Document   : vista-profesor-script
    Created on : Apr 25, 2014, 10:21:17 AM
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
            'title': 'Gestión de Información',
            'content': 'permite modificar la información personal.'
        });  
    });  
    $(function () { 
        $("#ayuda2").popover({
            'title': 'Gestión de Material',
            'content': 'permite agregar, consultar, descargar y eliminar el'
                +' material (documentos) correspondientes al trimestre actual y '
                + ' conultar y descargar el material de trimestres anteriores.'
        });  
    });
</script>

<logic:present name="gestion_informacion">
    <script>
        $(function() {
            $('#collapseInformacion').addClass('in'); 
        });
    </script>
</logic:present>
<logic:present name="gestion_material">
    <script>
        $(function() {
            $('#collapseMaterial').addClass('in'); 
        });
    </script>
</logic:present>