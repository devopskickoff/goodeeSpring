<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script>
 $(document).ready(function(){
	
	 $("#searchBtn").on("click",function(){
		 searchForm.submit();
	 });
	 
	 $("tbody").on("click","tr",function(){
		$("#m_no").val($(this).attr("m_no"));
		$("#dtlForm").submit();
	 });
	 
	 $("#joinBtn").on("click",function(){
		 location.href = "mtestAdd";
	 });
	 
 });
</script>
</head>
<body>

<form action="mtestDtl" id="dtlForm" method="post">
	<input type="hidden" name="m_no" id="m_no"/>
</form>

<form action="mtestList" id="searchForm" method="post">
	<input type="text" name="searchTxt" id="searchTxt">
	<select id="searchGbn" name="searchGbn">
		<option value="0">���̵�</option>
		<option value="1">�̸�</option>
	</select>
	<input type="button" value="�˻�" id="searchBtn">
	<input type="button" value="ȸ������" id="joinBtn">
</form>

<table>
   <thead>
      <tr>

         <th>���̵�</th>
         <th>�̸�</th>
         <th>��ȭ��ȣ</th>
         <th>������</th>
      </tr>
   </thead>
   <tbody>
      <c:forEach var="data" items="${list}">
         <tr m_no="${data.M_NO}">
            <td>${data.M_ID}</td>
            <td>${data.M_NM}</td>
            <td>${data.M_PHONE}</td>
            <td>${data.M_JOIN}</td>
         </tr>
      </c:forEach>
   </tbody>

</table>

</body>
</html>