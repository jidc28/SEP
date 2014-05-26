<%-- 
    Document   : obtener-evaluaciones-script
    Created on : Mar 11, 2014, 2:15:05 PM
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

<%--<logic:notPresent name="informacion_coordinacion">
    <script>
        $(document).ready(function(){
            $('a[href="#departamento"]').tab('show');
        });
    </script>
</logic:notPresent>--%>

<logic:present name="general">
    <script>
        $(document).ready(function(){
            $('a[href="#departamento-general"]').tab('show');
        });
    </script>
</logic:present>
    
<logic:notPresent name="general">
    <script>
        $(document).ready(function(){
            $('a[href="#departamento"]').tab('show');
        });
    </script>
</logic:notPresent>

<script>
    function guardar_informacion(){
        confirm("¿Está seguro que los datos insertados son correctos?");
        document.forms['informacion_coordinacion'].submit();
        //        document.forms['informacion_coordinacion'].submit();
    }
</script>

<script>
    function update(){
        document.forms['informacion_coordinacion'].submit();
    }
</script>

<logic:present name="informacion_coordinacion">
    <script>
        $(document).ready(function(){
            $('a[href="#coordinacion"]').tab('show');
        });
    </script>
</logic:present>