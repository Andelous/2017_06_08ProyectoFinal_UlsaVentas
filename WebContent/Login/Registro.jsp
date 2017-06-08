<%
	
	session.setAttribute("titulo", "Registro");
	session.setAttribute("layoutPaginaActiva", "registro");
	
%>

<%@ include file="../COMPARTIDO/LayoutHeader.jsp" %>

<%
	Boolean usuarioRegistrado = (Boolean)session.getAttribute("usuarioRegistrado");
	Usuario usuarioRegistradoObj = null;
			
	if (usuarioRegistrado != null && !usuarioRegistrado)
		usuarioRegistradoObj = (Usuario)session.getAttribute("usuarioRegistradoObj");
%>

<div class="row">
	
    <div class="col-md-3"></div>

    <div class="col-md-6">
		<form action="<%= request.getContextPath() %>/ServletUsuario" method="post">
			<input type="hidden" name="servletUsuarioAccion" value="registro"/>            
			<h2 class="page-header">Registrarse.</h2>
				
				<%
					if (usuarioRegistrado != null && !usuarioRegistrado) {
						%>
							<div class="alert alert-danger">
								No se pudo registrar al usuario.
							</div>
						<%
					}
				%>
				
	            <div class="form-group">
	                <label class="control-label" for="Usuario">Nombre de usuario</label>
                    <input class="form-control" id="Usuario" name="usuario" type="text" value="<%= usuarioRegistrado != null && !usuarioRegistrado ? usuarioRegistradoObj.getUsuario() : "" %>" placeholder="Usuario">
	            </div>
	            <div class="form-group">
	                <label class="control-label" for="Password">Contraseña</label>
                    <input class="form-control" id="Password" name="contrasena" type="password" placeholder="Contraseña">
	            </div>
	            <div class="form-group">
	                <label class="control-label" for="ConfirmPassword">Confirmar contraseña</label>
                    <input class="form-control" id="ConfirmPassword" name="contrasena" type="password" placeholder="Confirmar contraseña">
	            </div>
	            <div class="form-group">
	                <label class="control-label" for="Nombre">Nombre(s)</label>
                    <input class="form-control" id="Nombre" name="nombres" type="text" placeholder="Nombre(s)" value="<%= usuarioRegistrado != null && !usuarioRegistrado ? usuarioRegistradoObj.getNombres() : "" %>">
	            </div>
	            <div class="form-group">
	                <label class="control-label" for="apellidoPaterno">Apellido paterno</label>
                    <input class="form-control" id="apellidoPaterno" name="apellidoPaterno" type="text" placeholder="Apellido paterno" value="<%= usuarioRegistrado != null && !usuarioRegistrado ? usuarioRegistradoObj.getApellidoPaterno() : "" %>">
	            </div>
	            <div class="form-group">
	                <label class="control-label" for="apellidoMaterno">Apellido materno</label>
                    <input class="form-control" id="apellidoMaterno" name="apellidoMaterno" type="text" placeholder="Apellido materno" value="<%= usuarioRegistrado != null && !usuarioRegistrado ? usuarioRegistradoObj.getApellidoMaterno() : "" %>">
	            </div>
	            <div class="form-group">
	                <div class="col-md-offset-4 col-md-4">
	                    <input type="submit" class="btn btn-default btn-block" value="Registrarse">
	                </div>
	            </div>
		</form>    
	</div>

    <div class="col-md-3"></div>
</div>

<%@include file="../COMPARTIDO/LayoutFooter.jsp" %>

<%
	if(usuarioRegistrado != null) {
		session.setAttribute("usuarioRegistrado", null);
		session.setAttribute("usuarioRegistradoObj", null);
	}
%>