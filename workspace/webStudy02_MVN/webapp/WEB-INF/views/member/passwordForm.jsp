<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String message = (String) session.getAttribute("message");
	session.removeAttribute("message"); //flashAttribute 방식 : 꺼내자마자 바로 지워버리는 방식
	if (message != null && !message.isEmpty()) {
%>
<script type="text/javascript">
	
	alert("<%=message%>");
</script>
<%
	}
%>
</head>
<body>
	<form method="post">
		<!-- 노출하면 안됨 -->
		<input type="text" name="mem_pass" required> 
		<input type="submit" value="인증">
	</form>

</body>
</html>