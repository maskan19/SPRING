<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<h1>관리자 로그인</h1>
	<div>
	Sign in to start your session
	<form  action="${cPath }/login/loginCheck.do" method="post" >
		<table>
			<tr><td><input type='text' name="employee_id" placeholder="아이디를 입력하세요." value="${cookie.idCookie.value}"></td></tr>
			<tr><td><input type='text' name="employee_pwd" placeholder="패스워드를 입력하세요."></td></tr>
			<tr><td><input type="checkbox" name="savedId" value="" >Remember Me<input type="submit" value="로그인"></td></tr>
			<tr><td><a href="#">아이디/패스워드 찾기</a> </td></tr>
		</table>
	</form>
	
	</div>
</div>
</body>
</html>