<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Početna</title>
</head>
<body>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/svi">Pregled aerodroma</a><br/>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/odabirAerodroma">Pregled jednog aerodroma</a><br/>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/odabirUdaljenostiOdDo">Pregled udaljenosti 2 aerodroma</a><br/>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/odabirSvihUdaljenosti">Pregled svih udaljenosti</a><br/>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/odabirNajduljiPut">Pregled najduljeg puta države</a><br/>
	<a href="${pageContext.servletContext.contextPath}/mvc/meteo/odabirMeteoAerodrom">Pregled meteo podataka za aerodrom</a><br/>
	<a href="${pageContext.servletContext.contextPath}/mvc/meteo/odabirMeteoAdresa">Pregled meteo podataka za adresu</a><br/>
</body>
</html>