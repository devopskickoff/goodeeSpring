<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   if("${param.searchGbn}" != ""){
      $("#searchGbn").val("${param.searchGbn}");
   }
   
   if("${page}" > "${pb.maxPcount}"){ // 데이터 변조로 인하여 페이지가 총 페이지를 넘어가는 경우 안정성을 위해 한다
	   $("#page").val("${pb.maxPcount}");
	   $("#searchForm").submit();
   }
   
   $("tbody").on("click","tr",function(){
      $("#no").val($(this).attr("no"));
      
      $("#dtlForm").submit();
   });
   
   $("#searchBtn").on("click",function(){
	  //page를 1페이지부터 나오게 하려고 하는거임 ?
	  $("#page").val("1");
      $("#searchForm").submit();
   }); // searchBtn click end
   
   $("#addBtn").on("click",function(){
      location.href = "testAdd";
   });
   
   $("#pagingWrap").on("click","span",function(){
	  $("#searchTxt").val($("#oldTxt").val());
      $("#page").val($(this).attr("page"));
      $("#searchForm").submit();
   });
   
   $("#loginBtn").on("click",function(){
	  location.href = "testLogin"; 
   });
   
   $("#logoutBtn").on("click",function(){
		  location.href = "testLogout"; 
	   });
}); // document ready end
</script>
</head>
<body>
<c:choose>
	<c:when test="${empty sMNo}">
		<input type="button" value="로그인" id="loginBtn"/>
	</c:when>
	<c:otherwise>
		${sMNm}님 어서오세요.
		<input type="button" value="로그아웃" id="logoutBtn"/>
	</c:otherwise>
</c:choose>
<form action="testDtl" id="dtlForm" method="post">
   <input type="hidden" name="no" id="no" />
   <input type="hidden" name="searchGbn" id="dtlGbn" value="${param.searchGbn}" />
   <input type="hidden" name="searchTxt" id="dtlTxt" value="${param.searchTxt}" />
   <input type="hidden" name="page" value="${param.page}" />
</form>
<form action="testList" id="searchForm" method="post">
   <input type="hidden" name="page" id="page" value="${page}" />
   <select id="searchGbn" name="searchGbn">
      <%-- <c:choose>
         <c:when test="${param.searchGbn eq 0}">
            <option value="0" selected="selected">제목</option>   
         </c:when>
         <c:otherwise>
            <option value="0">제목</option>
         </c:otherwise>
      </c:choose>
      <c:choose>
         <c:when test="${param.searchGbn eq 1}">
            <option value="1" selected="selected">작성자</option>   
         </c:when>
         <c:otherwise>
            <option value="1">작성자</option>
         </c:otherwise>
      </c:choose> --%>      
      <option value="0">제목</option>
      <option value="1">작성자</option>
   </select>
   <input type="text" name="searchTxt" id="searchTxt" value="${param.searchTxt}" />
   <input type="hidden" name="oldTxt" id="oldTxt" value="${param.searchTxt}" />
   <input type="button" value="검색" id="searchBtn" />
   <input type="button" value="작성" id="addBtn" />   
</form>
<table>
   <thead>
      <tr>
         <th>번호</th>
         <th>제목</th>
         <th>작성자</th>
         <th>작성일</th>
      </tr>
   </thead>
   <tbody>
      <c:forEach var="data" items="${list}">
         <tr no="${data.NO}">
            <td>${data.NO}</td>
            <td>${data.TITLE}</td>
            <td>${data.WRITER}</td>
            <td>${data.DT}</td>
         </tr>
      </c:forEach>      
   </tbody>
</table>
<div id="pagingWrap">
   <span page="1" >처음</span>
   <c:choose>
      <c:when test="${page eq 1}">
         <span page="1" >이전</span>
      </c:when>
      <c:otherwise>
         <span page="${page - 1}" >이전</span>
      </c:otherwise>
   </c:choose>   
   <c:forEach var="i" begin="${pb.startPcount}" end="${pb.endPcount}" step="1">
      <c:choose>
         <c:when test="${page eq i}">
            <span page="${i}" ><b>${i}</b></span>
         </c:when>
         <c:otherwise>
            <span page="${i}" >${i}</span>
         </c:otherwise>
      </c:choose>      
   </c:forEach>
   <c:choose>
      <c:when test="${page eq pb.maxPcount}">
         <span page="${pb.maxPcount}" >다음</span>
      </c:when>
      <c:otherwise>
         <span page="${page +1}">다음</span>
      </c:otherwise>
   </c:choose>
   <span page="${pb.maxPcount}" >마지막</span>
</div>
</body>
</html>