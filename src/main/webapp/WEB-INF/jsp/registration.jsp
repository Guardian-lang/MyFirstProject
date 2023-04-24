<%--
  Created by IntelliJ IDEA.
  User: Ahmed
  Date: 21.04.2023
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
  <label for="first_name">Name:
    <input type="text" name="first_name" id="first_name">
  </label><br/>
    <label for="last_name">Surname:
        <input type="text" name="last_name" id="last_name">
    </label><br/>
  <c:forEach var="gender" items="${requestScope.genders}">
    <input type="radio" name="gender" VALUE="${gender}"> ${gender}
    <br/>
  </c:forEach>
  <label for="birth_date">Date of birth:
    <input type="date" name="birth_date" id="birth_date">
  </label><br/>
  <label for="birth_date">Date of birth:
    <input type="date" name="birth_date" id="birth_date">
  </label><br/>
  <label for="email">Email:
    <input type="text" name="email" id="email">
  </label><br/>
  <label for="pwd">Password:
    <input type="password" name="pwd" id="pwd">
  </label><br/>
  <input type="submit" value="Send">
</form>
<c:if test="${not empty requestScope.errors}">
  <div style="color: red">
    <c:forEach var="error" items="${requestScope.errors}">
      <span>${error.message}</span>
      <br>
    </c:forEach>
  </div>
</c:if>
</body>
</html>
