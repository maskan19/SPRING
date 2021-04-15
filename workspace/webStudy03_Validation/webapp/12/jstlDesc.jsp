<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
<style type="text/css">
.blues{
background-color: skyblue;
}
.greens{
background-color: lightgreen;
}

</style>
</head>

<body>
<h4>JSTL(Jsp Standard Tag Library)</h4>


<select onchange="location.href='?lang='+this.value">
<c:forEach var="loc" items="${Locale.getAvailableLocales() }">
<option value="${loc.toLanguageTag() }">${loc.displayLanguage }[${loc.displayCountry }]</option>
</c:forEach>
</select>


<pre>
: 커스텀 태그 라이브러리(server side): 개발자가 편의를 위해 만듬. 자바 코드로 변환됨.
<prefix:tagname arrtibutes/>
1. core
	1) EL변수(속성) 지원: set, remove
	<c:set var="name" value="값" scope="request"/>
	scope=""의 디폴트 값은 page(pageScope)
	\${requestScope.name } =  \${requestScope["name"] } = 값
	<c:remove var="name" scope="request"/>
	\${requestScope.name } =  \${requestScope["name"] } = (빈칸)
	2) 흐름제어:
		- 조건문: if, choose-when-otherwise
		<c:if test="${true }">
			무조건 실행
		</c:if>
		<c:choose>
			<c:when test="${empty name }">name 없음</c:when>
			<c:otherwise>name 있음</c:otherwise>
		</c:choose>
		${empty name ? "name 없음" : "name 있음" } = name 없음
		- 반복문: forEach, forTokens
		for(blockl variable(var) : collection(items))
		for(선언절(var = begin); 조건절(end); 증감절(step))
		LoopTagStatus  property : index(변수의 현재 값), count(변수가 호출된 횟수), first(처음 호출인지 boolean), last(마지막 호출인지 boolean)
		<c:forEach var="i" begin="1" end="3" step="1">${i }</c:forEach>
		= for(int i =1; i<=3; i+1)와 동일
		
		<c:forEach var="i" begin="1" end="5" step="2" varStatus="vs">${i } - ${vs.count }번째 반복,  ${vs.first },  ${vs.last}</c:forEach>
		step은 0이상이어야하며 1인 경우는 생략 가능하다.
		
2-9단까지 구구단 출력



<table>
	<c:forEach var="dan" begin="2" end="9" step="2"  varStatus="danvs">
		<tr class="${danvs.count eq 3 ? 'greens' :  'normal'}">
			<c:forEach var="mul" begin="1" end="9"  varStatus="mulvs">
			<c:choose>
			<c:when test="${mulvs.first or mulvs.last }">
				<c:set var="clz" value="blues"></c:set>
			</c:when>
			<c:otherwise>
				<c:remove var="clz"/>
			</c:otherwise>
			</c:choose>
				<td class="${clz }">${dan } * ${mul } = ${dan*mul }</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

<table>
	<c:forEach var="a" begin="2" end="9" varStatus="avs" step="2">
		<tr>
			<c:forEach var="b" begin="1" end="9" varStatus="bvs" >
				<c:choose>
					<c:when test="${bvs.first or bvs.last}">
						<c:set var="color" value="blues"></c:set>
					</c:when>
					<c:when test="${avs.count eq 3 }">
						<c:set var="color" value="greens"></c:set>
					</c:when>
					<c:otherwise>
						<c:remove var="color"/>
					</c:otherwise>
				</c:choose>
				<td class="${color }">${a } * ${b } = ${a*b }</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

		
		token: 문장의 구성요소 중 의미를 부여할 수 있는 최소 단위
		inti=4;
		int i =4; 토큰을 구분할 수 있는 구분자(delimeter)를 스페이스로 사용
		<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
			${token }
		</c:forTokens>
		<c:forTokens items="100,200,300" delims="," var="number">
			${number*3}
		</c:forTokens>
		
	3) URL재처리 : url 클라이언트 사이드에서 경로를 만들어줌
	쿠키가 지원되지 않는 환경에서도 자동으로 발급
	<c:url var="memberList" value="/member/memberList.do">
		<c:param name="page" value="2"></c:param>
	</c:url>
	<a href="${memberList }">회원목록의 2페이지</a>

	4) 기타 : import, out
<%-- 	<c:import url="https://www.naver.com" var="naver"></c:import> naver 변수에 사이트를 저장  --%>
<%-- 	<c:out value="${naver }"  escapeXml="false"></c:out> 네이버의 소스를 보여줌 escapeXml="true"가 디폴트 --%>
	
	
	
2. format
	locale지원
	1) 언어처리: setLocale, message
		- 언어 결정(한글, 영문)
		- 메시지 번들 작성(properties)
		- locale 결정
		<c:if test="${empty param.lang }">
		<c:set var="locale" value="${pageContext.request.locale }"></c:set>
		</c:if>
		<c:if test="${not empty param.lang }">
			<c:set var="locale" value="${param.lang }"></c:set>
		</c:if>
<%-- 		<fmt:setLocale value="ko"/> --%>
		<fmt:setLocale value="${locale }"/> 찾을 수 없는 경우 기본값(_text가 없는 프로퍼티)을 사용
		- 취합해서 메시지 출력: 번들로딩, 메시지 사용
		<fmt:bundle basename="kr.or.ddit.messages.message">
			<fmt:message key="bow"></fmt:message>
		</fmt:bundle>
	2) 메시지 형식 처리
		- parsing: parseNumber, parseDate
		- formatting: formatNumber, formatDate
<%-- 		<fmt:setLocale value="en_US"/>언어와 지역 정보를 모두 포함하고 있어야한다. --%>
		<fmt:formatNumber value="30000" type="currency"   var="money"></fmt:formatNumber> money에 저장
		${money }
		<fmt:parseNumber value="${money }" type="currency" var="number"></fmt:parseNumber>
		${number *20 } = 600000
		<fmt:formatDate value="<%=new Date() %>" type="both" dateStyle="short" var="dateStr" />
		${dateStr} = 21. 4. 15 오후 1:47:02
		<fmt:parseDate value="${dateStr }" type="both" dateStyle="short" var="dateObj"  ></fmt:parseDate>
		${dateObj.time } = 1618462022000(밀리세컨드)
		${dateObj} = Thu Apr 15 13:47:02 KST 2021
		
		
3. function
${fn:indexOf("abcd","c") } = 2
<c:set var="array1" value='<%=new String[]{"value1", "value2", "value3", "value4"} %>'></c:set>
${fn:join(array1, " and ") } = value1 and value2 and value3 and value4 
토근의 반대. 
<c:set var="array2" value='${fn:split("test1,test2",",") }'></c:set>
${fn:join(array2, " , ") } = test1 , test2 




</pre>

</body>
</html>