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
	$('#listBtn').on("click",function(){
		location.href ="testList";
	});
});
</script>
</head>
<body>
번호 : ${data.NO}</br>
제목 : ${data.TITLE}</br>
작성자 : ${data.WRITER}</br>
작성일 : ${data.DT}</br>
내용 : ${data.CON}</br>
<input type="button" value="목록으로" id="listBtn"/>
</body>
</html>