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
	//html�� �ڹٽ�ũ��Ʈ���� ��� ����ų� 
	$("#add1Btn").on("click",function(){
		num++;
		var html = "";
		html += "<div>"+ num +"<input type=\"button\" value=\"����\" class=\"delBtn\" /> </div>";
		
		//append : ��ƼƼ �޺κп� �ش� ������ �߰��Ѵ�
		
 		$(".wrap").append(html);
		
 		//���� �̺�Ʈ�� ����
/*		
		$(".delBtn").off("click");
		$(".delBtn").on("click",function(){
			$(this).parent().remove();
		}); */
	});
	
	$("#add2Btn").on("click",function(){
		num++;
		var html = "";
		html += "<div>" + num + "<input type=\"button\" value=\"����\" class=\"delBtn\"/></div>"
		
		//prepend : ��ƼƼ �պκп� �ش� ������ �߰��Ѵ�
		$(".wrap").prepend(html);
	});
	
	$("#changeBtn").on("click",function(){
		num++;
		var html = "";
		html += "<div>" + num + "</div>";
		
		//html : ��ƼƼ�� �������ų� ��ü�� �Ѵ�
		//html() : ��ƼƼ�� ������
		//html(��) : ��ƼƼ�� ������ ��ü
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
<input type="button" value="�߰�1" id="add1Btn"/>
<input type="button" value="�߰�2" id="add2Btn"/>
<input type="button" value="��ü" id="changeBtn"/>
<input type="button" value="�ʱ�ȭ" id="resetBtn"/>
<div class="wrap">
	<div>1 <input type="button" value="����" class="delBtn"/> </div>
</div>
</body>
</html>