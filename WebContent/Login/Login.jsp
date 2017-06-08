<%
	
	session.setAttribute("titulo", "Login");
	session.setAttribute("layoutPaginaActiva", "login");
	
%>

<%@ include file="../COMPARTIDO/LayoutHeader.jsp" %>

<%
	Boolean loginExitoso = (Boolean)session.getAttribute("loginExitoso");
	String loginUsuario = (String)session.getAttribute("loginUsuario");
%>

<div class="row">
    <div class="col-md-4"></div>

    <div class="col-md-4">
		<form action="<%= request.getContextPath() %>/ServletUsuario" class="form-horizontal" method="post">
			<input type="hidden" name="servletUsuarioAccion" value="login"/>
			
			<h2>Iniciar sesión.</h2>
			
			<%
				if (loginExitoso != null && !loginExitoso) {
					%>
						<div class="alert alert-danger">
							Error al iniciar sesión.
						</div>
					<%
				}
			%>
			
		            <hr>
		            <div class="input-group">
		                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		                
		                <input class="form-control" id="usuario" name="usuario" type="text" value="<%= loginExitoso != null && !loginExitoso ? loginUsuario : "" %>" placeholder="Usuario">
		            </div>
		            <br>
		            <div class="input-group">
		                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
		                <input class="form-control" id="contrasena" name="contrasena" type="password" placeholder="Contraseña">
		            </div>
		            <br>
		            <div class="form-group">
		                <div class="col-md-3"></div>
		                <div class="col-md-6">
		                    <input type="submit" value="Iniciar sesión" class="btn btn-default btn-block">
		                </div>
		                <div class="col-md-3"></div>
		            </div>
		            <p>
		                <a href="<%= request.getContextPath() %>/Login/Registro.jsp">Registrar como nuevo usuario</a>
		            </p>
		</form>    
	</div>

    <div class="col-md-4"></div>
</div>

<%@include file="../COMPARTIDO/LayoutFooter.jsp" %>


<%
	session.setAttribute("loginExitoso", null);
	session.setAttribute("loginUsuario", null);
%>