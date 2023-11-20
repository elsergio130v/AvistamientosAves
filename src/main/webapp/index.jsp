<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contexto" value="${pageContext.request.contextPath}"/>
<c:set var="style" value="${contexto}/css/style.css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${style}"/>
    <title>Avistamientos de Aves</title>
</head>
<body>
    <main class="container">
        <form action="FrontController" method="post">
            <h3>Avistamiento de Aves</h3>
            <button type="submit" name="enviar" value="crear" class="button-30">Crear Avistamiento</button>
            <button type="submit" name="enviar" value="leer" class="button-30">Leer Todo</button>
            <button type="submit" name="enviar" value="actualizar" class="button-30">Actualizar</button>
            <button type="submit" name="enviar" value="eliminar" class="button-30">Eliminar</button>
        </form>

    </main>
    
</body>
</html>
