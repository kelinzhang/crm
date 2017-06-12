
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">

    <%@include file="common.jsp" %>
    <script type="text/javascript" src="../../js/plugins/jquery_portal/jquery.portal.js"></script>
    
  
    <link media="all" href="../../css/index.css" type="text/css" rel="stylesheet">
    <link media="all" href="../../js/plugins/jquery_portal/iportal.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
    	$('#pt').protal(options);
    	
        // create the panel
        var p = $('<div></div>').appendTo('body');
        p.panel({
        	title: 'My Title',
        	height:150,
        	closable: true,
        	collapsible: true
        });
         
        // add the panel to portal
        $('#pt').portal('add', {
        	panel: p,
        	columnIndex: 0
        });
    	
    </script>
</head>
<body>




</body>
</html>
