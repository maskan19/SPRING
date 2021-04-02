<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 1. left, right의 피연산자와 operator 이름의 연산자를 포함한 필수 파라미터 입력 -->
	<!-- 2. 연산의 종류, 4칙연산 지원 -->
	<!-- 3. 입력 데이터는 실수형. -->
	<!-- 4. 파라미터 전송 : /03/calculator(uri)의 방향으로 전송( 전송데이터는 비노출).  -->
	<!-- 5. 연산의 결과: ex)3*4 = 12와 같은 형태로 제공 -->

	<form action="<%= request.getContextPath()%>/03/calculator" method="post">
		<input type="number" name="num1"> 
		<select name="oop">
			<option selected>+</option>
			<option>-</option>
			<option>*</option>
			<option>/</option>
		</select> 
		<input type="number" name="num2"> 
		<input type="submit" 	value="=">
		<input type="number" name="result">
	</form>


</body>
</html>