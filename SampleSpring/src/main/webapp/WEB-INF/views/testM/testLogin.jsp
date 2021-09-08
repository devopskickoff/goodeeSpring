<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#loginBtn").on("click",function(){
		if(checkVal("#id")){
			alert("아이디를 입력해주세요");
			$("#id").focus();
		} else if(checkVal("#pw")){
			alert("비밀번호를 입력해주세요");
			$("#pw").focus();
		} else {
			$("#loginForm").submit();
		}
	});
});

function checkVal(sel) {
	if($.trim($(sel).val()) == "") {
		return true;
	} else {
		return false;
	}
}

</script>
</head>
<body>
<form action="testLogins" id="loginForm" method="post">
아이디 <input type="text" id="id" name="id"/><br/>
비밀번호 <input type="password" id="pw" name="pw"/>
</form>

<input type="button" value="로그인" id="loginBtn"/>

</body>
</html>








