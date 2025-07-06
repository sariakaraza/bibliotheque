<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Effectuer un pret</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>
    <h2>
        <a href="${pageContext.request.contextPath}/catalogue" class="lien-catalogue">
            Voir la liste des livres
        </a>
        |
        <a href="${pageContext.request.contextPath}/retour" class="lien-catalogue">
            Retourner les prets
        </a>
        |
        <a href="${pageContext.request.contextPath}/prolonger" class="lien-catalogue">
            Prolonger un pret
        </a>
    </h2>


    <h1>EFFECTUER UN PRET</h1>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/pret/save" method="post">
    <label for="idExemplaire">Numero de l'exemplaire :</label>
    <input type="number" id="idExemplaire" name="idExemplaire" required><br><br>

    <label for="idAdherant">Numero de l'adherent :</label>
    <input type="number" id="idAdherant" name="idAdherant" required><br><br>

    <label for="idTypePret">Type de pret :</label>
    <select name="idTypePret" id="idTypePret" required>
        <option value="1">A domicile</option>
        <option value="2">Sur place</option>
    </select><br><br>

    <label for="dateDebut">Date du pret :</label>
    <input type="datetime-local" id="dateDebut" name="dateDebut"><br><br>

    <button type="submit">Enregistrer le pret</button>
    </form>


</body>
</html>
