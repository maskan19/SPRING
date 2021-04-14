<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:if test="${not empty sessionScope.message }">
<script type="text/javascript">
		alert("${message }");
	</script>
	<c:remove var="message" scope="session"/> 
<!-- 	flashAttribute 방식 : 꺼내자마자 바로 지워버리는 방식 -->
</c:if>
</head>
<body>
	<form method="post">
		<!-- 노출하면 안됨 -->
		<input type="text" name="mem_pass" required> 
		<input type="submit" value="인증">
	</form>

</body>
</html>