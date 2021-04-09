<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/scopeDesc.jsp</title>
</head>
<body>
<h4>영역(Scope)</h4>
<pre>
	: 서블릿이나 JSP의 인스턴스 관리 권한은 컨테이너가 가짐
	전역변수를 통한 데이터 공유가 불가능
	웹 어플리케이션 내에서 데이터를 공유할 목적으로 운영되는 저장공간
	영역(scope-Map)을 통해 공유되는 데이터 : 속성(attribute-Entry)
	
	각 영역에 대한 참조를 소유한 기본 객체와 동일한 사용범위(생명주기)를 가짐
	
	기본객체의 생명주기와 동일하다.
	1. page(pageContext) JSP page하나로 국한 : 하나의 페이지로 사용범위가 제한
	2. request(HttpServletRequest) 요청 수 >response가 나갈 때 소멸 : 하나의 요청이 살아있는한 사용범위가 유지됨
	3. session(HttpSession) 클라이언트 수 : 한 세션동안 사용가능한 영역
	4. application (ServletContext) 싱글톤> 1개 : 어플리케이션과 생명주기가 동일


</pre>

</body>
</html>