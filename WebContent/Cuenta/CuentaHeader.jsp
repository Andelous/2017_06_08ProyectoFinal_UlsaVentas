<%
	session.setAttribute("layoutPaginaActiva", "cuenta");
	
	String cuentaPaginaActiva = session.getAttribute("cuentaPaginaActiva").toString();
%>

<%@ include file="../COMPARTIDO/LayoutHeader.jsp" %>
<%@ include file="../COMPARTIDO/LayoutValidacionUsuario.jsp" %>

<div class="row">
    <div class="col-sm-3">
        
        <h2 class="page-header"><span class="glyphicon glyphicon-cog"></span> Opciones</h2>

        <div class="btn-group-vertical" style="width:100%;">

            <a href="index.jsp" class="btn btn-default btn-lg <%= cuentaPaginaActiva.equals("resumen") ? "active" : "" %>" style="text-align:left;">
                <span class="glyphicon glyphicon-th-list"></span> &nbsp;&nbsp;Resumen</a>
            
            <a href="Informacion.jsp" class="btn btn-default btn-lg <%= cuentaPaginaActiva.equals("informacion") ? "active" : "" %>" style="text-align:left;">
                <span class="glyphicon glyphicon-info-sign"></span> &nbsp;&nbsp;Informaci�n de perfil</a>
            
            <a href="Publicaciones.jsp" class="btn btn-default btn-lg <%= cuentaPaginaActiva.equals("publicaciones") ? "active" : "" %>" style="text-align:left;">
                <span class="glyphicon glyphicon-tags"></span> &nbsp;&nbsp;Mis publicaciones</a>
            
            <a href="Favoritos.jsp" class="btn btn-default btn-lg <%= cuentaPaginaActiva.equals("favoritos") ? "active" : "" %>" style="text-align:left;">
                <span class="glyphicon glyphicon-heart"></span> &nbsp;&nbsp;Mis favoritos</a>
            
            <a href="Compras.jsp" class="btn btn-default btn-lg <%= cuentaPaginaActiva.equals("compras") ? "active" : "" %>" style="text-align:left;">
                <span class="glyphicon glyphicon-usd"></span> &nbsp;&nbsp;Mis compras</a>

        </div>

    </div>

    <div class="col-sm-9">
        <h2 class="page-header">
        	<%
        		switch (cuentaPaginaActiva) {
        			case "resumen":
        				%><span class="glyphicon glyphicon-th-list"></span><%
        				break;
        			case "informacion":
        				%><span class="glyphicon glyphicon-info-sign"></span><%
        				break;
        			case "publicaciones":
        				%><span class="glyphicon glyphicon-tags"></span><%
        				break;
        			case "favoritos":
        				%><span class="glyphicon glyphicon-heart"></span><%
        				break;
        			case "compras":
        				%><span class="glyphicon glyphicon-usd"></span><%
        				break;
        		}
        	%>
            &nbsp;<%= session.getAttribute("titulo").toString() %>
        </h2>