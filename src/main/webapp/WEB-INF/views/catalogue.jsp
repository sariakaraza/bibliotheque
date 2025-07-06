<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Catalogue des Livres</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<h1>Catalogue des Livres</h1>

<c:choose>
    <c:when test="${not empty livres}">
        <c:forEach var="livre" items="${livres}">
            <h2>${livre.titre}</h2>
            <p>Auteur : ${livre.auteur} | Année : ${livre.anneePublication}</p>
            <ul>
                <c:forEach var="ex" items="${livre.exemplaires}">
                    <li>
                        Exemplaire n°${ex.idExemplaire} -
                        <span class="statut">${ex.dernierStatutTemporaire}</span>
                    </li>
                </c:forEach>
            </ul>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>Aucun livre n'est disponible pour le moment.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
