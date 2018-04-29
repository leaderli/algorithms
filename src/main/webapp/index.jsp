<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <style>

        img {
            width: 75%;  /* 移除这行 */
        }

    </style>
    <script>
        var test = document.getElementById("demo").innerHTML
        console.log(test)
    </script>
<body>
<h2>Hello World!</h2>
    <c:forEach var="img" items="${sessionScope.imgs}">
        <div style="text-align: center">
        <img src="/img/${img}">
            <br/>
            <br/>
        </div>
    </c:forEach>
</body>
</html>
