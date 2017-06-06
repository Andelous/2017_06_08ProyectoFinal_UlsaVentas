<%@ page import="modelo.Usuario" %>

<%
	Usuario usuario = (Usuario)session.getAttribute("usuario");

	if(usuario != null) {
		%>
            <div class="nav navbar-nav navbar-right" style="margin-right: 0px">
                <div class="btn-group">
                    <a href="#" title="Manage" class="btn btn-default navbar-btn">
                        <strong><%= usuario.getUsuario() %></strong> &nbsp;<span class="glyphicon glyphicon-user"></span>
                    </a>
                    <a href="javascript:document.getElementById('logoutForm').submit()" class="btn btn-danger navbar-btn">
                        Cerrar sesión &nbsp;<span class="glyphicon glyphicon-log-out"></span>
                    </a>
                </div>
            </div>
		<%
	} else {
		%>
			<div class="nav navbar-nav navbar-right" style="margin-right: 0px">
			    <div class="btn-group">
			        <a href="#" class="btn btn-success navbar-btn">
			            Regístrate &nbsp;<span class="glyphicon glyphicon-user"></span>
			        </a>
			        <a href="#" class="btn btn-primary navbar-btn">
			            Iniciar sesión &nbsp;<span class="glyphicon glyphicon-log-in"></span>
			        </a>
			    </div>
			</div>
		<%
	}
%>