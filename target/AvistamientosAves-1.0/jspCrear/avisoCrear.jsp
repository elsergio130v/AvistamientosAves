<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contexto" value="${pageContext.request.contextPath}"/>
<c:set var="style" value="${contexto}/css/style.css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${style}"/>
        <title>Creado</title>
    </head>
    <body>
        <main class="container">
            <form action="Vuelta" method="post">
            <h3>Creación de Avistamiento</h3>
            <c:set var="ave" value="${requestScope.objetoCrear}"/>
            <fmt:formatDate value="${ave.fecha}" var="fechaFormateada" pattern="yyyy-MM-dd" />
            <p>Anilla: <c:out value="${ave.anilla}"/></p>
            <p>Especie: <c:out value="${ave.especie}"/></p>
            <p>Lugar: <c:out value="${ave.lugar}"/></p>
            <p>Fecha: <c:out value="${fechaFormateada}"/></p>
            <button type="submit" name="volver" class="button-30">inicio</button>

        </form>

    </main>
        
    </body>
</html>
