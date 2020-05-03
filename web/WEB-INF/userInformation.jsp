<%--
  Created by IntelliJ IDEA.
  User: yinyuman
  Date: 03/05/2020
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-15" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Les informations de l'utilisateur</title>
    <style>
        table {
            margin: 100px;
        }
        tr td {
            margin: 20px;
        }
    </style>
</head>
<body>

<table>
    <tr>
        <td>Nom d'utilisateur</td>
        <td>${utilisateur.nom}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${utilisateur.email}</td>
    </tr>
</table>
<a href="/user_logout"><button class="sansLabel">Déconnecter</button></a>
</body>
</html>
