<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><!-- 메시지 출력을 위한 tag -->
<!DOCTYPE html>
<html>
<!--  -->
<head><title><spring:message code="greeting"/></title></head>
<body>
	<h1><spring:message code="greeting"/></h1>
	<div>현재 언어 : ${ pageContext.response.locale }</div>
	<a href="index.do?lang=ko"><spring:message code="label.ko" /></a><!-- 원하는 언어로 코딩하는 해줌 -->
	<a href="index.do?lang=en"><spring:message code="label.en" /></a>
	<a href="other.do">다른 페이지로 이동</a>
</body>
</html>