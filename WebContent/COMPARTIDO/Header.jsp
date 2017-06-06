<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><%= session.getAttribute("titulo") %> - ULSA Ventas</title>
    
    <%@ include file="Librerias.jsp" %>
    
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
                <a class="navbar-brand" href="#" style="color:white;"><span class="glyphicon glyphicon-shopping-cart"></span> <strong>ULSA</strong> Ventas</a>
            </div>

            <div class="collapse navbar-collapse" id="myNavbar">

                <ul class="nav navbar-nav">
                    <li class="active"><a href="#" data-toggle="tooltip" data-placement="bottom" title="Inicio"><span class="glyphicon glyphicon-home"></span></a></li>
                    <li class=""><a href="#" data-toggle="tooltip" data-placement="bottom" title="Publicar"><span class="glyphicon glyphicon-plus"></span></a></li>
                </ul>
                
                <%@ include file="LoginParcial.jsp" %>

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