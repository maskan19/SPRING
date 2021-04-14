<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.lang.reflect.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elDesc.jsp</title>
</head>
<body>
	<h4>EL(Expression Language : 표현 언어)</h4>
	<pre>
  (속성)데이터를 표현(출력)할 목적으로 정의된 스크립트 언어(실제 스크립트 언어는 아니지만(java 처럼 컴파일 됨) 사용 방법은 스크립트 언어를 닮음)



1. 네개의 영역의 속성들을 표현할 때 사용(*****).



2. 연산자 지원: 피연산자가 아닌 연산자가 기준 파싱 불가는 500에러 발생

	산술연산자 : +, -, *, /, %  ${2/3 } = 0.6666666666666666 , ${"2"+ "3"}, ${"2"+3 }   (+는 무조건 산술 연산자로 문자열을 concat하지 않는다)
	논리연산자 : &&(and), ||(or), !(not), ^(xor)
		${true and true} = ${true and "true"} = \${false or 3}
	비교연산자 : >(gt),<(lt), >=(ge), <=(le), ==(eq), !=(ne)
		${not(4 le 3) }, ${4 gt 3}
	삼항연산자 : 조건식? 참 : 거짓
      <%
  	String test = "  ";
  	pageContext.setAttribute("asdf", test);
  %>
	단항연산자 : ++, -- 할당연산자를 사용할수 없기때문에 el안에선는 쓸수없다. (단 3.0버전 이상부터 쓸수있다.)
	잠깐! 유일하게 쓰는 연산자 empty ${empty asdf }
		1) 속성의 존재 여부
		2) null 여부
		3) type check
			String : length > 0
			array : length > 0
			collection : size > 0



3. (속성) 자바 객체에 대한 접근 기능 지원
    <%
  	MemberVO member = new MemberVO("a001", "java");
  	request.setAttribute("member", member);
  %>
	\${member.mem_id } = a001
	\${member["mem_id"] } = a001 연산 배열 구조
	\${member.getTest() } = test
	\${member.test } = test



4. (속성) 집합객체에 대한 접근 기능 지원
<%
	String[] array = new String[] { "value1", "value2", "value3" };
	session.setAttribute("array", array);

	List<?> list = Arrays.asList("value10", "value20", "value30");
	application.setAttribute("sampleList", list);

	List<String> list2 = Arrays.asList("temp1", "temp2");
	request.setAttribute("sampleList", list2);//겹치게 되면 작은 영역의 값이 출력된다

	Set<String> set = new HashSet<>();
	set.add("value1");
	set.add("value2");
	pageContext.setAttribute("sampleSet", set);
	
	Map<String, Object> map = new HashMap<>();
	map.put("key1", "map1");
	map.put("key-2", new Date());
	map.put("key3", 3);
	session.setAttribute("sampleMap", map);
%>
	\${array} = [Ljava.lang.String;@223c601e
	\${array[1] } = value
	\${array[4] } = (공백)
	 
	EL은 표현이 주 목적이라 웬만해선 에러를 발생시키지 않음
	
	\${sampleList } = [value10, value20, value30]
	\${sampleList.get(0) } = value10
	\${sampleList.get(3) } = 에러(자바코드로 직역되기 때문에 에러 발생)
	\${sampleList[0] } = value10
	\${sampleList[3] } = (공백)
	
	page> request> session> application 순으로 변수를 찾고 찾자마자 반환한다.



5. EL의 기본 객체 지원
	1. scope: pageScope, requestScope, sessionScope, applicationScope
	2. parameter: param(Map &gt; String, String &lt;), paramValues(Map &gt; String, String[] &lt;)
	<a href="?param1=value1&param1=value2">파라미터 전달</a>쿼리스트링으로 겟방식으로 넘긴다.
	<%=request.getParameter("param1") %> = ${param.param1 } = ${param["param1"] } = value1
	<a href="?param10=value1&param10=value2">파라미터 전달</a>쿼리스트링으로 겟방식으로 넘긴다.
	<%=request.getParameterValues("param10") %> = ${paramValues.param10}  = ${paramValues["param10"]}
	3. header : header(Map &gt; String, String &lt;), headerValues(Map &gt; String, String[] &lt;)
		user-agent값
		<%=request.getHeader("user-agent") %> = Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36
		\${header["user-agent"] } = Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36
		\${headerValues["user-agent"][0] } = Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36
	4. cookie : cookie(Map  &lt; String, Cookie &gt;)
	<%=new CookieUtils(request).getCookie("JSESSIONID")%> = javax.servlet.http.Cookie@245a8026
	\${cookie.JSESSIONID } = javax.servlet.http.Cookie@245a8026
	\${cookie["JSESSIONID"]} = javax.servlet.http.Cookie@245a8026
	
	<%=new CookieUtils(request).getCookie("JSESSIONID").getValue()%> = 7205DC2DB62461D0D73510D62270939C
	\${cookie.JSESSIONID.value }  = 7205DC2DB62461D0D73510D62270939C
	\${cookie["JSESSIONID"]["value"]} = 7205DC2DB62461D0D73510D62270939C
	\${pageContext.session.id } = 7205DC2DB62461D0D73510D62270939C
	
	5. context parameter: initParam(Map  &lt; String, String &gt;)
	<%=application.getInitParameter("contentFolder") %> = d:/contents
	\${initParam.contentFolder } = d:/contents
	\${initParam["contentFolder"] } = d:/contents
	
	6. pageContext
	\${pageContext.request.contextPath } = /webStudy03_Validation

\${applicationScope.sampleList } = [value10, value20, value30]
\${requestScope.sampleList } = [temp1, temp2]

\${pageScope.sampleSet } = [value2, value1] pageScope의 경우는 참조해주지 않아도 속도가 같다.

<c:forEach items="${ sampleSet}" var="element" varStatus="vs">
<%-- <c:if test="${vs.count eq 1}"> --%>
<c:if test="${not vs.last}">
${element }
</c:if>
</c:forEach> = value2

\${sampleMap.get("key-2") } = Wed Apr 14 15:41:05 KST 2021
\${sampleMap.key-2 } = -2
\${sampleMap["key-2"] } = Wed Apr 14 15:41:05 KST 2021

\${sampleMap.get("key-2").getTime() } = 1618382608340
\${sampleMap["key-2"].time } = 1618382608340
\${sampleMap["key-2"]["time"] } = 1618382608340: 추천되는 방식



</pre>
</body>
</html>