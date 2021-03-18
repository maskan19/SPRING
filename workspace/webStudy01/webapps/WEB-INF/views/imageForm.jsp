<%@page import="java.util.Date"%>
<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(function() {
			const SRCPTRN = "%A?%N=%V"	// 상수값 (A:servlet Addr / N:name / V: value )
			//console.log($("#iamge"));
			const action = $('form')[0].action;
			$("#image").on('change', function(event) {	// 익명함수(callBack 함수)
				$('#imageArea').empty();
			
				// img 태그 생성
				console.log($('form')[0])
				var name = this.name;		
				var values = $(this).val();
				var imgs = [];
				$(values).each(function(idx,value) {
					var src = SRCPTRN.replace("%A", action)
									 .replace("%N", name)
									 .replace("%V", value)
					var img = $('<img>').attr("src", src);
					imgs.push(img);
				})
				// imageArea 에 img 태그를 innerHTML 로 삽입
				$('#imageArea').html(imgs);
			});
		})
	</script>
</head>
<body>
<h4>
<%
StringBuffer options = new StringBuffer();
String[] children = (String[]) request.getAttribute("children");

for (String child : children) {
	options.append(String.format("<option>%s</option>", child));
}
%>

</h4>
<% //상대 경로  %>
<!-- <form action='../01/image.do'> -->
<!-- 
생략된 http://localhost 경로는 window 브라우저 상에서 자동으로 해석해서 붙여줌
브라우저 F12 console.log 창에 window.location을 치면 나오는
origin 뒤의 붙은 경로를 붙여주는게 '절대 경로'임
 -->
 <h4><%=new Date() %></h4>
<form action='<%=request.getContextPath()%>/01/image.do' method="get">
<select name='image' id="image" multiple>
	<%=options %>
</select>
<input type="submit" value="전송" style="background-color: red;"/>

</form>
<div id="imageArea"></div>
<script type="text/javascript">
	/* var select = document.querySelector("#image")
	select.onchange = function(event) {
		// 해당 객체의 event.target과 form 기능의 submit 버튼을 실행
		event.target.form.submit();
	} */
	
</script>
</body>
</html>