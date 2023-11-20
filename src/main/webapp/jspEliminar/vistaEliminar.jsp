<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <form action="ControllerEliminar" method="post">
            <h3>Elige una o mas Aves para Eliminar</h3>
            <c:set var="error" value="${requestScope.error}" /> 
            <c:if test="${not empty error}">
                <c:out value="${error}"/>
                </c:if>
            <c:set var="lista" value="${sessionScope.eliminacion}" /> 
            <c:if test="${not empty lista && lista.size() >= 1}">
            <div class="dos">
               
                <c:forEach var="ave" items="${lista}" >
                    <c:set var="anilla" value="${ave.anilla}" /> 
                    <c:set var="especie" value="${ave.especie}" /> 
                    <p><input type="checkbox" name="elige" value="${anilla}"> - <c:out value="${especie}"/></p>
                    <br/>
                </c:forEach>
            </c:if>
            </div>

                    
            
            <button type="submit" name="enviar" class="button-30">Realizar</button>
            <button type="submit" name="volver" class="button-30">Cancelar</button>
        </form>

    </main>
    </body>
</html>
