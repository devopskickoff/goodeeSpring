<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<!-- 
	el tag 활용시
	param.~~~ << 이전 화면에서 넘어오는 데이터
	~~~~(이름만) << Controller에서 넘어오는 데이터
 -->

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
