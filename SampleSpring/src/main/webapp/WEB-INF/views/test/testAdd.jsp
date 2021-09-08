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
	$(document).ready(function() {
		$("#cancelBtn").on("click", function() {
			history.back();
		});

		$("#addBtn").on("click", function() {
			if (checkVal("#title")) {
				alert("제목을 입력하세요");
				$("#title").focus();
			} else if (checkVal("#writer")) {
				alert("작성자를 입력하세요");
				$("#writer").focus();
			} else if (checkVal("#con")) {
				alert("내용을 입력하세요");
				$("#con").focus();
			} else {
				$("#addForm").submit();
			}
		});
		console.log("checkval" + ("#title"));
	});//document ready end

	function checkVal(sel) {
		if ($.trim($(sel).val()) == "") {
			return true;
		} else {
			return false;
		}
	}
</script>

</head>

<body>
	<form action="testAdds" id="addForm" method="post">
		제목 <input type="text" id="title" name="title" /><br /> 아이디 <input
			type="text" id="writer" name="writer" /><br /> 이름<br />
		<textarea rows="8" cols="40" id="con" name="con"></textarea>
	</form>
	<input type="button" value="저장" id="addBtn" />
	<input type="button" value="취소" id="cancelBtn" />
	</form>
</body>
</html>