<%-- 
    Document   : ver-planillas-llenas-script
    Created on : Mar 11, 2014, 10:34:18 AM
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

<script>  
    $(function () { 
        $("#ayuda1").popover({
            'title': 'Ir a llenar las planillas',
            'content': 'Haga click en este <html:link action="/ConsultaEstadoPlanillas"> link </html:link> para llenar las planillas.',
            'html': 'true'
        });  
    });  
</script>
