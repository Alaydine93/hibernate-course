<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="row">
    <div class="column">
        Liste femmes : <BR/>
        <TABLE>
            <c:forEach var="joueur" items="${listeFemmes}">
                <TR>
                    <TD>${joueur.prenom}</TD><TD>${joueur.nom}</TD>
                </TR>
            </c:forEach>
        </TABLE>
    </div>
    <div class="column">
        Liste hommes : <BR/>
        <TABLE>
            <c:forEach var="joueur1" items="${listeHommes}">
                <TR>
                    <TD>${joueur1.getSexe()}</TD><TD>${joueur1.nom}</TD>
                </TR>
            </c:forEach>
        </TABLE>
    </div>
</div>
</body>
</html>
