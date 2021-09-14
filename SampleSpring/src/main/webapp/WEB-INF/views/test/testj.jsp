<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript"
	src="resources/script/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
var num = 1;

$(document).ready(function(){
	//html을 자바스크립트에서 어떻게 만들거냐 
	$("#add1Btn").on("click",function(){
		num++;
		var html = "";
		html += "<div>"+ num +"<input type=\"button\" value=\"삭제\" class=\"delBtn\" /> </div>";
		
		//append : 엔티티 뒷부분에 해당 내용을 추가한다
		
 		$(".wrap").append(html);
		
 		//기존 이벤트를 제거
/*		
		$(".delBtn").off("click");
		$(".delBtn").on("click",function(){
			$(this).parent().remove();
		}); */
	});
	
	$("#add2Btn").on("click",function(){
		num++;
		var html = "";
		html += "<div>" + num + "<input type=\"button\" value=\"삭제\" class=\"delBtn\"/></div>"
		
		//prepend : 엔티티 앞부분에 해당 내용을 추가한다
		$(".wrap").prepend(html);
	});
	
	$("#changeBtn").on("click",function(){
		num++;
		var html = "";
		html += "<div>" + num + "</div>";
		
		//html : 엔티티를 가져오거나 교체를 한다
		//html() : 엔티티를 가져옴
		//html(값) : 엔티티를 값으로 교체
		$(".wrap").html(html);
		console.log($(".wrap").html());
	});
	
	$(".wrap").on("click",".delBtn",function(){
		$(this).parent().remove();
	});
	
	$("#resetBtn").on("click",function(){
		num = 0;
		//empty :
		$(".wrap").empty();
	});
});
</script>
</head>
<body>
<input type="button" value="추가1" id="add1Btn"/>
<input type="button" value="추가2" id="add2Btn"/>
<input type="button" value="교체" id="changeBtn"/>
<input type="button" value="초기화" id="resetBtn"/>
<div class="wrap">
	<div>1 <input type="button" value="삭제" class="delBtn"/> </div>
</div>
</body>
</html>