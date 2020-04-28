<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/style/page.jsp"%>
<title>공지</title>
</head>
<body>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="loginform" method="post" action="">
				<input type="hidden" name="act" value="login"> <br>
				<div class="form-group" align="left">
					<label for=""><strong>글번호 : </strong> ${list.no}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>작성자 : </strong> ${list.id}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>작성일 : </strong> ${list.regtime}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>제목 : </strong>${list.title}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>내용 : </strong>${list.content}</label>
				</div>
				<div class="form-group" align="center">
					<c:if test="${userinfo != null}">
						<a href="${root}/notice.do?act=mvmodify&no=${list.no}">게시글 수정</a>
						<a href="${root}/notice.do?act=delete&no=${list.no}">게시글 삭제</a>
					</c:if>
				</div>
			</form>
		</div>
	</div>
</body>
</html>