<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes Prets</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<h1>Mes Prets</h1>

<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<c:if test="${not empty successMessage}">
    <p style="color: green;">${successMessage}</p>
</c:if>

<table border="1" style="border-collapse: collapse; width: 90%;">
    <tr>
        <th>Numero Pret</th>
        <th>Exemplaire</th>
        <th>Date Debut</th>
        <th>Date Fin</th>
        <th>Action</th>
    </tr>
    <c:forEach var="pret" items="${mesPrets}">
        <tr>
            <td>${pret.idPret}</td>
            <td>${pret.exemplaire.livre.titre}</td>
            <td>${pret.dateDebut}</td>
            <td>${pret.dateFin}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/pret/prolonger">
                    <input type="hidden" name="idPret" value="${pret.idPret}" />
                    <button type="submit">Prolonger</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
