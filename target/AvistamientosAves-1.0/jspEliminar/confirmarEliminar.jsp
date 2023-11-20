<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <h3>¿Estas seguro de que deseas eliminar estos campos?</h3>

            <c:set var="lista" value="${sessionScope.borrar}" /> 
            <c:if test="${not empty lista && lista.size() >= 1}">
            <div class="dos">
                <c:forEach var="ave" items="${lista}" >
                    <c:set var="anilla" value="${ave.anilla}" /> 
                    <c:set var="especie" value="${ave.especie}" /> 
                    <c:set var="lugar" value="${ave.lugar}" /> 
               <fmt:formatDate value="${ave.fecha}" var="fechaFormateada" pattern="yyyy-MM-dd" />
                    <c:set var="fecha" value="${fechaFormateada}" /> 
                    <p><c:out value="${anilla}"/> - <c:out value="${especie}"/> - <c:out value="${lugar}"/> - <c:out value="${fecha}"/></p>
                    <br/>
                </c:forEach>
            </c:if>
            </div>

                    
            
            <button type="submit" name="enviarConf" class="button-30">Eliminar</button>
            <button type="submit" name="volver" class="button-30">Cancelar</button>
        </form>

    </main>
    </body>
</html>
