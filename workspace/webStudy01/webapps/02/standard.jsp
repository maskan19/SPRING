<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
<style>
	span {
		font-weight: bold;
	}
	.ss {
		color: red;
	}
</style>
</head>
<body>
<h4><%=new Date() %></h4>
<h4>JSP(Java Server Page)</h4>
<pre>
	: <span class="ss">동적 웹 페이지</span> 생성을 위한 서버 사이드 <span>스크립트 언어</span>로 템플릿 구조를 완성하기 위해 사용되며,
	   서블릿 스펙을 기반으로 동작함.
	   
	 JSP 구성요소
	 1. <span>정적 텍스트</span> 
	 2. <span>scriptlet(동적)</span>  "&lt;% // 자바 코드 %&gt;" - 기호
	 <%
	 	// 자바코드 : 차후 서블릿 소스가 파싱될 때 _jspService 메소드의 지역 코드화가 됨.
	 	Date today = new Date(); // 지역변수
	 	test2();
	 	// block 변수 < 지역 변수 < 전역변수 < class 변수(정적변수:static) 순으로 사용 범위가 넓다
	 	// 이 곳에서 전부 있는 변수들은 지역변수 이다.
	 	// sciprtlet 은 하나의 메소드이다.
	 %>
	 	 1) <span>지시자(directive)</span>   : &lt;%@ 지시자명 속성들.. %&gt;
	 	    - 실제 실행 로직을 완성하기 위한 구성 요소가 아닌 그 로직의 환경 설정을 나타냄
	 	    - 현재 jsp 페이지에 대한 환경 정보 meta 정보 설정에 사용.
	 	    - 현재 페이지에 직접적으로 영향을 주지 않음
	 	    a. page(기본) : page 에 대한 기본 환경 정보 설정
	 	      - isErrorPage : 에러 처리만 전문적으로 할 건지에 대해 지시
	 	         -> errorPage 로 포워딩 된 페이지로 이동
	 	      - import : 
	 	      - session : jsp 안에서도 session 을 통해 상태유지가 가능 (즉, 세션의 사용 여부)
	 	    b. include(옵션) : 정적 내포에 사용
	 	    c. taglib(옵션) : 커스텀 태그 로딩에 사용
	 	 2) <span>표현식(expression)</span>   : &lt;%= // 출력할 변수 혹은 값 %&gt;
	 	    - 사실 out.write() 라는 친구이다.
	 	 3) <span>선언부(declaration)</span>  : &lt;%! //정적 변수나 메소드의 선언 %&gt;
	 	    - scriptlet 에서 정적변수나 메소드를 생성하고 싶을 때 선언부에서 사용해줌
	 	    <%!
	 	    	public String test = "정적 데이터";
		 	    public void test2() {
		 	    	System.out.println(test);
		 	    }
	 	    %>
	 	    - 스코프 : 서로 다른 jsp 간의 데이터 공유를 사용하기 위해서
	 	 4) <span>주석(comment)</span> 
	 	    - client side comment : F12 의 소스에서 보면 보이는 주석들
	 	       : 클라이언트가 볼 수 있기 때문에 되도록 쓰지 말자
	 	    	<!-- HTML 주석 -->
	 	    	<script type="text/javascript">
	 	    		// 자바스크립트 주석
	 	    	</script>
	 	    	<style type="text/css">
	 	    		/* css 주석 */
	 	    	</style>
	 	    - server side comment : F12를 눌렀을 때 주석이 보이지 않음
	 	    <%-- jsp scriptlet 주석 --%>
	 	    <% 
	 	    	// 주석
	 	    	/* 주석 */
	 	    %>
	3. 기본객체
	4. action 태그
	5. EL(표현언어)
	6. JSTL(커스텀 태그 라이브러리)
</pre>
</body>
</html>