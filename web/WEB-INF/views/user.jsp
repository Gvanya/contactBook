<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>

    <script type="text/javascript">
        function validate_form(obj) {
            var valid = true;
            var lastName_pattern = /^[A-Za-z]{5,20}$/;
            var firstName_pattern = /^[A-Za-z]{2,20}$/;
            var age_pattern = /^\s[0-1]{1}[0-9]{0,2}$/;
            var gender_pattern = /^[A-Za-z]{1,8}$/;
            var phoneNumber_pattern = /^\+\(\d{3}\)\ \d{3}\-\d{2}\-\d{2}$/;

            if (!lastName_pattern.test(obj.lastName.value)) {
                valid = false;
                alert("Last Name must contain more then five letters");
            }
            if (!firstName_pattern.test(obj.firstName.value)) {
                valid = false;
                alert("First Name must contain more then two letters");
            }
            if (!age_pattern.test(obj.age.value)) {
                valid = false;
                alert("Age must contain only numbers");
            }
            if (!gender_pattern.test(obj.gender.value)) {
                valid = false;
                alert("Gender must be male or female");
            }
            if (!phoneNumber_pattern.test(obj.phoneNumber.value)) {
                valid = false;
                alert("Phone Number must contain +(xxx) xxx-xx-xx");
            }
            return valid;
        }
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Add User</title>
</head>
<body>
<caption><h2>Enter User</h2></caption>
<form method="POST" action='UserServlet' name="addUser" onsubmit="return validate_form(addUser);">
    <fieldset>
        <div>
            <label for="lastName">Last Name</label> <input type="text"
                                                             name="lastName" value="<c:out value="${user.lastName}" />"
                                                             placeholder="lastName" />
        </div>
        <div>
            <label for="firstName">First Name</label> <input type="text"
                                                             name="firstName" value="<c:out value="${user.firstName}" />"
                                                             placeholder="firstName" />
        </div>
        <div>
            <label for="age">Age</label> <input type="text"
                                                           name="age" value="<c:out value="${user.age}" />"
                                                           placeholder="age" />
        </div>

        <div>
           <label for="gender">Gender:</label>
                <select name="gender">
                    <option value=""></option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
        </div>

        <div>
            <label for="phoneNumber">Phone Number</label> <input type="text" name="phoneNumber"
                                                  value="<c:out value="${user.phoneNumber}" />" placeholder="phoneNumber" />
        </div>
        <div>
            <input type="submit" value="Submit" />
        </div>
    </fieldset>
    <input type="hidden" name="userId" value="${user.userId}" />
</form>
<p><a href="UserServlet?action=listUser">Back</a></p>
</body>
</html>