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
        <title>Actualizar</title>
    </head>
    <body>
         <main class="container">
        <form action="ControllerUpdate" method="post">
            <h3>Crear Avistamiento de Aves</h3>
            <c:set var="ave" value="${requestScope.aveActualizar}"/>
            
            <c:set var="error" value="${requestScope.error}"/>
            <c:if test="${not empty error}"> 
                <p>errores: <c:out value="${error}"/></p>
            </c:if>
                <fmt:formatDate value="${ave.fecha}" var="fechaFormateada" pattern="yyyy-MM-dd" />
                <input type="text" name="anilla" placeholder="Anilla" value="${ave.anilla}" readonly>
            <input type="text" name="especie" placeholder="Especie" value="${ave.especie}">
            <input type="text" name="lugar" placeholder="Lugar" value="${ave.lugar}">
            <input type="date" name="fecha" placeholder="Fecha" value="${fechaFormateada}">
            <button type="submit" name="enviarForm" class="button-30">Añadir</button>
            <button type="submit" name="volver" class="button-30">Cancelar</button>
        </form>

    </main>
    </body>
</html>
