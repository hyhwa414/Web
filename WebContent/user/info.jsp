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
<title>회원 정보</title>
<%@ include file ="/style/page.jsp" %>
</head>
<body>
<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<form id="loginform" method="post" action="">
		<input type="hidden" name="act" value="login"> 
			<br>
			<div class="form-group" align="left">
				<label for=""><strong>아이디</strong> : ${userinfo.id}</label>
			</div>
			<div class="form-group" align="left">
				<label for=""><strong>이름</strong> : ${userinfo.name}</label>
			</div>
			<div class="form-group" align="left">
				<label for=""><strong>주소 : </strong>${userinfo.addr}</label>
			</div>
			<div class="form-group" align="left">
				<label for=""><strong>전화번호 : </strong>${userinfo.phone}</label>
			</div>
			<div class="form-group" align="center">
				<a href="${root}/user.do?act=mvmodify&userid=${userinfo.id}">회원정보수정</a>
				<a href="${root}/user.do?act=delete&userid=${userinfo.id}">회원탈퇴</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>