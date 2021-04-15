<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.ddit.or.kr"  prefix="my"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
<c:when test="${not empty param.zone }">
<c:set var="tz" value="${my:getTimeZone(param.zone) }"></c:set>
</c:when>
<c:otherwise>
	<c:set var="tz" value="${my:getDefaultTimeZone() }"></c:set>
</c:otherwise>
</c:choose>

<form action="">
1. java의 모든 timezone을 select로 선택 가능하도록
 <br/>
<select name="zone">
<c:set var="timezone" value="${my:getAvailableIDs() }"/>timezone = String[]
    <c:forEach var="t" items="${timezone }">
      <option value="${t}">  ${my:getTimeZone(t).displayName }      
<%--       <%=TimeZone.getTimeZone(pageContext.getAttribute("timezone").toString()).getDisplayName() %> --%>
    </c:forEach>
</select>
 <br/> <br/> <br/> <br/>

2. 선택한 시간대에 맞추어 시간 출력
 <br/>
 <c:set var="now" value="${my:getNow() }"></c:set>
 <fmt:setLocale value="${not empty param.locale ? param.loc : pageContext.request.locale }"/>
<fmt:formatDate value="${now }" type="both" dateStyle="long"  timeZone="${tz }"/>
 <br/>
 <br/>
 
 <br/> <br/> <br/> <br/>

3. java의 모든 locale을 select로 선택 가능하도록
 <br/>
<c:set var="locale" value="<%=Locale.getAvailableLocales() %>"></c:set>locale = Locale[]
<select name="locale">
	<c:forEach var="loc" items="${locale }">
		<option value="${loc.toLanguageTag() }">${loc.displayLanguage }[${loc.displayCountry }]</option>
	</c:forEach>
</select>
 <br/> <br/> <br/> <br/>

4. 선택한 locale에 맞춰서 출력 메시지 형식 결정
<br/>
<%-- <fmt:parseDate value="${now }" type="both" dateStyle="long" var="dateObj"  ></fmt:parseDate> --%>
<%-- 	${dateObj} --%>
</form>


<script type="text/javascript">
	
	function changeHandler(e){
		let select = e.target //selectbox = this
		select.form.submit();
	}

	let selects = document.getElementsByTagName("select");
	for(let i = 0; i<selects.length; i++){
		selects[i].onchange = changeHandler;
	}
	
	document.forms[0].loc.value="${param.locale}";
	document.forms[0].timezone.value="${param.zone}";

</script>

</body>
</html>