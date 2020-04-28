<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:if test="${cookie.ssafy_id.value ne null}">
	<c:set var="saveid" value="${cookie.ssafy_id.value}" />
	<c:set var="ckid" value=" checked=\"checked\"" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기완료</title>
<%@ include file ="/style/page.jsp" %>
</head>
<body>
<div align="center">
<div class="container" align="center">
	<br><h2>${findinfo.id}(${findinfo.name})님의 비밀번호는 아래와 같습니다</h2>
	<br><h4>비밀번호 : <strong>${findinfo.password}</strong></h4>
</div>
</div>
</body>
</html>