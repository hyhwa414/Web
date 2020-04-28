<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이트맵</title>
<%@ include file="/style/page.jsp"%>
</head>
<body>
	<div align="center">
		<br>
		<h2>사이트맵</h2>
		<br><a href="${root}/apt/search.jsp"><strong> -거래검색</strong></a>
		<br><br><a href="${root}/notice.do?act=notice"><strong>-공지사항</strong></a>
	</div>
</body>
</html>