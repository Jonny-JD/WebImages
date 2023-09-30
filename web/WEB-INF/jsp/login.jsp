<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <link rel="stylesheet" href="../../style/style.css">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body id="login">
<%@ include file="language-header.jsp"%>
<div class="login">
    <h1>Login</h1>
    <form action="${pageContext.request.contextPath}/login" method="post" enctype="application/x-www-form-urlencoded">
        <label for="email">
            <input type="email" name="email" placeholder="<fmt:message key="page.login.email"/>" id="email" required/>
        </label>
        <label for="password">
            <input type="password" name="password" placeholder="<fmt:message key="page.login.password"/>" id="password" required />
        </label>
        <button type="submit" class="btn btn-primary btn-block btn-large"><fmt:message key="page.login.login"/></button>
    </form>
    <p id="registration-login-ref"><fmt:message key="page.login.register.message" /> <a href="${pageContext.request.contextPath}/registration"><fmt:message key="page.reg.link"/> </a>
    </p>
</div>
</body>
</html>