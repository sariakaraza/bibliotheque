<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Catalogue des Livres</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>
    <h1>Catalogue des Livres</h1>
    <c:if test="${sessionScope.userType == 'adherant'}">
    <a href="${pageContext.request.contextPath}/pret/mes-prets" class="lien-catalogue"><h1> Mes prets</h1></a>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>
    <c:choose>
        <c:when test="${not empty livres}">
            <table border="1" style="border-collapse: collapse; width: 100%; margin-top: 20px;">
                <thead>
                    <tr>
                        <th style="font-size: 18px; padding: 12px; background-color: var(--primary-color); color: white;">Titre</th>
                        <th style="font-size: 18px; padding: 12px; background-color: var(--primary-color); color: white;">Auteur</th>
                        <th style="font-size: 18px; padding: 12px; background-color: var(--primary-color); color: white;">Annee</th>
                        <th style="font-size: 18px; padding: 12px; background-color: var(--primary-color); color: white;">Numero Exemplaire</th>
                        <th style="font-size: 18px; padding: 12px; background-color: var(--primary-color); color: white;">Statut</th>
                        <c:if test="${sessionScope.userType == 'adherant'}">
                            <th style="font-size: 18px; padding: 12px; background-color: var(--primary-color); color: white;">Date Reservation</th>
                            <th style="font-size: 18px; padding: 12px; background-color: var(--primary-color); color: white;">Action</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="livre" items="${livres}">
                        <c:forEach var="ex" items="${livre.exemplaires}" varStatus="status">
                            <tr>
                                <c:choose>
                                    <c:when test="${status.first}">
                                        <td style="font-size: 16px; padding: 10px; font-weight: bold; vertical-align: top;" rowspan="${livre.exemplaires.size()}">
                                            ${livre.titre}
                                        </td>
                                        <td style="font-size: 16px; padding: 10px; vertical-align: top;" rowspan="${livre.exemplaires.size()}">
                                            ${livre.auteur}
                                        </td>
                                        <td style="font-size: 16px; padding: 10px; vertical-align: top;" rowspan="${livre.exemplaires.size()}">
                                            ${livre.anneePublication}
                                        </td>
                                    </c:when>
                                </c:choose>
                                
                                <td style="font-size: 16px; padding: 10px; text-align: center;">
                                    ${ex.idExemplaire}
                                </td>
                                <td style="font-size: 16px; padding: 10px;">
                                    <span class="statut">${ex.dernierStatutTemporaire}</span>
                                </td>
                                <c:if test="${sessionScope.userType == 'adherant'}">
                                    <td style="font-size: 16px; padding: 10px;">
                                        <form action="${pageContext.request.contextPath}/reservation/new" method="post">
                                            <input type="hidden" name="idExemplaire" value="${ex.idExemplaire}" />
                                            <input type="datetime-local" id="dateDebut_${ex.idExemplaire}" name="dateDebut" required />
                                    </td>
                                    <td style="font-size: 16px; padding: 10px; text-align: center;">
                                            <button type="submit" class="btn-reserver compact">Reserver</button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Aucun livre n'est disponible pour le moment.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>