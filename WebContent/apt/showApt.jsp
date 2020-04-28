<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/style/page.jsp"%>
<title>거래 상세정보 조회</title>
</head>
<body>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="loginform" method="post" action="">
				<input type="hidden" name="act" value="login"> <br>
				<div class="form-group" align="left">
					<label for=""><strong>거래번호 : </strong> ${deal.no}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>동 : </strong> ${deal.dong}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>아파트 이름 : </strong> ${deal.aptName}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>거래액 : </strong>${deal.dealAmount}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>거래일자 : </strong>${deal.dealYear}-${deal.dealMonth}-${deal.dealDay}</label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>건축년도 : </strong>${deal.buildYear} </label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>면적 : </strong>${deal.area} </label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>층 : </strong>${deal.floor} </label>
				</div>
				<div class="form-group" align="left">
					<label for=""><strong>지번 : </strong>${deal.jibun} </label>
				</div>
			</form>
		</div>
	</div>
</body>
</html>