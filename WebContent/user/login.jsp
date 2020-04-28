<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:if test="${cookie.ssafy_id.value ne null}">
	<c:set var="saveid" value="${cookie.ssafy_id.value}" />
	<c:set var="ckid" value=" checked=\"checked\"" />
</c:if>
<head>
<title>로그인 화면</title>
<%@ include file="/style/page.jsp"%>
<script type="text/javascript">
	function login() {
		if (document.getElementById("userid").value == "") {
			alert("아이디 입력!!!");
			return;
		} else if (document.getElementById("userpwd").value == "") {
			alert("비밀번호 입력!!!");
			return;
		} else {
			document.getElementById("loginform").action = "${root}/user.do";
			document.getElementById("loginform").submit();
		}
	}

	function findpwd() {
		document.location.href = "${root}/user/findpwd.jsp";
	}
</script>
</head>
<body>
	<br><div align="center">
		<c:if test="${userinfo == null}">
			<h4>${msg}</h4>
		</c:if>
	</div>
	<br>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="loginform" method="post" action="">
				<input type="hidden" name="act" value="login">
				<div class="form-group" align="left">
					<label for="">아이디</label> <input type="text" class="form-control"
						id="userid" name="userid" placeholder="" value="${saveid}">
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호</label> <input type="password"
						class="form-control" id="userpwd" name="userpwd" placeholder=""
						onkeydown="javascript:if(event.keyCode == 13) {login();}">
				</div>
				<div class="form-group form-check" align="right">
					<label class="form-check-label"> <input
						class="form-check-input" type="checkbox" id="idsave" name="idsave"
						value="saveok" ${ckid}> 아이디저장
					</label>
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-warning"
						onclick="javascript:login();">로그인</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:findpwd();">비밀번호찾기</button>
				</div>
			</form>
		</div>
	</div>
</body>
