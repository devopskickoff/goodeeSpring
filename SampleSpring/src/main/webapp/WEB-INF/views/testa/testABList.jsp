<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	if("${param.searchGbn}"!=""){
		$("#searchGbn").val("${param.searchGbn}");
	}
	
	reloadList();
});

// 데이터 취득
function reloadList(){
	var params = $("#actionForm").serialize();
	$.ajax({//jQuery의 ajax함수 호출
		url: "testABLists", //접속주소
		type: "post", //전송방식
		dataType: "json", //받아올 데이터 형태
		data: params, //보낼 데이터 (문자열 형태)
		success: function(res){ // 성공 (ajax통신 성공)시 다음 함수 실행
			drawList(res.list);
		},
		error: function(request, status, error){ //실패시 다음 함수 실행
			console.log(error);
		}
	});
}

function drawList(list){
	var html = "";
	
	for(var data of list) {
		html+= "<tr no=\""+ data.B_NO +"\"> ";
		html+= "<td>"+data.B_NO+"</td>";
		html+= "<td>"+data.B_TITLE+"</td>";
		html+= "<td>"+data.M_NM+"</td>";
		html+= "<td>"+data.B_DT+"</td>";
		html+= "<td>"+data.B_HIT+"</td>";
		html+= "</tr>";
	}
	
	$("tbody").html(html);
}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty sMNo}">
			<input type="button" value="로그인" id="loginBtn"/>
		</c:when>
		<c:otherwise>
			${sMNm}님 어서오세요. <input type="button" value="로그아웃" id="logoutBtn"/>
		</c:otherwise>
	</c:choose>
<div>
	<form action="#" id="actionForm" method="post">
		<select name="searchGbn" id="searchGbn">
			<option value="0">제목</option>
			<option value="1">작성자</option>
		</select>
		<input type="text" name="searchTxt" id="searchTxt" value="${param.searchTxt}" />
		<input type="hidden" name="oldTxt" id="oldTxt" value="${param.searchTxt}" />
		<input type="hidden" name="page" id="page" value="${page}" />
		<input type="hidden" name="no" id="no"/>
		<input type="button" value="검색" id="searchBtn" />
		<c:if test="${!empty sMNo}">
			<input type="button" value="작성" id="addBtn"/>
		</c:if>
	</form>
</div>
<div>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>aa</td>
				<td>aaa</td>
				<td>2020</td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>
<div class="paging_wrap">
	<span>처음</span>
	<span>이전</span>
	<span><b>1</b></span>
	<span>2</span>
	<span>다음</span>
	<span>마지막</span>
</div>
</body>
</html>