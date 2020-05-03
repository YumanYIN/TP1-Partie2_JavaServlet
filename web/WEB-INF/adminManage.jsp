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
    <title>Admin Management Page</title>
</head>
<body>

<table>
    <tr>
        <th></th>
        <th>Nom d'utilisateur</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td></td>
            <td><c:out value="${user.nom}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><a href="#">Supprimer</a></td>
        </tr>
    </c:forEach>
</table>

<a href="/admin_logout"><button class="sansLabel">Déconnecter</button></a>

</body>
</html>
