<%@page import="kr.or.ddit.enumpkg.OsType"%>
<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
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
<!-- 클라이언트의 브라우저를 확인하고,
iexplore 라면 "당신의 브라우저는 익스플로러입니다"
chrome 라면 "당신의 브라우저는 크롬입니다"
edge 라면 "당신의 브라우저는 엣지입니다"
라는 메시지를 alert 창으로 띄울것. -->
<%
// Map<String,String> browserMap = new LinkedHashMap<>();
// browserMap.put("EDG", "엣지");
// browserMap.put("CHROME", "크롬");
// browserMap.put("TRIDENT", "익스플로러");
// browserMap.put("OTHER", "기타");
String agent = request.getHeader("user-agent");
System.out.println(agent);

String MSGPTRN = "당신의 브라우저는 %s입니다.";
String MSGPTRNOS = "당신의 OS는 %s입니다.";
String message = null;
String messageos = null;
// String name = browserMap.get("OTHER");
// for(Entry<String,String> entry : browserMap.entrySet()){
// 	String keyWord = entry.getKey();
// 	if(agent.contains(keyWord)){
// 		name = entry.getValue();
// 		break;
// 	}
// }

message = String.format(MSGPTRN, BrowserType.getBrowserName(agent));
messageos = String.format(MSGPTRNOS, OsType.getOsName(agent));
%>
<script type="text/javascript">
	alert("<%=message%>");
	alert("<%=messageos%>");
</script>
</body>
</html>