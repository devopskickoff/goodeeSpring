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
      $("#searchGbn").val("${param.searchGbn}")
   }
   
   $("tbody").on("click","tr",function(){
      $("#no").val($(this).attr("no"));
      
      $("#dtlForm").submit();
   });
   
   $("#searchBtn").on("click",function(){
      $("#searchForm").submit();
   }); // searchBtn click end
   
   $("#addBtn").on("click",function(){
      location.href = "testAdd";
   });
}); // document ready end
</script>
</head>
<body>
<form action="testDtl" id="dtlForm" method="post">
   <input type="hidden" name="no" id="no" />
   <input type="hidden" name="searchGbn" value="${param.searchGbn}" />
   <input type="hidden" name="searchTxt" value="${param.searchTxt}" />
</form>
<form action="testList" id="searchForm" method="post">
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
</body>
</html>