<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="importURL" value="${param.importURL}"></c:set>
<c:set var="source" value="${param.source}"></c:set>

	<form action="">
		<input type="url" name="importURL" placeholder="https://www.naver.com" value=${importURL }>
		<label><input type="checkbox" name="source" value="true" ${not empty source and source ? "checked" : ""}>소스보기 
		</label> <input type="submit" value="가져오기">
	</form>

		<div>
		<c:if test="${not empty importURL}">
			<c:import url="${ importURL }" var="url"></c:import>
			
			<c:if test="${empty source }">
				<c:set var="source" value="false"></c:set>
			</c:if>
			
			<c:out value="${url }" escapeXml="${source }"></c:out>
		</c:if>

		</div>
</body>
</html>