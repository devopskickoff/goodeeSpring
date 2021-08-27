<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript">
	//화면이 다 그려진 후 function 실행 / 하나의 함수만 할당가능
	window.onload = function(){
		
	};
	
	//화면이 준비가 완료된 후 function 실행 / 여러번 사용해도 동작, 순서대로 실행
	$(document).ready(function(){
		$("tbody").on("click","tr",function(){
			$("#no").val($(this).attr("no")); //인자가 한개일 경우 값 할당
/* 			console.log($("#no").val());
			
			alert($(this).attr("no"));
			$(this).attr("no","click");
 */
 			$("#goForm").submit();
		});
	});
/* jquery 이벤트 할당 방법*/
/* 	$(셀렉터).이벤트(함수); 	=> $("#btn").click(function(){}); */
	
/* 	$(셀렉터).on(이벤트,함수); => $("#btn").on("click",function(){}); */
 
/*  $(셀렉터1).on(이벤트, 셀렉터2, 함수);
 => $("#wrap").on("click","#btn",function(){});
 */ 
 
 /* jquery 값을 다루는 규칙 : 값과 관련된 함수는 항상 2개가 존재, 인자 개수는 1개 차이난다*/
 
</script>
</head>
<body>
<form action="test3Dtl" method="post" id="goForm">
	<input type="hidden" id="no" name="no" value="">
</form>
<table border="1">
	<thead>
		<tr>
			<th>번호</th>
			<th>제복</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="data" items="${list}">
			<tr no="${data.no}">
				<td>${data.no}</td>
				<td>${data.title}</td>
			</tr>
		
		</c:forEach>
	</tbody>
</table>
</body>
</html>