<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>All users</title>
</head>
<body>
<c:choose>
    <c:when test="${empty users}">
        <p>The contact book is empty</p><br>
        <a href="${pageContext.request.contextPath}UserServlet?action=create">Add User</a>
    </c:when>
    <c:otherwise>


        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>User ID</th>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Phone Number</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.userId}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.age}" /></td>
                    <td><c:out value="${user.gender}" /></td>
                    <td><c:out value="${user.phoneNumber}" /></td>
                    <td><a href="UserServlet?action=update&userId=<c:out value="${user.userId}"/>">Update</a></td>
                    <td><a href="UserServlet?action=delete&userId=<c:out value="${user.userId}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<p><a href="UserServlet?action=insert">Add User</a></p>
<p><a href="UserServlet?action=find">Find User</a></p>
</body>
</html>