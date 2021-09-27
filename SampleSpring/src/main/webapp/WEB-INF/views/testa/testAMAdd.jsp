<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#att {
	display:none;
}

</style>
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="resources/script/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="resources/script/jquery/jquery.form.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$("#cancelBtn").on("click", function() {
		$("#goForm").submit();
	});
	
	$("#id").on("change",function(){
		 var params = $("#addForm").serialize();
         
         $.ajax({
            url: "testAMIdCheckAjax",
            type: "post",
            dataType: "json",
            data: params,
            success: function(res){
 				if(res.cnt > 0){
 					$("#check").html("중복된 아이디가 있습니다");
 					$("#check").css("color","red");
 					$("#checkId").val("false");
 				} else {
 					$("#check").html("사용 가능한 아이디입니다");
 					$("#check").css("color","green");
 					$("#checkId").val("true");
 				}
            },
            error: function(request,status,error){
               console.log(error);
            }
         });
	});
	
    $("#fileBtn").on("click",function(){
		$("#att").click(); 
	});
    
    $("#att").on("change",function(){
  	  $("#fileName").html($(this).val().substring($(this).val().lastIndexOf("\\") + 1)); 
		var fileForm = $("#fileForm");
		fileForm.ajaxForm({
			success:function(res){
				if(res.result == "SUCCESS"){
					if(res.fileName.length > 0){
    			 		$("#mFile").val(res.fileName[0]);
    			 		$("#imgBox").attr("src","resources/upload/"+res.fileName[0]);
    			 	}
				}
			},
	   		 error: function(req, status, error){
				 console.log(error)
				 alert("파일 업로드 중 문제가 발생하였습니다");
			 }
		});
		fileForm.submit();
     });
	
	$("#addBtn").on("click", function() {
		if(checkVal("#id")) {
			alert("아이디를 입력해 주세요.");
			$("#id").focus();
		} else if($("#checkId").val() == "false"){
			alert("아이디 중복체크를 해주세요");
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
	        var params = $("#addForm").serialize();
	         
	         $.ajax({
	            url: "testAMCUDAjax",
	            type: "post",
	            dataType: "json",
	            data: params,
	            success: function(res){
	               if(res.result=="success"){
	                  location.href="testAMList";
	               } else if(res.result=="failed"){
	                  alert("작성에 실패하였습니다.");
	               } else {
	                  alert("작성 중 문제가 발생했습니다.");
	               }
	            },
	            error: function(request,status,error){
	               console.log(error);
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
<form id="fileForm" action="fileUploadAjax" method="post" ectype="multipart/form-data">
	<input type="file" name="att" id="att"/>
</form>

<form action="testAMList" method="post" id="goForm">
	<input type="hidden" name="searchGbn" value="${param.searchGbn}"/>
	<input type="hidden" name="searchGbn" value="${param.searchTxt}"/>
	<input type="hidden" name="searchGbn" value="${param.page}"/>
</form>
<form action="#" id="addForm" method="post">
<input type="hidden" name="gbn" value="c"/>
<input type="hidden" id="checkId" value="false"/>
	아이디 <input type="text" id="id" name="id" /><span id="check"></span><br/>
	비밀번호 <input type="password" id="pw" name="pw" /><br/>
	비밀번호확인 <input type="password" id="repw" /><br/>
	이름 <input type="text" id="nm" name="nm" /><br/>
	전화번호 <input type="text" id="phone" name="phone" /><br/>
	프로필 사진 선택 : <input type="button" value="첨부파일선택" id="fileBtn"/>
	<span id="fileName"></span>
	<img id="imgBox"/>
	<input type="hidden" name="mFile" id="mFile"/>
</form>
<input type="button" id="addBtn" value="등록" />
<input type="button" id="cancelBtn" value="취소" />
</body>
</html>








