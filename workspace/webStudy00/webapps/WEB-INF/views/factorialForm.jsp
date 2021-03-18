<%@page import="kr.or.ddit.enumpkg.MimeType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h4>팩토리얼 연산 처리</h4>
<select id="mimeKind">
	<option value>dataType 선택</option>
	<%
		for(MimeType tmp : MimeType.values()){
			%>
			<option value="<%=tmp.name().toLowerCase()%>"><%=tmp.name() %></option>
			<%
		}
	%>
</select>
<!-- 동기처리 연산 수행. -->
<!-- 비동기 처리 연산 수행(JSON, HTML) -->
<form id="facForm" method="post">
	<input type="number" name="single" />
	<input type="hidden" name="factorial" value="factorial"/>
	<input type="submit" value="="/>
</form>
<div id="resultArea"></div>
<script type="text/javascript">
	$(function() {
		var functionMap = {
			json:function(resp){
				resultArea.text(resp.expression);
			},
			xml:function(resp){
				resultArea.text($(resp).find("expression").text());
			},
			html:function(resp){
				resultArea.html(resp);
			},
			plain:function(resp){
				resultArea.text(resp);
			}
		}
		var mimeKind = $("#mimeKind"); 
		var resultArea = $("#resultArea");
		var facForm = $("#facForm").on("submit", function(event) {
			var dataType = mimeKind.val();
			if(!dataType){
				return true;
			}
			event.preventDefault();
			var action = "factorial";
			var method = this.method;
			var data = $(this).serialize();
			var options = {
				url : action,
				method : method,
				data : data,
				error : function(xhr, error, msg) {
					console.log(xhr);
					console.log(error);
					console.log(msg);
				}
			};
			console.log(data);
			options.dataType = dataType;
			options.success = functionMap[options.dataType];
			$.ajax(options);
			return false;
		});
	});
</script>
</body>
</html>