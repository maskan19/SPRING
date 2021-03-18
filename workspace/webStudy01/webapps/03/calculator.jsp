<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@page import="kr.or.ddit.enumpkg.MimeType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		var functionMap = {
				// success Function 이 됨
				// ==> success : function(res) {}
				JSON: function(resp){ 
					//console.log(resp);
					resultArea.text(resp.expression);
				},
				XML: function(resp) {
					//console.log(resp);
					resultArea.text($(resp).find("expression").text());
				},
				HTML: function(resp) {
					//console.log(resp);
					resultArea.html(resp);
				},
				PLAIN: function(resp) {
					//console.log(resp);
					resultArea.text(resp);
				}
		}
		// 딱 한 번 그 위치를 찾고 그 뒤로 찾지 않기 위해서 이런 식으로 써주면 트래버싱이 일어나지 않는다.
		var resultArea = $("#resultArea");
		var mimeKind = $("#mimeKind");
		var calForm = $("#calForm").on('submit', function(event) {
			var dataType = mimeKind.val();
			if(!dataType) {
				return true;
			}
			// 이벤트 실행 못하게 막음
			event.preventDefault();
			var action = this.action;
			var method = this.method;
			var data = $(this).serialize();	// queryString 문자열이 만들어짐
			//console.log(data) // left=1&operator=PLUS&right=2
			var options = {
				url : action,
				method : method,
				data : data,
				// mime을 결정하기 위한 상수 : dataType
				//dataType : "json",	// Accept : text:text/plain, html:[text/html], json:application/json, 
									// xml:application/xml, script:text/javascript
				
				/* success : function(resp) {
					resultArea.html(resp.result);
				}, */
				error : function(xhr, error, msg) { // xmlHttpRequest, error, massage
					console.log(xhr);
					console.log(error);
					console.log(msg);
				}
			}
			// mimeKind 는 jQuery 객체의 변수이기 때문에 val() 를 사용
			options.dataType = dataType;
			// 연산배열 구조
			options.success = functionMap[options.dataType];
			$.ajax(options)
			return false;	
		})
		
	})
</script>
</head>
<body>
<!-- 1. left, right 의 피연산자와 operator 이름의 연산자를 포함한 필수 파라미터 입력.
2. 연산의 종류, 4칙연산(+,-,*,/) 지원
3. 입력 데이터는 실수형. - 입력 데이터 실수형 제한 O
4. 파라미터 전송 : /03/calculator 의 방향으로 전송(전송 데이터는 비노출 post).  O
5. 연산의 결과 : ex) 3 * 4 = 12 와 같은 형태로 제공. -->
<!-- 동기 방식으로 요청하는 친구 -->
<select id="mimeKind">
	<option value="">dataType 선택</option>
	<%
		for(MimeType tmp : MimeType.values()) {
	%>
	<!-- value를 지정해주지 않을 경우 눈에 보이는 녀석이 value 값이 됨 -->
			<option><%=tmp.name()%></option>
	<%
		}
	%>
</select>
<form id="calForm" action="<%=request.getContextPath()%>/03/calculator" method="post">
<input type="number" name="left" min="0"/>
<!-- <select name="operator">
	<option value="PLUS">+</option>
	<option value="MINUS">-</option>
	<option value="MULTI">*</option>
	<option value="DIVI">/</option>
</select> -->
	<%
		for(OperatorType tmp : OperatorType.values()) {
	%>
			<label><input type="radio" name="operator" value="<%=tmp.name()%>"><%=tmp.getSign()%></label>
	<%
		}
	%>
<input type="number" name="right" min="0"/>
<input type="submit" value="=" />
</form>
<div id="resultArea"></div>
</body>
</html>