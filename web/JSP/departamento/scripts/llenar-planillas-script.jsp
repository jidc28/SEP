<%-- 
    Document   : llenar-planillas-script.jsp
    Created on : Feb 18, 2014, 2:19:18 PM
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
<logic:present name="error_num_estudiantes">
    <script>
        $(function() {
            $('#${error_num_estudiantes.getCodigo_materia()}_${error_num_estudiantes.getTrimestre()}').addClass('in'); 
        });
    </script>
</logic:present>
    
<logic:present name="agregar_informacion">
    <script>
        $(function() {
            $('#${agregar_informacion.getCodigo_materia()}_${agregar_informacion.getTrimestre()}').addClass('in'); 
        });
    </script>
</logic:present>
