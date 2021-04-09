<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flow Control.jsp</title>
</head>
<body>
<h4>웹 어플리케이션에서 흐름 제어 방법(페이지 이동)</h4>
<pre>
1. 서버 내에서의 위임 구조, RequestDispatcher(요청 분기 제어 관리자) 사용
	- 원본요청(request)을 도착지점에서 사용할 수 있는 구조.
	1) forward
	2) include
	<%
// 		String dest ="/04/localeDesc.jsp"; //서버가 사용할 정보이므로 ContextPath는 필요하지 않다.
// 		RequestDispatcher rd = request.getRequestDispatcher(dest);
// 		rd.include(request, response);
	%>
2. Redirect: Body가 없이, 상태코드(302)로 구성된 line과 Location 헤더를 가지고 응답이 전송됨.
	HTTP의 Stateless특성에 따라 원본 요청에 대한 정보는 사라진다.
		(서블릿의 특성 : stateless(상태(정보)를 유지하지 않음), connectless(연결 지양))
	<%
		String location = request.getContextPath() + "/04/localeDesc.jsp"; //클라이언트가 사용할 정보이다.
		response.sendRedirect(location);
		
	%>
</pre>

</body>
</html>