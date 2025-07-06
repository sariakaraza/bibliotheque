<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Retourner un Pret</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<h1>Retourner un Pret</h1>

<form method="post" action="retour">
    <label for="pretId">Choisir un pret :</label>
    <select name="pretId" required>
        <c:forEach var="pret" items="${prets}">
            <option value="${pret.idPret}">
                Livre: ${pret.exemplaire.livre.titre} | Adherent: ${pret.adherant.nomAdherant} ${pret.adherant.prenomAdherant}
            </option>
        </c:forEach>
    </select>

    <label for="dateRetour">Date de retour :</label>
    <input type="datetime-local" name="dateRetour" required />

    <button type="submit">Valider le retour</button>
</form>


</body>
</html>
