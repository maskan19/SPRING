<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>동일경로에서 확인하는 쿠키</h4>


	<%
		Cookie[] cookies = request.getCookies();
		//배열은 인덱스를 바로 알 수 없음
		Cookie searched = null;
		if (cookies != null) {
			for (Cookie tmp : cookies) {
				String value = URLDecoder.decode(tmp.getValue(), "UTF-8");
				out.println(String.format("%s : %s \n", tmp.getName(), value));
			}
		}
	%>

</body>
</html>