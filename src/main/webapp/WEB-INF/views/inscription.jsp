<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription - Bibliotheque</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/inscription.css">
</head>
<body>
    <h1>INSCRIPTION A LA BIBLIOTHEQUE</h1>

    <form action="inscription" method="post">
        <label>Nom:</label>
        <input type="text" name="nom" required><br>

        <label>Prenom:</label>
        <input type="text" name="prenom" required><br>

        <label>Date de naissance:</label>
        <input type="date" name="dateNaissance" required><br>

        <label>Profil :</label>
        <select name="nomProfil" required>
            <option value="Enfant">Enfant</option>
            <option value="Etudiant">Etudiant</option>
            <option value="Professeur">Professeur</option>
        </select>

        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>Mot de passe:</label>
        <input type="password" name="password" required><br>

        <button type="submit">S'inscrire</button>
    </form>

    <p>Deja inscrit ? <a href="login">Connectez-vous ici</a></p>
</body>
</html>
