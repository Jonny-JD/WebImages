<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <link rel="stylesheet" href="../../style/style.css">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body id="registration">
<%@ include file="language-header.jsp"%>
<div class="login">
    <h1>Register</h1>
    <form action="${pageContext.request.contextPath}/registration" method="post" enctype="application/x-www-form-urlencoded">
        <label for="name">
            <input type="text" name="name" placeholder="<fmt:message key="page.reg.name"/>" id="name" required />
        </label>
        <label for="email">
            <input type="email" name="email" placeholder="<fmt:message key="page.login.email"/>" id="email" required/>
        </label>
        <label for="password">
            <input type="password" name="password" placeholder="<fmt:message key="page.login.password"/>" id="password" required/>
        </label>
        <label for="password-confirm">
            <input type="password" name="password-confirm" placeholder="<fmt:message key="page.reg.password-confirm"/>" id="password-confirm" required/>
        </label>
        <button type="submit" class="btn btn-primary btn-block btn-large"><fmt:message key="page.reg.send"/></button>
    </form>
    <p id="registration-login-ref"><fmt:message key="page.reg.login.message" /> <a href="${pageContext.request.contextPath}/login"><fmt:message key="page.login.link"/></a>
    </p>
</div>
</body>
</html>