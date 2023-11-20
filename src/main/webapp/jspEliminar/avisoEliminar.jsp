<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contexto" value="${pageContext.request.contextPath}"/>
<c:set var="style" value="${contexto}/css/style.css"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="${style}"/>
        <title>Eliminación</title>
    </head>
    <body>
                 <main class="container">
        <form action="Vuelta" method="post">
            <h3>Informacion sobre Eliminaciones</h3>
            <c:set var="lista" value="${sessionScope.borrar}" /> 
            
            
            <p>se han eliminado <c:out value="${fn:length(lista)}"/> registros</p>
            <button type="submit" name="volver" class="button-30">Cancelar</button>
        </form>

    </main>
    </body>
</html>
