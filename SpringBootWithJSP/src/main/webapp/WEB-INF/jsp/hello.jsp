<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
    <style>
        .hello-title {
            color: darkgreen;
        }
    </style>
</head>
<body>
<h2 class="hello-title">Hello ${name}!</h2>
My name is <c:out value="${name}"/>
<c:forEach var="i" begin="1" end="5">
    <p>Item <c:out value="${i}"/></p>
</c:forEach>
<script type="text/javascript">
    (function () {
        console.log("Hello World!");
    })();
</script>
</body>
</html>