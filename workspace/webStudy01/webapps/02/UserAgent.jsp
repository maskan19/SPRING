<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/userAgent.jsp</title>
</head>
<body>
<h4>User Agent</h4>
<!-- 
1. 클라이언트의 브라우저를 확인
2. if (explore) : "당신의 브라우저는 익스플로러 입니다."
3. else if(chrome) : "당신의 브라우저는 크롬입니다."
4. else if(edge) : "당신의 브라우저는 엣지입니다."
5. 라는 결과를 alert 창으로 띄움 
-->

<%	
	/* Map<String, String> browserMap = new LinkedHashMap<>();	// 순서가 있는 Map 객체
	browserMap.put("EDG", "앳지");
	browserMap.put("CHROME", "크롬");
	browserMap.put("TRIDENT", "익스플로러");
	browserMap.put("OTHER", "기타등등"); */
	//BrowserType[] types = BrowserType.values();
	
	String agent = request.getHeader("user-agent");
	System.out.println("agent : " + agent);
	//String MSGPTRN = "당신의 브라우저는 %s 입니다.";
	String MSGPTRN = "당신의 OS는 %s 입니다.";
	
	String message = null;
	/* String name = browserMap.get("OTHER");
	for(Entry<String, String> entry : browserMap.entrySet()) {
		String keyWord = entry.getKey();
		if(agent.contains(keyWord)) {
			name = entry.getValue();
			break;
		}
	} */
	//message = String.format(MSGPTRN, BrowserType.getBrowserName(agent));
	message = String.format(MSGPTRN, BrowserType.getBrowserName(agent));
%>
<script type="text/javascript">
	alert("<%=message%>");
</script>
</body>
</html>