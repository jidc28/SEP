<%-- 
    Document   : modificar-informacion-script
    Created on : Feb 18, 2014, 2:32:24 PM
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
<script type="text/javascript" src="css/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="css/bootstrap2.3.2/js/bootstrap.js"></script>
<script type="text/javascript" src="css/bootstrap2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function() {
        $('#datetimepicker1').datetimepicker({
            pickTime: false
        });
    });
</script>
<script type="text/javascript">
    $(function() {
        $('#datetimepicker2').datetimepicker({
            pickTime: false
        });
    });
</script>