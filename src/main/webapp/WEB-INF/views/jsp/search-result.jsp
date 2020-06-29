<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Search result - ${keywork}</title>

    <jsp:include page="partials/head.jsp" />
</head>
<body>
<jsp:include page="partials/moveTop.jsp" />
<header>
    <jsp:include page="partials/header.jsp" />
</header>
<article>
    <jsp:include page="partials/slider.jsp" />
    <jsp:include page="partials/rent.jsp"/>
    <jsp:include page="partials/map.jsp" />
</article>
<footer>
    <jsp:include page="partials/footer.jsp" />
</footer>
</body>
</html>