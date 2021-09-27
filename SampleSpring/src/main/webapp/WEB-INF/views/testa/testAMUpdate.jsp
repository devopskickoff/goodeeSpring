<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>           
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#att {
	display: none;
}

.hide_btn {
	display: none;
}

#imgBox{
	width: 200px;	
}

</style>
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="resources/script/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="resources/script/jquery/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#cancelBtn").on("click", function() {
		history.back();
	});
	
   //첨부 파일 버튼
   $("#fileBtn").on("click",function(){
	  $("#att").click(); 
   });
   
   //첨부파일 선택 시 
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
   
   //첨부파일 삭제 버튼
   $("#fileDelBtn").on("click",function(){
	   $("#imgBox").attr("src","");
	   $("#fileName").html(""); //사용자에게 보여지는 파일명
	   $("#bFile").val(""); // DB에 올라갈 파일명
	   $("#fileBtn").attr("class","");//첨부파일 선택 버튼 보이기
	   $(this).remove(); //첨부파일 삭제 버튼 제거 
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
					updateAjax();
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
			updateAjax();
		}
	});
});


function updateAjax(){
    var params = $("#updateForm").serialize();
    
    $.ajax({
       url: "testAMCUDAjax",
       type: "post",
       dataType: "json",
       data: params,
       success: function(res){
          if(res.result=="success"){
             $("#updateForm").attr("action","testAM");
             $("#updateForm").submit();
          } else if(res.result=="failed"){
             alert("수정에 실패하였습니다.");
          } else {
             alert("수정중 문제가 발생했습니다.");
          }
       },
       error: function(request,status,error){
          console.log(error);
       }
    });
}

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
<form id="fileForm" action="fileUploadAjax" method="post" enctype="multipart/form-data">
	<input type="file" name="att" id="att"/>
</form>
<form action="testMUpdates" id="updateForm" method="post">
	<input type="hidden" name="gbn" value="u"/>
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
	전화번호 <input type="text" id="phone" name="phone" value="${data.M_PHONE}" /><br/>
	프로필사진:<br/> <img id="imgBox" src="resources/upload/${data.M_IMG}"/><br/>
	 
	<c:choose>
		<c:when test="${!empty data.M_IMG }">
			<input type="button" value="사진선택" id="fileBtn" class="hide_btn"/>
		</c:when>
		<c:otherwise>
			<input type="button" value="사진선택" id="fileBtn"/>
		</c:otherwise>
	</c:choose>
   <c:set var="len" value="${fn:length(data.M_IMG)}"></c:set>
   <span id="fileName">${fn:substring(data.M_IMG, 20, len)}</span>
   <c:if test="${!empty data.M_IMG }">
   	<input type="button" value="첨부파일삭제" id="fileDelBtn"/>
   </c:if>
   <input type="hidden" name="mFile" id="mFile" value="${data.M_IMG}" />
</form>
<input type="button" id="updateBtn" value="수정" />
<input type="button" id="cancelBtn" value="취소" />
</body>
</html>








