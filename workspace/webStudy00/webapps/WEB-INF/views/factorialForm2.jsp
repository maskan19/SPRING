<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.enumpkg.MimeType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h4>팩토리얼 연산 처리</h4>

<!-- 동기처리 연산 수행. -->
<!-- 비동기 처리 연산 수행(JSON, HTML) -->

<%
Map<String, Object> target = (Map) request.getAttribute("target");
%>


<form id="facForm" method="post">
	<input type="number" name="single" value="${target.op}" />
	<input type="submit" value="="/>
	${target["result"]}
</form>
<div id="resultArea"></div>

</body>
</html>