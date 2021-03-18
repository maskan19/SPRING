<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
<h4>JSP(Java Server Page)</h4>
<pre>
: 동적 웹페이지 생성을 위한 서버사이드 스크립트 언어로 템플릿 구조를 완정하기 위해 사용되며, 서블릿 스펙을 기반으로 동작함.

구성요소
1. 정적 텍스트
2. scriptlet &lt;% //자바 코드 %&gt;

<%
//자바코드 :  차후 서블릿 소스가 파싱될 떄 _jspService 메소드의 지역코드화가 됨
Date today = new Date();
test2();

%>
	1) 지시자(directive)&lt;%@ 지시자명 속성들..%&gt;
	: 현재 jsp 페이지에 대한 환경 정보 meta 정보 설정에 사용
	page(기본): page에 대한 기본 환경 정보 설정
	include(옵션): 정적 내포에 사용
	taglib(옵션): 커스텀 태그 로딩에 사용
	
	2) 표현식(expression)&lt;%= //출력할 값 또는 변수%&gt;
	3) 선언부(declaration)&lt;%! //정적 변수나 메소드의 선언 %&gt;
	<%!
	static String test = "정적데이터";
	public void test2(){
		System.out.println(test);
	}
	%>
	4) 주석(comment)
		- client side comment: html, javascript, css
		//         
		/*  */
		<!--   -->
		- server side comment: jsp, java
	<% // %>
3.기본 객체
4. action 태그
5. EL(표현언어)
6. JSL(커스텀 태그 라이브러리)
	
</pre>

</body>
</html>