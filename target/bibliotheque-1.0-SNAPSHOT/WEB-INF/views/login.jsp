<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - Bibliothèque</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h1>CONNEXION A LA BIBLIOTHEQUE</h1>

    <form action="login" method="post">
        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>Mot de passe:</label>
        <input type="password" name="password" required><br>

        <button type="submit">Se connecter</button>

        <p th:if="${error}" style="color:red;">${error}</p>
    </form>

    <p>Pas encore inscrit ? <a href="inscription">Inscrivez-vous ici</a></p>
</body>
</html>
