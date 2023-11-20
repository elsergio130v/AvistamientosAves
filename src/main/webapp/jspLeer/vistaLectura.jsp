<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contexto" value="${pageContext.request.contextPath}"/>
<c:set var="style" value="${contexto}/css/style.css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${style}"/>
        <title>Vista Todas</title>
    </head>
    <body>
        <main class="container">
            <form action="Vuelta" method="post">
                <table>
                    <tr>
                        <th>Anilla</th>
                        <th>Especie</th>
                        <th>Lugar</th>
                        <th>Fecha</th>
                    </tr>
                <c:set var="lista" value="${requestScope.aves}"/>
                <c:forEach var="lista" items="${requestScope.aves}">
                    <tr>
                        <td><c:out value="${lista.anilla}"/></td>
                        <td><c:out value="${lista.especie}"/></td>
                        <td><c:out value="${lista.lugar}"/></td>
                        <td><c:out value="${lista.fecha}"/></td>
                    </tr>
                    </c:forEach>
                </table>
                    <button type="submit" name="volver" class="button-30">volver</button>
        </form>
        </main>
    </body>
</html>
