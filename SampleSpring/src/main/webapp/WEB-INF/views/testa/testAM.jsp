<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#imgBox {
	width: 200px;
	
}
</style>
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#listBtn").on("click", function() {
		$("#actionForm").attr("action", "testAMList");
		$("#actionForm").submit();
	});
	$("#updateBtn").on("click", function() {
		$("#actionForm").attr("action", "testAMUpdate");
		$("#actionForm").submit();
	});
	$("#deleteBtn").on("click", function() {
		if(confirm("삭제하시겠습니까?")) {
	        var params = $("#actionForm").serialize();
	         
	         $.ajax({
	            url: "testAMCUDAjax",
	            type: "post",
	            dataType: "json",
	            data: params,
	            success: function(res){
	               if(res.result=="success"){
	                  location.href="testAMList";
	               } else if(res.result=="failed"){
	                  alert("삭제에 실패하였습니다.");
	               } else {
	                  alert("삭제중 문제가 발생했습니다.");
	               }
	            },
	            error: function(request,status,error){
	               console.log(error);
	            }
	         });
		}
	});
});
</script>
</head>
<body>
<form action="#" id="actionForm" method="post">
	<input type="hidden" name="gbn" value="d"/>
	<input type="hidden" name="no" value="${param.no}" />
	<input type="hidden" name="page" value="${param.page}" />
	<input type="hidden" name="searchGbn" value="${param.searchGbn}" />
	<input type="hidden" name="searchTxt" value="${param.searchTxt}" />
</form>
<img id="imgBox" src="resources/upload/${data.M_IMG}"/><br/>
회원번호 : ${data.M_NO}<br/>
아이디 : ${data.M_ID}<br/>
이름 : ${data.M_NM}<br/>
전화번호 : ${data.M_PHONE}<br/>
가입일 : ${data.M_JOIN}<br/>

<input type="button" value="수정" id="updateBtn" />
<input type="button" value="삭제" id="deleteBtn" />
<input type="button" value="목록" id="listBtn" />
</body>
</html>



