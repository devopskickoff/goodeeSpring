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
	$("#cancelBtn").on("click", function() {
		history.back();
	});
	
	$("#addBtn").on("click", function() {
		if(checkVal("#id")) {
			alert("아이디를 입력해 주세요.");
			$("#id").focus();
		} else if(checkVal("#pw")) {
			alert("비밀번호를 입력해 주세요.");
			$("#pw").focus();
		} else if(checkVal("#repw")) {
			alert("비밀번호 확인을 입력해 주세요.");
			$("#repw").focus();
		} else if($("#pw").val() != $("#repw").val()) {
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("#pw").val("");
			$("#repw").val("");
			$("#pw").focus();
		} else if(checkVal("#nm")) {
			alert("이름을 입력해 주세요.");
			$("#nm").focus();
		} else {
			//$("#addForm").submit();
			
			var params = $("#addForm").serialize();
			
			$.ajax({
				url: "testMAdds2",
				type: "post",
				dataType: "json",
				data: params,
				success: function(res){
					if(res.result=="success"){
						location.href = "testMList2"
					}
				},
				error: function(request, status, error){
					console.log(error)
				}
				
			});
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
<form action="testMAdds" id="addForm" method="post">
아이디 <input type="text" id="id" name="id" /><br/>
비밀번호 <input type="password" id="pw" name="pw" /><br/>
비밀번호확인 <input type="password" id="repw" /><br/>
이름 <input type="text" id="nm" name="nm" /><br/>
전화번호 <input type="text" id="phone" name="phone" />
</form>
<input type="button" id="addBtn" value="등록" />
<input type="button" id="cancelBtn" value="취소" />
</body>
</html>








