<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/eventDesc.jsp</title>
</head>
<body>
	<h4>서버사이드 이벤트 처리 방법(EDD)</h4>
	<pre>

**서버사이드 이벤트 종류 : 서버를 켜고 끄고 세션을 생성하고 만료되고 요청하고 새로고침을 하는 모든 것
lifecycle event(init, destroy): request(response), session, application 
attribute event(add, remove, change): request, session, application (scope를 변경함) 

언어별로 단계만 다를 뿐 다 같다.

1. 이벤트(event)의 대상(target) 식별 : button, application
2. 처리할 이벤트 식별 : click
3.  처리할 내용을 가진 핸들러 구현 : function, listener
4.  이벤트 대상에게 핸들러를 부착 : onclick,  web.xml(listener)

</pre>

	<button type="button" id="clickBtn">클릭</button>
	<script type="text/javascript">
		let button = document.getElementById("clickBtn");//1
		function clickHandler() {//2
			alert("클릭");//3
		}
		button.onclick = clickHandler; //4
	</script>


</body>
</html>