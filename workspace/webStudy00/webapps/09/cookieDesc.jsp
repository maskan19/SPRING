<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>

    :Http의 stateless 특성을 보완하기 위해 최소한의 상태 정보를 저장하는 개념.
      1. session : 상태 정보를 서버사이드에서 유지.
      2. cookie : 상태 정보를 클라이언트 사이드에서 유지.
      
      **쿠키 사용 방법
   <%
		
		Cookie sampleCookie = new Cookie("sampleCookie","CookieValue");   // 1
		response.addCookie(sampleCookie);
		  
		String kr_value= URLEncoder.encode("한글쿠키","UTF-8");
		
		Cookie KoreanCookie = new Cookie("KoreanCookie",kr_value);   // 1
		response.addCookie(KoreanCookie);
		
		Cookie allDomain = new Cookie("allDomainCookie","all");   // 1
		//allDomain.setDomain(".lhk.com");// 톰캣 8.5부터는 막아놓음
		//response.addCookie(allDomain);
		
		//네번째 쿠키
		Cookie allPathCookie = new Cookie("allPathCoookie","All~Path~");
		allPathCookie.setPath("/");
		response.addCookie(allPathCookie);
		         
		//다섯번째 쿠키
		Cookie longliveCookie = new Cookie("longliveCookie","long~~~~");
		
		longliveCookie.setMaxAge(-1);//0으로 하면 사라진다
		longliveCookie.setPath(request.getContextPath());
		
		//cookname 식별자가 아니다. 
		
		response.addCookie(longliveCookie);

		%>
      
      
      1. 쿠키 생성 (서버사이드/ 클라이언트 사이드) 우리는 서버사이드에서  볼 것임
      2. 응답에 쿠키를 포함하여 전송(header)
============================================================여기까지 서버      


      클라이어트의 브라우저에서 벌어지는 일
      3. 쿠키는 브라우저가 가진 저장소에 저장됨.
      4. 저장되어 있던 쿠키가 다음번 요청에 실려 재전송(header)
      5. 요청에 포함된 쿠키를 꺼내서 상태 복원 
      <a href="CookieView.jsp">쿠키확인하기(동일경로)</a>
      <a href="../08/CookieView.jsp">쿠키확인하기(다른경로)</a>

      **쿠키 속성의 종류
      1. name(required)  : 공백금지 특수문자 금지, 자바의 변수 선언과 같다.
      2. value(required) : 특수문자를 포함하는 경우, RFC2396 규약에 따라 url encoding 방식을 사용함.
      3. domain(host)    : 쿠키의 재전송을 결정하는 조건
       - 생략시, 쿠키 생성 도메인이 기본값으로 반영됨.
       ex) .naver.com :host 에 상관없이 재전송가능....
      
      4. path : 쿠키의 만료시점
      5.maxAge(expires) : 쿠키의 만료 시점
       생략시, 쿠키 생성시의 경로가 기본값으로 반영됨.
        0 : 쿠키 삭제(쿠키의 모든 조건이 동일한 경우)
       -1 : 열려있는 브라우저가 삭제되면 그 즉시 삭제하겠다. 
   
      6.httpOnly : http 프로토콜에만 재전송
      7.secure   : https 프로토콜에만 전송


      
      
</pre>
</body>
</html>