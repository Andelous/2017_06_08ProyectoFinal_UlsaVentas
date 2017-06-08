<%@ include file="LayoutImports.jsp" %>

<%
	String layoutPaginaActiva = session.getAttribute("layoutPaginaActiva").toString();
	Usuario usuario = (Usuario)session.getAttribute("usuario");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><%= session.getAttribute("titulo") %> - ULSA Ventas</title>
    
    <%@ include file="LayoutLibrerias.jsp" %>
</head>

<body style="font-family:Open Sans">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%= request.getContextPath() %>" style="color:white;"><span class="glyphicon glyphicon-shopping-cart"></span> <strong>ULSA</strong> Ventas</a>
            </div>

            <div class="collapse navbar-collapse" id="myNavbar">

                <ul class="nav navbar-nav">
                    <li class="<%= layoutPaginaActiva.equals("inicio") ? "active" : "" %>"><a href="<%= request.getContextPath() %>" data-toggle="tooltip" data-placement="bottom" title="Inicio"><span class="glyphicon glyphicon-home"></span></a></li>
                    <li class="<%= layoutPaginaActiva.equals("publicar") ? "active" : "" %>"><a href="#" data-toggle="tooltip" data-placement="bottom" title="Publicar"><span class="glyphicon glyphicon-plus"></span></a></li>
                </ul>
                
				<%
					if(usuario != null) {
						%>
				            <div class="nav navbar-nav navbar-right" style="margin-right: 0px">
				                <div class="btn-group">
				                    <a href="<%= request.getContextPath() + "/Cuenta" %>" title="Manage" class="btn btn-default navbar-btn <%= layoutPaginaActiva.equals("cuenta") ? "active" : "" %>">
				                        <strong><%= usuario.getUsuario() %></strong> &nbsp;<span class="glyphicon glyphicon-user"></span>
				                    </a>
				                    <a href="<%= request.getContextPath() %>/ServletUsuario?servletUsuarioAccion=logout" class="btn btn-danger navbar-btn">
				                        Cerrar sesión &nbsp;<span class="glyphicon glyphicon-log-out"></span>
				                    </a>
				                </div>
				            </div>
						<%
					} else {
						%>
							<div class="nav navbar-nav navbar-right" style="margin-right: 0px">
							    <div class="btn-group">
							        <a href="<%= request.getContextPath() %>/Login/Registro.jsp" class="btn btn-success navbar-btn <%= layoutPaginaActiva.equals("registro") ? "active" : "" %>">
							            Regístrate &nbsp;<span class="glyphicon glyphicon-user"></span>
							        </a>
							        <a href="<%= request.getContextPath() %>/Login/Login.jsp" class="btn btn-primary navbar-btn <%= layoutPaginaActiva.equals("login") ? "active" : "" %>">
							            Iniciar sesión &nbsp;<span class="glyphicon glyphicon-log-in"></span>
							        </a>
							    </div>
							</div>
						<%
					}
				%>

                <form class="navbar-form navbar-right" action="#">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Búsqueda" name="q">
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Buscar</button>
                        </div>
                    </div>
                    
                </form>

            </div>

        </div>
    </nav>

    <div class="container" style="margin-top:50px">