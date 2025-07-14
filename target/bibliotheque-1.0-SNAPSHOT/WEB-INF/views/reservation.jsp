<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Reservations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<h1>Liste des Reservations</h1>

<table border="1" style="border-collapse: collapse; width: 100%;">
    <tr>
        <th style="font-size: 18px; padding: 12px;">ID</th>
        <th style="font-size: 18px; padding: 12px;">Adherent</th>
        <th style="font-size: 18px; padding: 12px;">Livre</th>
        <th style="font-size: 18px; padding: 12px;">Date</th>
        <th style="font-size: 18px; padding: 12px;">Statut</th>
    </tr>
    <c:forEach var="res" items="${reservations}">
        <tr>
            <td style="font-size: 16px; padding: 10px;">${res.idReservation}</td>
            <td style="font-size: 16px; padding: 10px;">
                ${res.adherant.nomAdherant} ${res.adherant.prenomAdherant}
            </td>
            <td style="font-size: 16px; padding: 10px;">
                ${res.exemplaire.livre.titre}
            </td>
            <td style="font-size: 16px; padding: 10px;">
                ${res.dateDeReservation}
            </td>
            <td>
                ${res.statut}
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/reservation/valider">
                    <input type="hidden" name="idReservation" value="${res.idReservation}" />
                    <button type="submit">Valider</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
