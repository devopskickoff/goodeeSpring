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
	
	$("#updateBtn").on("click", function() {
		if($("#pw").val() != "") { // 비밀번호를 변경할 경우
			if(checkVal("#ocpw")) { // 기존비밀번호 입력여부
				alert("기존 비밀번호를 입력해 주세요.");
				$("#ocpw").focus();
			} else if($("#opw").val() == $("#ocpw").val()) { // 실 비밀번호와 입력된 기존비밀번호 비교
				if(checkVal("#repw")) {
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
					$("#updateForm").submit();
				}
			} else { // 비교 결과 같지 않은 경우
				alert("기존 비밀번호가 일치하지 않습니다.");
				$("#ocpw").val("");
				$("#ocpw").focus();
			}
		} else if(checkVal("#nm")) {
			alert("이름을 입력해 주세요.");
			$("#nm").focus();
		} else {
			$("#updateForm").submit();
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
<form action="testMUpdates" id="updateForm" method="post">
	<input type="hidden" name="no" value="${param.no}" />
	<input type="hidden" name="page" value="${param.page}" />
	<input type="hidden" name="searchGbn" value="${param.searchGbn}" />
	<input type="hidden" name="searchTxt" value="${param.searchTxt}" />
	회원번호 : ${data.M_NO}<br/>
	아이디 : ${data.M_ID}<br/>
	<input type="hidden" id="opw" value="${data.M_PW}" />
	기존비밀번호 <input type="password" id="ocpw" /><br/>
	비밀번호 <input type="password" id="pw" name="pw" /><br/>
	비밀번호확인 <input type="password" id="repw" /><br/>
	이름 <input type="text" id="nm" name="nm" value="${data.M_NM}" /><br/>
	전화번호 <input type="text" id="phone" name="phone" value="${data.M_PHONE}" />
</form>
<input type="button" id="updateBtn" value="수정" />
<input type="button" id="cancelBtn" value="취소" />
</body>
</html>








