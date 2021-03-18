<%@page import="kr.or.ddit.enumpkg.MimeType"%>
<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		var functionMap = {
				json : function(resp){
					console.log(resp);
					resultArea.text(resp.expression);
				},
				xml : function(resp){
					console.log(resp);
					resultArea.text($(resp).find("expression").text())
				},
				html : function(resp){
					console.log(resp);
					resultArea.html(resp);
				},
				plain : function(resp){
					console.log(resp);
					resultArea.text(resp);
				},
		}
		var mimeKind = $("#mimeKind")
		var resultArea = $("#resultArea")
		var claForm = $('#calForm').on("submit", function(event) {
			var dataType =  mimeKind.val();
			if(!dataType){
				return true;
			}
			event.preventDefault();//동기 요청 취소
			var action = this.action;
			var method = this.method;
			var data = $(this).serialize();//querystring
			var options = {
				url : action,
				method : method,
				data : data,
//				dataType : "script",//Accept: text/plain;
				//json >> application/json, text/javascript, */*; q=0.01
				//xml >> application/xml, text/xml, */*; q=0.01
				//script >> text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01
//				success : function(resp) {
//					resultArea.html(resp.result);
//				},
				error : function(xhr, error, msg) {
					console.log(xhr, error, msg);
				}

			}
			console.log(data);
			options.dataType = dataType;
			options.success =  functionMap[options.dataType];
			$.ajax(options);
			return false; //이벤트가 발생하지 않음

		});
	})
</script>
</head>
<body>
	<!-- 1. left, right의 피연산자와 operator 이름의 연산자를 포함한 필수 파라미터 입력 -->
	<!-- 2. 연산의 종류, 4칙연산 지원 -->
	<!-- 3. 입력 데이터는 실수형. -->
	<!-- 4. 파라미터 전송 : /03/calculator(uri)의 방향으로 전송( 전송데이터는 비노출).  -->
	<!-- 5. 연산의 결과: ex)3*4 = 12와 같은 형태로 제공 -->
	<select id="mimeKind">
	<option value>dataType 선택</option>
		<%
			for (MimeType tp : MimeType.values()) {
		%>
		<option	value="<%=tp.name().toLowerCase()%>"> <%=tp.name()%></option>
		<%
			}
		%>
		<option></option>
		<option></option>
		<option></option>
	</select>
	<form id="calForm"
		action="<%=request.getContextPath()%>/03/calculator2" method="post">
		<input type="number" name="left" step=any>
		<%
			for (OperatorType op : OperatorType.values()) {
		%>
		<label><input type="radio" name="operator"
			value="<%=op.name()%>"><%=op.getSign()%></label>
		<%
			}
		%>
		<input type="number" name="right">
		<button type="submit">=</button>
	</form>
	<div id="resultArea"></div>


</body>
</html>