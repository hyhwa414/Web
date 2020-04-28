<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>회원 가입</title>
<%@ include file="/style/page.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$("#registerBtn").click(function() {
		var pw = $("#userpwd").val();
		 var num = pw.search(/[0-9]/g);
		 var eng = pw.search(/[a-z]/ig);
		 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		if($("#username").val() == "") {
			alert("이름 입력!!!");
			return;
		} else if($("#userid").val() == "") {
			alert("아이디 입력!!!");
			return;
		} else if(pw.length < 8 || pw.length > 20){
			  alert("8자리 ~ 20자리 이내로 입력해주세요.");
			  return;
		}else if(pw.search(/\s/) != -1){
			  alert("비밀번호는 공백 없이 입력해주세요.");
			  return;
		}else if(num < 0 || eng < 0 || spe < 0 ){
			  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
			  return;
		 } else if($("#userpwd").val() != $("#pwdcheck").val()) {
			alert("비밀번호 확인!!!");
			return;
		} else if($("#tel2").val() == "") {
			alert("번호 입력!!!");
			return;
		} else if($("#tel3").val() == "") {
			alert("번호 입력!!!");
			return;
		} else if($("address").val() == "") {
			alert("주소 입력!!!");
			return;
		} else {
			$("#memberform").attr("action", "${root}/user.do?").submit();
		}
	});	 
});
</script>
</head>
<body>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="memberform" method="post" action="">
				<input type="hidden" name="act" id="act" value="join"> <br>
				<div class="form-group" align="left">
					<label for="name">이름</label> <input type="text"
						class="form-control" id="username" name="username" placeholder="">
				</div>
				<div class="form-group" align="left">
					<label for="">아이디</label> <input type="text" class="form-control"
						id="userid" name="userid" placeholder="">
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호</label> <input type="password"
						class="form-control" id="userpwd" name="userpwd"
						placeholder="8자리 이상 영문,숫자,특수문자를 혼합">
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호재입력</label> <input type="password"
						class="form-control" id="pwdcheck" name="pwdcheck" placeholder="">
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
						<input type="text" class="form-control" id="tel2" name="tel2" placeholder=""> _ 
						<input type="text" class="form-control"	id="tel3" name="tel3" placeholder="">
					</div>
				</div>
				<div class="form-group" align="left">
					<label for="">주소</label><br> <input type="text"
						class="form-control" id="address" name="address" placeholder="">
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-primary" id="registerBtn">회원가입</button>
					<button type="reset" class="btn btn-warning">초기화</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>