<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>메인 화면</title>
<%@ include file="/style/home.jsp"%>
</head>
<body>
	<div class="row">
		<div class="leftcolumn">
			<div class="card">
				<h2>웹사이트 소개</h2>
				<h5>웹사이트 설명 4월 26일, 2020년</h5>
				<img src="img/무악동현대.jpg" style="max-width: 100%; height: auto;">
				<p>이 웹사이트는 4월 23일부터 제작되었으며, 지금까지 계속 수정, 보완, 업그레드 중입니다.</p>
				<p>더 보기..</p>
			</div>
		</div>
		<div class="rightcolumn">
			<div class="card">
				<h2>SSAFY 3기</h2>
				<h4>만든사람 : 임진섭</h4>
				<h4>만든사람 : 정현화</h4>
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="footer-inner">
			<div class="row">
				<div class="col-md-8">
					<div class="footer-content">
						<h2 class="title">Find Us</h2>
						<div class="separator-2"></div>
						<ul class="list-icons">
							<li><i class="fa fa-map-marker pr-2 text-default"></i>(SSAFY)
								멀티캠퍼스 8층 11반</li>
							<li><i class="fa fa-phone pr-2 text-default"></i> 1544-9001</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>