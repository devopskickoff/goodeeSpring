<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello Spring!! ${a}
	<form action="testRec" method="post">
		<input type="text" name="msg"/>
		<input type="submit" value="전송"/>
	</form>
</body>
</html>