<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form action="Vuelta" method="post">
            <h3>Información de actualización</h3>
            <c:set var="mensaje" value="${requestScope.actualizado}"/>

                <p><c:out value="${mensaje}"/></p>

            <button type="submit" name="volver" class="button-30">Cancelar</button>
        </form>

    </main>
    </body>
</html>

