<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="lang-button-block">
    <div id="locale">
        <form action="${pageContext.request.contextPath}/locale" method="post">
            <button class="lang-button" name="lang" type="submit" value="ru_RU">ru</button>
            <button class="lang-button" name="lang" type="submit" value="en_EN">en</button>
        </form>
    </div>
    <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang :
                                                        (param.lang != null ? param.lang : 'en_EN')}"/>
    <fmt:setBundle basename="translations"/>

</div>
