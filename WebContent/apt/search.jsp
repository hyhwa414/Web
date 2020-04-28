<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>검색</title>
<%@ include file="/style/page.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
	function searchAll() {
		document.location.href = "${root}/apt.do?act=searchAll&pg=1";
	}
	function search() {
		if (document.getElementById("word").value == "") {
			alert("내용 입력 !!!");
			return;
		}

		var sel = document.getElementById("key");
		var tmp = document.getElementById("word");
		if (sel.options[sel.selectedIndex].value == "aptname") {
			document.location.href = "${root}/apt.do?act=searchAptName&pg=1&word="
					+ tmp.value;
		} else if (sel.options[sel.selectedIndex].value == "dongname") {
			document.location.href = "${root}/apt.do?act=searchDong&pg=1&word="
					+ tmp.value;
		}
	}
</script>
</head>

<body>
	</form>
	<div class="container" align="center">
		<div class="col-lg-8" align="center">
			<br>
			<h2>실거래가 목록</h2>
			<br>
			<form id="searchform" method="get" class="form-inline" action="">
				<table class="table table-borderless">
					<tr>
						<td align="right">
							<button type="button" class="btn btn-primary"
								onclick="javascript:searchAll();">전체 조회</button> <select
							class="form-control" name="key" id="key">
								<option value="aptname" selected="selected">아파트 이름</option>
								<option value="dongname">동 이름</option>
						</select> <input type="text" class="form-control" placeholder="검색어 입력"
							name="word" id="word">
							<button type="button" class="btn btn-primary"
								onclick="javascript:search();">검색</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>