<%

	session.setAttribute("titulo", "Inicio");
	session.setAttribute("layoutPaginaActiva", "inicio");
	
	List<Publicacion> listaPublicacion = (List<Publicacion>)session.getAttribute("indexListaPublicacion");
	
%>

<%@ include file="COMPARTIDO/LayoutHeader.jsp" %>

<div class="jumbotron">
    <h1><span class="glyphicon glyphicon-shopping-cart"></span> ULSA Ventas</h1>
    
    <p class="lead">
        Bienvenido a ULSA Ventas, el lugar donde comprar y vender tus cosas.
    </p>
</div>

<%
	if(listaPublicacion != null && listaPublicacion.size() > 0) {
		
		%>
		   <h3 class="page-header">Productos que quizá te interesen</h3>
		    <div class="row">
		    
		    <% 
		    	for(Publicacion p : listaPublicacion) {
		    	
		    		%>
		    		
			            <div class="col-md-4">
			                
			                <div class="row">
			
			                    <div class="col-md-6">
			                        <img class="img-circle img-responsive" src="<%= p.getDireccionImagen() %>" />
			                    </div>
			
			                    <div class="col-md-6">
			                        <h5 class="page-header">
			                            <a class="text-primary"><%= p.getTitulo() %></a>
			                        </h5>
			                        <p class="text-muted">
			                            <%= p.getDescripcion() %>
			                        </p>
			                    </div>
			
			                </div>
			
			            </div>
		    		
		    		<%
		    		
		    	}
	    	%>
		    
		    </div>
		<%
		
	} else {
		%>
		    <div class="alert alert-info">
		        Ups, parece que aún no hay publicaciones ¡Sé el primero en publicar!
		    </div>
		<%
	}
%>

<hr />

<div class="row">

	<%
		
		if (usuario == null) {
			%>
			
		        <div class="col-md-4">
		            <h2>¡Comienza ya!</h2>
		            <p>
		                Crea una cuenta y comienza a comprar y vender.
		            </p>
		            <p><a class="btn btn-success btn-block" href="<%= request.getContextPath() %>/Login/Registro.jsp">Regístrate <span class="glyphicon glyphicon-user"></span></a></p>
		        </div>
		        <div class="col-md-4">
		            <h2>¿Ya eres miembro?</h2>
		            <p>Inicia sesión para ver tus compras y ventas.</p>
		            <p><a class="btn btn-primary btn-block" href="<%= request.getContextPath() %>/Login/Login.jsp">Iniciar sesión <span class="glyphicon glyphicon-log-in"></span></a></p>
		        </div>
			
			<%
		}
	%>

    <div class="<%= usuario != null ? "col-md-12" : "col-md-4" %>">
        <h2>Proyecto escolar</h2>
        <p>Proyecto escolar para la materia Desarrollo Web II. Universidad LaSalle Oaxaca</p>
    </div>
</div>


<%@include file="COMPARTIDO/LayoutFooter.jsp" %>