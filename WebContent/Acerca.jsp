<%
	session.setAttribute("titulo", "Acerca de nosotros");
	session.setAttribute("layoutPaginaActiva", "acerca");
%>

<%@ include file="COMPARTIDO/LayoutHeader.jsp" %>

<h2 class="page-header"><%= session.getAttribute("titulo").toString() %></h2>

<div class="row">
    <div class="col-md-6">
        <p>Proyecto escolar de Laboratorio de programación de Servidores Web</p>
    </div>
    <div class="col-md-6">
        <p>Nuestras oficinas:</p>
        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBosm5YSmCWuTxoAYG2GGSFF3yLamdXNJE"></script>
        
		<div id="map" style="height: 400px; width: 100%;"></div>
		
		<script>
		
		    function initMap() {
		        var lat1 = 17.021963;
		        var lng1 = -96.720713;
		
		        var uluru = { lat: lat1, lng: lng1 };
		        var map = new google.maps.Map(document.getElementById('map'), {
		            zoom: 17,
		            center: uluru
		        });
		        var marker = new google.maps.Marker({
		            position: uluru,
		            map: map
		        });
		    }
		
		    initMap();
		</script>
        
    </div>
</div>

<%@include file="COMPARTIDO/LayoutFooter.jsp" %>