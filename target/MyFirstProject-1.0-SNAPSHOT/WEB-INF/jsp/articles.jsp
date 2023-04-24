<%--
  Created by IntelliJ IDEA.
  User: Ahmed
  Date: 21.04.2023
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="article" items="${requestScope.articles}">
        <h2>${article.title}</h2>
        <h3><a href="${article.text}">Читать далее</a> </h3>
    </c:forEach>
</ul>
</body>
</html>