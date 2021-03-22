<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Welcome page</h4>
	인증을 처리하는 모든 과정은 post방식이어야한다.
	<%
		String authId = (String) session.getAttribute("authId");
		if (authId != null && !authId.isEmpty()) {
	%>
	<form name="logoutForm" method="post"
		action="<%=request.getContextPath()%>/login/logout.do"></form>
	<%=authId%>님
	<a href="#" onclick="clickHandler(event);">로그아웃</a>
	<script type="text/javascript">
		function clickHandler(event) {
			event.preventDefault();
			document.logoutForm.submit();
			return false;
		}
	</script>
	<%
		} else {
	%>
	<a href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인</a>
	<%
		}
	%>
</body>
</html>