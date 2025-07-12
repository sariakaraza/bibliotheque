<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des penalites</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<h1>Adherents Penalises</h1>

<table border="1" style="border-collapse: collapse; width: 100%;">
    <tr>
        <th style="font-size: 18px; padding: 12px;">ID</th>
        <th style="font-size: 18px; padding: 12px;">Nom</th>
        <th style="font-size: 18px; padding: 12px;">Prenom</th>
        <th style="font-size: 18px; padding: 12px;">Date debut</th>
        <th style="font-size: 18px; padding: 12px;">Date fin</th>
    </tr>
    <c:forEach var="penalite" items="${penalites}">
        <tr>
            <td style="font-size: 16px; padding: 10px;">${penalite.adherant.idAdherant}</td>
            <td style="font-size: 16px; padding: 10px;">${penalite.adherant.nomAdherant}</td>
            <td style="font-size: 16px; padding: 10px;">${penalite.adherant.prenomAdherant}</td>
            <td style="font-size: 16px; padding: 10px;">${penalite.dateDebut}</td>
            <td style="font-size: 16px; padding: 10px;">${penalite.dateFin}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
