<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Find User</title>
</head>
<body>
<caption><h2>Find User</h2></caption>
<form name="search_form" action="UserServlet?action=find" method="get">
<fieldset>
    <div>
        <label for="filter">Filter:</label>
        <select name="filter">
            <option value=""></option>
            <option value="lastName">Last Name</option>
            <option value="firstName">First Name</option>
            <option value="age">Age</option>
            <option value="phoneNumber">Phone Number</option>
        </select>
        <div>
            <label for="search">Search</label> <input type="text" name="search"
                                                                 value="<c:out value="${search}" />" placeholder="search" />
        </div>
        <label for="gender">Gender:</label>
        <select name="gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
        <div>
            <input type="hidden" name="action" value="find"/>
            <input type="submit" value="Search" />
        </div>
    </div>
</fieldset>
</form>


<c:choose>
    <c:when test="${empty users}">
        <p>The list is empty</p><br>
    </c:when>
    <c:otherwise>
        <table border="1" cellpadding="5">
            <caption><h2>List of found users</h2></caption>
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
<p><a href="UserServlet?action=listUser">Back</a></p>
</body>
</html>
