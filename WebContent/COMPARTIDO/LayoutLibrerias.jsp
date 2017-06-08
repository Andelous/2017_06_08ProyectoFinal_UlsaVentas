<%
	String contextPath = request.getContextPath();
%>

<link rel="stylesheet" type="text/css" href="<%= contextPath %>/MISC/CSS/bootstrap.css" />

<script src="<%= contextPath %>/MISC/JS/jquery-3.2.1.js"></script>
<script src="<%= contextPath %>/MISC/JS/bootstrap.js"></script>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>