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
        <a href="${pageContext.request.contextPath}/admin/prolongements"" class="lien-catalogue">
            Voir la liste des prolongements
        </a>
        |
        <a href="${pageContext.request.contextPath}/penalites" class="lien-catalogue">
            Liste des penalises
        </a>
        |
        <a href="${pageContext.request.contextPath}/reservation" class="lien-catalogue">
            Voir la liste des reservations
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

    <h2>Liste des prets existants</h2>

    <table border="1" style="border-collapse: collapse; width: 80%;">
        <tr>
            <th>ID</th>
            <th>Adherent</th>
            <th>Exemplaire</th>
            <th>Titre du livre</th>
            <th>Date debut</th>
            <th>Date fin</th>
            <th>Type de pret</th>
        </tr>
        <c:forEach var="pret" items="${prets}">
            <tr>
                <td>${pret.idPret}</td>
                <td>${pret.adherant.nomAdherant} ${pret.adherant.prenomAdherant}</td>
                <td>${pret.exemplaire.libelle}</td>
                <td>${pret.exemplaire.livre.titre}</td>
                <td>${pret.dateDebut}</td>
                <td>${pret.dateFin}</td>
                <td>${pret.typePret.type}</td>
            </tr>
        </c:forEach>
    </table>


    <h2>Faire le pret d'un prolongement valide</h2>

    <table border="1" style="border-collapse: collapse; width: 80%;">
        <tr>
            <th>Numero</th>
            <th>Adherent</th>
            <th>Exemplaire</th>
            <th>Date du prolongement</th>
            <th>Action</th>
        </tr>
        <c:forEach var="p" items="${prolongementsValides}">
            <tr>
                <td>${p.idProlongement}</td>
                <td>${p.pret.adherant.nomAdherant} ${p.pret.adherant.prenomAdherant}</td>
                <td>${p.pret.exemplaire.idExemplaire} ${p.pret.exemplaire.livre.titre}</td>
                <td>${p.dateProlongement}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/pret/fromProlongement">
                        <input type="hidden" name="idAdherant" value="${p.pret.adherant.idAdherant}" />
                        <input type="hidden" name="idExemplaire" value="${p.pret.exemplaire.idExemplaire}" />
                        <input type="hidden" name="dateDebut" value="${p.dateProlongement}" />
                        <input type="hidden" name="idProlongement" value="${p.idProlongement}" />
                        <input type="hidden" name="idTypePret" value="1" />
                        <button type="submit">Faire le pret</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Faire le pret d'une reservation validee</h2>

    <table border="1" style="border-collapse: collapse; width: 80%;">
        <tr>
            <th>Numero</th>
            <th>Adherent</th>
            <th>Exemplaire</th>
            <th>Date de reservation</th>
            <th>Action</th>
        </tr>
        <c:forEach var="r" items="${reservationsValidees}">
            <tr>
                <td>${r.idReservation}</td>
                <td>${r.adherant.nomAdherant} ${r.adherant.prenomAdherant}</td>
                <td>${r.exemplaire.idExemplaire} ${r.exemplaire.livre.titre}</td>
                <td>${r.dateDeReservation}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/pret/fromReservation">
                        <input type="hidden" name="idAdherant" value="${r.adherant.idAdherant}" />
                        <input type="hidden" name="idExemplaire" value="${r.exemplaire.idExemplaire}" />
                        <input type="hidden" name="dateDebut" value="${r.dateDeReservation}" />
                        <input type="hidden" name="idReservation" value="${r.idReservation}" />
                        <input type="hidden" name="idTypePret" value="1" /> <!-- à domicile -->
                        <button type="submit">Faire le pret</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>


    <h2>Liste des adherents</h2>
    <table border="1" style="border-collapse: collapse; width: 60%;">
        <tr>
            <th style="font-size: 18px; padding: 12px;">ID</th>
            <th style="font-size: 18px; padding: 12px;">Numero</th>
            <th style="font-size: 18px; padding: 12px;">Nom</th>
            <th style="font-size: 18px; padding: 12px;">Prenom</th>
        </tr>
        <c:forEach var="adherant" items="${adherants}">
            <tr>
                <td style="font-size: 16px; padding: 10px;">${adherant.idAdherant}</td>
                <td style="font-size: 16px; padding: 10px;">${adherant.password}</td>
                <td style="font-size: 16px; padding: 10px;">${adherant.nomAdherant}</td>
                <td style="font-size: 16px; padding: 10px;">${adherant.prenomAdherant}</td>
            </tr>
        </c:forEach>
    </table>

    <br>

    <h2>Liste des exemplaires</h2>
    <table border="1" style="border-collapse: collapse; width: 60%;">
        <tr>
            <th style="font-size: 18px; padding: 12px;">Numero Exemplaire</th>
            <th style="font-size: 18px; padding: 12px;">Exemplaire</th>
            <th style="font-size: 18px; padding: 12px;">Titre du livre</th>
            <th style="font-size: 18px; padding: 12px;">Auteur</th>
        </tr>
        <c:forEach var="exemplaire" items="${exemplaires}">
            <tr>
                <td style="font-size: 16px; padding: 10px;">${exemplaire.idExemplaire}</td>
                <td style="font-size: 16px; padding: 10px;">${exemplaire.libelle}</td>
                <td style="font-size: 16px; padding: 10px;">${exemplaire.livre.titre}</td>
                <td style="font-size: 16px; padding: 10px;">${exemplaire.livre.auteur}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
