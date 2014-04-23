<%-- 
    Document   : graficar-rendimiento-script
    Created on : Mar 11, 2014, 6:22:30 PM
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
<script type="text/javascript" src="css/js/jsapi"></script>
<script src="css/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function(){
        $('a[href="#profesor"]').tab('show');
    });
</script>

<script type="text/javascript">
    google.load('visualization', '1', {packages: ['corechart']});
</script>

<script type="text/javascript">
    function drawVisualization() {
        
        var data = google.visualization.arrayToDataTable([
            ['rubro', '<bean:write name="profesor" property="apellido"/>', 'Ponderado'],
            ['aprobados',   ${aprobados},${aprobados_general}],
            ['reprobados',  ${aplazados},${aplazados_general}],
            ['retirados',<bean:write name="EP" property="retirados"/>,<bean:write name="EPS" property="retirados"/>]
        ]);

        var options = {
            title : 'Cantidad de estudiantes aplazados, reprobados y retirados',
            vAxis: {title: "Cantidad de estudiantes"},
            seriesType: "bars",
            colors : ["#0099c6","#66aa00"],
            allowHtml : true,
            width : 500,
            height :300
        };

        var chart = new google.visualization.ComboChart(document.getElementById('combo-chart-general'));
        chart.draw(data, options);
    }
    google.setOnLoadCallback(drawVisualization);
</script>

<script type="text/javascript">
    function drawVisualization() {

        var data = google.visualization.arrayToDataTable([
            ['rubro', '<bean:write name="profesor" property="apellido"/>', 'Ponderado'],
            ['1',<bean:write name="EP" property="nota1"/>,<bean:write name="EPS" property="nota1"/>],
            ['2',<bean:write name="EP" property="nota2"/>,<bean:write name="EPS" property="nota2"/>],
            ['3',<bean:write name="EP" property="nota3"/>,<bean:write name="EPS" property="nota3"/>],
            ['4',<bean:write name="EP" property="nota4"/>,<bean:write name="EPS" property="nota4"/>],
            ['5',<bean:write name="EP" property="nota5"/>,<bean:write name="EPS" property="nota5"/>]
        ]);

        var options = {
            title: 'Catidad de estudiantes con nota 1, 2, 3, 4 y 5',
            vAxis: {title: "Cantidad de estudiantes"},
            hAxis: {title: "Nota"},
            seriesType: "bars",
            colors : ["#0099c6","#66aa00"],
            allowHtml : true,
            width : 500,
            height :300
        };

        var chart = new google.visualization.ComboChart(document.getElementById('combo-chart-por-nota'));
        chart.draw(data, options);
    }
    google.setOnLoadCallback(drawVisualization);
</script>

<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        
        var data = google.visualization.arrayToDataTable([
            ['Status', 'Estudiantes'],
            ['Aprobados',${aprobados}],
            ['Reprobados',${aplazados}],
            ['Retirados',<bean:write name="EP" property="retirados"/>]
        ]);

        var options = {
            title: 'Porcentaje de retirados, aprobados y aplazados',
            width : 500,
            height :300
        };

        var chart = new google.visualization.PieChart(document.getElementById('pie-chart-profesor'));
        chart.draw(data, options);
    }
</script>

<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        
        var data = google.visualization.arrayToDataTable([
            ['Nota', 'Estudiantes'],
            ['1',<bean:write name="EP" property="nota1"/>],
            ['2',<bean:write name="EP" property="nota2"/>],
            ['3',<bean:write name="EP" property="nota3"/>],
            ['4',<bean:write name="EP" property="nota4"/>],
            ['5',<bean:write name="EP" property="nota5"/>],
            ['Retirados',<bean:write name="EP" property="retirados"/>]
        ]);

        var options = {
            title: 'Porcentaje de estudiantes por nota',
            width : 500,
            height :300
        };

        var chart = new google.visualization.PieChart(document.getElementById('pie-chart-nota'));
        chart.draw(data, options);
    }
</script>