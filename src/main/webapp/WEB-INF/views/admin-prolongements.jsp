<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Prolongements</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<h1>Liste des Prolongements</h1>

<c:if test="${not empty successMessage}">
    <p style="color: green;">${successMessage}</p>
</c:if>
<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<table border="1" style="border-collapse: collapse; width: 90%;">
    <tr>
        <th>ID</th>
        <th>Adherent</th>
        <th>Livre</th>
        <th>Date Prolongement</th>
        <th>Statut</th>
        <th>Action</th>
    </tr>

    <c:forEach var="p" items="${prolongements}">
        <tr>
            <td>${p.idProlongement}</td>
            <td>${p.pret.adherant.nomAdherant} ${p.pret.adherant.prenomAdherant}</td>
            <td>${p.pret.exemplaire.livre.titre}</td>
            <td>${p.dateProlongement}</td>
            <td>${p.statut}</td>
            <td>
                <c:if test="${p.statut == 'en attente'}">
                    <form action="${pageContext.request.contextPath}/admin/prolongements/valider" method="post">
                        <input type="hidden" name="idProlongement" value="${p.idProlongement}" />
                        <button type="submit">Valider</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
