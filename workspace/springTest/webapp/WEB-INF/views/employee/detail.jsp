<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세페이지</title>
</head>
<body>

회원 관리

<div>
<div class="profile__img__div"></div>
<file></file><button type="button">업로드</button>
</div>

<div>
<form>
<table>
<tr><th>아이디</th><td><input type="text" placeholder="13글자 영문자, 숫자 조합"></td></tr>

//수정<tr><th>패스워드</th><td><input type="text" placeholder="20글자 영문자, 숫자, 특수문자 조합"></td></tr>

<tr><th>이름</th><td><input type="text" placeholder="이름을 입력하세요"></td></tr>

//수정<tr><th>권한</th><td><select><option>사용자</option></select></td></tr>

<tr><th>이메일</th><td><input type="email" placeholder="example@naver.com"></td></tr>
<tr><th>전화번호</th><td><select><option>-선택-</option></select>-<input type="number" >-<input type="number" ></td></tr>
</table>


<input type="button" value="수정"> <input type="button" value="삭제"> <input type="button" value="정지"> <input type="button" value="닫기"> 


//수정<input type="submit" value="수정하기"> <input type="reset" value="취소">

</form>

</div>




</body>
</html>