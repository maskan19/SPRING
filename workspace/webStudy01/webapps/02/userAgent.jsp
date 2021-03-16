<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>User Agent</h4>
	<!-- 클라이언트의 브라우저를 확인하고  -->
	<!-- Internet explore라면 "당신의 브라우저는 익스플로러입니다." -->
	<!-- chrome라면 "당신의 브라우저는 크롬입니다." -->
	<!-- edge라면 "당신의 브라우저는 엣지입니다." -->
	<!-- 라는 메시지를 alert 창으로 띄울 것 -->

	<%
		// 	Map<String, String> browserMap = new LinkedHashMap<>();
		// 	browserMap.put("Trident", "익스플로러");
		// 	browserMap.put("Edg", "엣지");
		// 	browserMap.put("Safari", "사파리");
		// 	browserMap.put("Chrome", "크롬");
		BrowserType[] types = BrowserType.values();
		String MSGPTRN = "당신의 브라우저는 %s입니다.";
		String MSGPTRN2 = "당신의 OS는 %s입니다.";
		String agent = request.getHeader("user-agent");
		String message = null;

		message = String.format(MSGPTRN, BrowserType.getBrowserName(agent));

		// 		if (request.getHeader("user-agent").indexOf("Trident") > -1) {
		// 			message = String.format(MSGPTRN, "익스플로러");
		// 		} else if (request.getHeader("user-agent").indexOf("Edg") > -1) {
		// 			message = String.format(MSGPTRN, "엣지");
		// 		} else if(request.getHeader("user-agent").indexOf("Chrome")>-1){
		// 			message = String.format(MSGPTRN, "크롬");
		// 		} else
		// 			message = "식별할 수 없는 브라우저입니다.";
	%>
	<script type="text/javascript">
		alert("<%=message%>");
	</script>
</body>
</html>