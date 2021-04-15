<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.Constants"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Welcome Page</h4>

<%-- <%=application.getAttribute(Constants.SESSIONCOUNTATTRNAME) %> --%>
누적 방문자 수 : ${sessionCount }
<br>
접속 중인 방문자
<ul>
	<c:forEach items="${userList }" var="user"> <!-- user는 변수가 아니라 속성이다. page scope에 넣음 -->  
		<li>${user.mem_name }</li>
	</c:forEach>
</ul>

<c:choose>
<c:when test="${not empty authMember }">
<form name="logoutForm" method="post" action="${cPath }/login/logout.do"></form>
		<img alt="" src="${authMember.mem_img}">
		<a href="${cPath}/mypage.do">${authMember.mem_name}</a>님 [${authMember.mem_role}]
		<a href="#" onclick="clickHandler(event);">로그아웃</a>
		<script type="text/javascript">
			function clickHandler(event){
				event.preventDefault();
				document.logoutForm.submit();
				return false;
			}
		</script>
</c:when>
<c:otherwise>
<a href="${cPath }/login/loginForm.jsp">로그인</a>
<a href="${cPath }/member/memberInsert.do">회원가입</a>

</c:otherwise>
</c:choose>


<pre>

EL은 네개의 스코프 중 하나에 들어있어야한다.EL의 단점을 해결하기 위해 JSTL사용.
JSTL은 커스텀 태그 > library 형태로 사용하기 위해 로딩이 필요함
인증을 처리하는 모든 과정은 post방식이어야한다.
</pre>
</body>
</html>
