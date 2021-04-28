<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<tiles:getAsString name="title"></tiles:getAsString> 
</title>

<tiles:insertAttribute name="preScript"> </tiles:insertAttribute>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>

<header class="site-header sticky-top py-1">
  <tiles:insertAttribute name="headerMenu" ></tiles:insertAttribute>
</header>

<main>
 	<tiles:insertAttribute name="content" ></tiles:insertAttribute>
</main>

<footer class="container py-5">
  <tiles:insertAttribute name="footer"></tiles:insertAttribute>
</footer>

<tiles:insertAttribute name="postScript"></tiles:insertAttribute>
</body>
</html>
