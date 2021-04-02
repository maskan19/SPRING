<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05 캐시컨트롤</title>
</head>
<body>
<h4>캐시제어</h4>
<pre>
캐시 제어를 위한 응답 헤더
1)Cache-Control(Http 1.1) : public(게이트웨이 프록시서버에 저장),  private(내부?에 남김), no-cache(no-store)
													max-age(초단위, 우선순위가 높음)
2)Pragma(Http 1.0) : public(게이트웨이 프록시서버에 저장),  private(내부?에 남김), no-cache(no-store) //버전의 차이만 있다.
3)Expires : 캐시 만료 시점(date, 우선순위가 낮음)
</pre>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setHeader("Pargmn", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<img alt="" src="<%=application.getContextPath()%>/images/cloud03.jpg">
</body>
</html>