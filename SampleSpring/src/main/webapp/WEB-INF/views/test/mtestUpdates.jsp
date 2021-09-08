<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function(){
		
		$("#updateBtn").on("click",function(){
			$("#actionForm").attr("action","mtestUpdate");
			$("#actionForm").submit();
		});
		
		$("#deleteBtn").on("click",function(){
			$("#actionForm").attr("action","mtestDelete");
			$("#actionForm").submit();
		});
	});
</script>
</head>
<body>
	<form action="#" id="actionForm" method="post">
		<input type="hidden" name="m_no" value="${data.M_NO }"/>
	</form>
	
	번호: ${data.M_NO}<br/>
	아이디: ${data.M_ID}<br/>
	이름: ${data.M_NM}<br/>
	번호: ${data.M_PHONE}<br/>
	가입일: ${data.M_JOIN}<br/>
	
	<input type="button" id="updateBtn" value="수정">
	<input type="button" id="deleteBtn" value="삭제">
</body>
</html>