<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>    
<!DOCTYPE html>
<html lang="ko">
<head>
<title>회원정보수정</title>
<%@ include file="/style/page.jsp"%>
<script type="text/javascript">
function modifyInfo() {
	if($("#username").val() == "") {
		alert("이름 입력!!!");
		return;
	} else if($("#userpwd").val() == "") {
		alert("비밀번호 입력!!!");
		return;
	} else if($("#userpwd").val() != $("#pwdcheck").val()) {
		alert("비밀번호 확인!!!");
		return;
	} else if($("#userpwd").val() != $("#pwdcheck").val()) {
		alert("비밀번호 확인!!!");
		return;
	} else {
	  	document.getElementById("memberform").action = "${root}/user.do";
	  	document.getElementById("memberform").submit();
	}
  }
</script>
</head>
<body>
<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<form id="memberform" method="post" action="">
		<input type="hidden" name="act" id="act" value="modify">
			<div class="form-group" align="left">
				<input type="hidden" name="userid" id="userid" value="${userinfo.id}">
			</div>
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="username" name="username" value="${userinfo.name}">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="userpwd" name="userpwd" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호재입력</label>
				<input type="password" class="form-control" id="pwdcheck" name="pwdcheck" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="tel">전화번호</label>
				<div id="tel" class="custom-control-inline">
				<select class="form-control" id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="032">032</option>
					<option value="041">041</option>
					<option value="051">051</option>
					<option value="061">061</option>
				</select> _
				<input type="text" class="form-control" id="tel2" name="tel2" placeholder="">
				<input type="text" class="form-control" id="tel3" name="tel3" placeholder="">
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="">주소</label><br>
				<input type="text" class="form-control" id="address" name="address" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn" onclick="javascript:modifyInfo();">수정완료</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>