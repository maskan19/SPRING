<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>내포(include)의 종류</h4>
<pre>

	include의 시기와 대상에 따라 분류
	
	1. 동적 include(getRequestDispatcher().include, pageContext.include)
	runtime에 실행 결과를 내포함
	
	- RequestDispatcher
	- pageContext
	- include 액션 태그를 사용
	<jsp:include page="/04/localeDesc.jsp"></jsp:include> 
	커스텀 태그 : 개발자가 필요에 의해 새로 정의한 태그
	&lt;prefix(접두어):tagname attributes/&gt;
	<%-- <%pageContext.include("/04/localeDesc.jsp"); %> --%>
	2. 정적 include
	소스가 파싱되는 중에 소스 자체를 내포함
	<%-- <%@ include file="/04/localeDesc.jsp" %> --%> //지시자 directive
	


</pre>

</body>
</html>