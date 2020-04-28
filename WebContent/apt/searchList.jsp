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
	function pageMove(pg) { 
		document.getElementById("pg").value=pg;
		document.location.href = "${root}/apt.do?act=searchAll&pg=" + pg;
	}
</script>
</head>

<body>
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="act" id="act" value="notice"> 
		<input type="hidden" name="pg" id="pg" value="">
	</form>
	<div class="container" align="center">
		<div class="col-lg-8" align="center">
			<h2>실거래가 목록</h2>
			<form id="searchform" method="get" class="form-inline" action="">
				<table class="table table-borderless">
					<tr>
						<td align="right">
							<button type="button" class="btn btn-primary"
								onclick="javascript:searchAll();">전체 조회</button> <select
							class="form-control" name="key" id="key">
								<option value="aptname" selected="selected">아파트 이름</option>
								<option value="dongname">동 이름</option>
						</select> <input type="text" class="form-control" placeholder="검색어 입력."
							name="word" id="word">
							<button type="button" class="btn btn-primary"
								onclick="javascript:search();">검색</button>
						</td>
					</tr>
				</table>
			</form>


			<c:forEach var="deal" items="${deals}">
				<table class="table table-active">
					<tbody>
						<tr
							onclick="document.location.href='${root}/apt.do?act=show&no=${deal.no}'"
							style="cursor: pointer;">
							<td><strong>${deal.no}</strong></td>
						</tr>
						<tr class="table-info">
							<td>동 : ${deal.dong}</td>
							<td align="right">아파트 이름 : ${deal.aptName}</td>
						</tr>
						<tr>
							<td class="table-danger"><strong>거래액 :
									${deal.dealAmount} 거래일자 :
									${deal.dealYear}-${deal.dealMonth}-${deal.dealDay}</strong></td>
							<td>건축년도 : ${deal.buildYear} 면적 : ${deal.area} 층 :
								${deal.floor} 지번 : ${deal.jibun}</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			<table>
				<tr>
					<td>${navigation.navigator}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>