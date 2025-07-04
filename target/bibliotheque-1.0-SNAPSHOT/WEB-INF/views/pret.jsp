<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Effectuer un pret</title>
    <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css"> --%>
</head>
<body>
    <h1>EFFECTUER UN PRET</h1>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/pret/save" method="post">
        <label for="idExemplaire">Numéro de l'exemplaire :</label>
        <input type="number" id="idExemplaire" name="idExemplaire" required><br><br>

        <label for="idAdherant">Numéro de l'adhérent :</label>
        <input type="number" id="idAdherant" name="idAdherant" required><br><br>

        <label for="idTypePret">Type de prêt :</label>
        <select name="idTypePret" id="idTypePret" required>
            <option value="1">À domicile</option>
            <option value="2">Sur place</option>
        </select><br><br>

        <button type="submit">Enregistrer le prêt</button>
    </form>

</body>
</html>
