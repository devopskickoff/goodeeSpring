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
</style>
<script type="text/javascript" src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="resources/script/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="resources/script/jquery/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   CKEDITOR.replace("con",{
      resize_enabled : false,
      language : "ko",
      enterMode : "2"
   });
   
   // 취소버튼
   $("#cancelBtn").on("click",function(){
      $("#backForm").submit();
   });
   
   // 엔터키 폼실행 막기
   $("#updateForm").on("keypress","input",function(event){
      if(event.keyCode == 13){ // 엔터키가 눌러졌을 때
         return false; // form 실행 이벤트를 하지 않음
      }
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
	   $("#fileName").html(""); //사용자에게 보여지는 파일명
	   $("#bFile").val(""); // DB에 올라갈 파일명
	   $("#fileBtn").attr("class","");//첨부파일 선택 버튼 보이기
	   $(this).remove(); //첨부파일 삭제 버튼 제거 
	   
   });
   
   
   // 수정 버튼
   $("#updateBtn").on("click",function(){
      // ck에디터 중에 con과 관련된 객체에서 데이터를 취득하여 textarea인 con에 값을 넣는다.
      $("#con").val(CKEDITOR.instances['con'].getData());
      
      if(checkVal("#title")){
         alert("제목을 입력해 주세요.");
         $("#title").focus();
      } else if(checkVal("#con")) {
         alert("내용을 입력해 주세요.")
      } else {
     	 var fileForm = $("#fileForm");
    	 fileForm.ajaxForm({
    		 success: function(res){
    			 if(res.result == "SUCCESS"){
    				 //업로드 파일명 적용
    			 	if(res.fileName.length > 0){
    			 		$("#bFile").val(res.fileName[0]);
    			 	}
    				 
    		         var params = $("#updateForm").serialize();
    		         
    		         $.ajax({
    		            url: "testABUpdates",
    		            type: "post",
    		            dataType: "json",
    		            data: params,
    		            success: function(res){
    		               if(res.result=="success"){
    		                  $("#backForm").submit();
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
    			 } else {
        			 alert("파일 업로드에 실패하였습니다");
    			 }
    			 
    		 },
    		 error: function(req, status, error){
    			 console.log(error)
    			 alert("파일 업로드 중 문제가 발생하였습니다");
    		 }
    	 });
    	 fileForm.submit();
      }
   });
});

function checkVal(sel) {  
   if($.trim($(sel).val())== ""){
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
<form action="testAB" id="backForm" method="post">
   <input type="hidden" name="searchGbn" value="${param.searchGbn}" />
   <input type="hidden" name="searchTxt" value="${param.searchTxt}" />
   <input type="hidden" name="page" value="${param.page}" />
   <input type="hidden" name="no" value="${param.no}" />
</form>
<form action="#" id="updateForm" method="post">
   번호 : ${data.B_NO}<input type="hidden" name="no" value="${data.B_NO}"><br/>
   제목  <input type="text" id="title" name="title" value="${data.B_TITLE }"/><br/>
   작성자 ${data.M_NM} <input type="hidden" id="mno" name="mno" value="${sMNo}" /><br/>
   <textarea rows="5" cols="5" id="con" name="con">${data.B_CON}</textarea>
   첨부파일 : 
   <c:choose>
	   <c:when test="${!empty data.B_FILE}">
	   <!-- 첨부파일이 있는경우 버튼을 숨긴다  -->
	   		<input type="button" value="첨부파일선택" id="fileBtn" class="hide_btn"/>
	   </c:when>
	   <c:otherwise>
	   		<input type="button" value="첨부파일선택" id="fileBtn"/>
	   </c:otherwise>
   </c:choose>
   <c:set var="len" value="${fn:length(data.B_FILE)}"></c:set>
   <span id="fileName">${fn:substring(data.B_FILE, 20, len)}</span>
   <c:if test="${!empty data.B_FILE }">
   	<input type="button" value="첨부파일삭제" id="fileDelBtn"/>
   </c:if>
   <input type="hidden" name="bFile" id="bFile" value="${data.B_FILE}" />
</form>
<input type="button" value="수정" id="updateBtn" />
<input type="button" value="취소" id="cancelBtn" />
</body>
</html>