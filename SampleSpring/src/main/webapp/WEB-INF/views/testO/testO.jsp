<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한줄게시판</title>
<style type="text/css">
body {
	margin: 0px;
	font-size: 0pt;
}

.login_wrap {
	text-align: right;
	padding: 10px;
}

.ob_wrap {
	width: 800px;
	margin: 0px auto;
	border: 1px solid #444444;
}

.write_area {
	height: 100px;
}

.user_info {
	display: inline-table;
	width: 100px;
	height: 100px;
	vertical-align: top;
}

.user_name {
	display: table-cell;
	width: 100px;
	height: 100px;
	vertical-align: middle;
	text-align: center;
	font-size: 12pt;
}

.write_con_wrap {
	display: inline-block;
	width: 550px;
	height: 100px;
	vertical-align: top;
}

.write_con {
	width: 540px;
	height: 90px;
	resize: none;
	margin: 2px;
}

.btn_wrap {
	display: inline-block;
	width: 150px;
	height: 100px;
	vertical-align: top;
}

.action_btn {
	width: 146px;
	height: 96px;
	margin: 2px;
}

.action_btn2 {
	width: 71px;
	height: 96px;
	margin: 2px;
}

.writer_area .action_btn2 {
	display: none;
}

.login_req_wrap {
	display: inline-table;
	width: 800px;
	height: 100px;
}

.login_req {
	display: table-cell;
	width: 800px;
	height: 100px;
	vertical-align: middle;
	text-align: center;
	font-size: 12pt;
}

.data_req_wrap {
	display: inline-table;
	width: 800px;
	height: 100px;
	border-top : 1px solid #444444;
	margin-bottom: 5px;
}

.data_req {
	display: table-cell;
	width: 800px;
	height: 100px;
	vertical-align: middle;
	text-align: center;
	font-size: 12pt;
}

.ob_data {
	border-top : 1px solid #444444;
	margin-bottom: 5px;
}

.con_info {
	display: inline-table;
	width: 550px;
	height: 100px;
	vertical-align: top;
}

.con {
	display: table-cell;
	width: 100px;
	height: 100px;
	vertical-align: middle;
	text-align: left;
	font-size: 12pt;
}

.paging_wrap {
	margin: 5px 0px;
	padding: 5px 0px;
	border-top: 1px solid #444444;
	text-align: center;
}

.paging_wrap span {
	margin: 0px 5px;
	font-size: 12pt;
	cursor: pointer;
}
</style>
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//목록에서 수정버튼 클릭했을 때
		$(".ob_list_wrap").on("click","#updateBtn",function(){
			//updateBtn > btn_wrap > ob_Data < coninfo < con
			var con = $(this).parent().parent().children(".con_info").children(".con").html();
			$(".write_con").val(con);
			
			var no = $(this).parent().attr("no");
			$("#no").val(no);
			
			$(".write_area .action_btn").hide();
			$(".write_area .action_btn2").show();
		});
		
		
		$(".ob_list_wrap").on("click","#deleteBtn",function(){						
			var no = $(this).parent().attr("no");
			$("#no").val(no);
			$("#actionForm").attr("action","deleteO");
			$("#actionForm").submit();
			
		});
		
		
		$(".write_area #cancelBtn").on("click",function(){
			$(".write_con").val("");
			$("#no").val("");
			$(".write_area .action_btn").show();
			$(".write_area .action_btn2").hide();
		});
		
		$("body").on("click","#loginBtn",function(){
			location.href = "testLogin";
		});
		
		$("#logoutBtn").on("click",function(){
			location.href = "testLogout";
		});
		
		$(".write_area").on("click","#updateBtn",function(){
			$("#actionForm").attr("action","updateO");
			$("#actionForm").submit();
		});
		
		$(".write_area").on("click","#addBtn",function(){
			alert("Test");
			$("#actionForm").attr("action","addO");
			$("#actionForm").submit();
		});
		
	});
</script>
</head>
<body>
<div class="login_wrap">

	<c:choose>
		<c:when test="${empty sMNo }">
			<!-- 비로그인 시-->
			<input type="button" value="로그인" id="loginBtn"/>
		</c:when>
		<c:otherwise>
			<!-- 로그인시 -->
			${sMNm}님 어서오십시오 <input type="button" value="로그아웃" id="logoutBtn"/>
		</c:otherwise>
	</c:choose>

</div>
<div class="ob_wrap">
	<!-- Write -->
	<div class="write_area">
	<c:choose>
		<c:when test="${ empty sMNo }">
			<!-- 비 로그인 시 -->
			<div class="login_req_wrap">
				<div class="login_req">작성 시 로그인이 필요합니다. <input type="button" value="로그인" id="loginBtn"/></div>
			</div>
		</c:when>
		<c:otherwise>
			<form action="#" id="actionForm" method="post">
			<input type="hidden" id="m_no" name="m_no" value="${sMNo}">
			<input type="hidden" id="no" name="no"/>
			<div class="user_info">
				<div class="user_name">${sMNm}</div>
			</div>
			<div class="write_con_wrap">
				<textarea class="write_con" name="con"></textarea>
			</div>
			<div class="btn_wrap">
				<input type="button" value="저장" class="action_btn" id="addBtn"/>
				<input type="button" value="수정" class="action_btn2" id="updateBtn"/>
				<input type="button" value="취소" class="action_btn2" id="cancelBtn" />
			</div>
			</form>
		</c:otherwise>
	</c:choose>
		
	</div>
	<!-- List -->
	<div class="ob_list_wrap">
	<c:choose>
		<c:when test="${fn:length(list) eq 0 }">
			<!-- 데이터 없을 시 -->
			<div class="data_req_wrap">
				<div class="data_req">데이터가 없습니다.</div>
			</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="data" items="${list}">
				<div class="ob_data">
					<div class="user_info">
						<div class="user_name">${data.M_NM}</div>
					</div>
					<div class="con_info">
						<div class="con">${data.CON}</div>
					</div>
					<div class="btn_wrap" no="${data.O_NO}">
						<c:if test="${data.M_NO eq sMNo}">
							<input type="button" value="수정" class="action_btn2" id="updateBtn"/>
							<input type="button" value="삭제" class="action_btn2" id="deleteBtn"/>
						</c:if>
					</div>
				</div>
			</c:forEach>
		 </c:otherwise>
	</c:choose>
	</div>
	<!-- Paging -->
	<div class="paging_wrap">
		<span>이전</span>
		<span><b>1</b></span>
		<span>2</span>
		<span>다음</span>
	</div>
</div>
</body>
</html>