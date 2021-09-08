<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function(){
		$("#addBtn").on("click",function(){
			alert("test");
			$("#addForm").submit();
		});
		
		$("#cancelBtn").on("click",function(){
			location.href="mtestList";
		});
	});
</script>
</head>
<body>
	<form action="mtestAdds" id="addForm" method="post">
		이름 <input type="text" id="m_nm" name="m_nm" /><br /> 
		아이디 <input	type="text" id="m_id" name="m_id" /><br />
		비밀번호 <input type="password" id="m_pw" name="m_pw"/><br />
		전화번호 <input type="text" id="m_phone" name="m_phone"/><br />
	</form>
	<input type="button" value="저장" id="addBtn" />
	<input type="button" value="취소" id="cancelBtn" />
	</form>
</body>
</html>