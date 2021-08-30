<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${param.no}단
	<c:forEach var="item" items="${list}">
		  <p>${item}</p>
	</c:forEach>
	<button type="button" onclick="location.href='testGugu'">돌아가기</button>
</body>
</html>