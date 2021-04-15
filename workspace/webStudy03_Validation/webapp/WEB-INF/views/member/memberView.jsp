<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 보기</title>
</head>
<body>
<table>
<tr><th>회원메일</th><td>${member.mem_mail }</td></tr>
<tr><th>회원이름</th><td>${member.mem_name }</td></tr>
<tr><th>회원비밀번호</th><td>${member.mem_pass }</td></tr>
<tr><th>회원아이디</th><td>${member.mem_id }</td></tr>
<tr><th>회원집주소2</th><td>${member.mem_add2 }</td></tr>
<tr><th>회원집주소1</th><td>${member.mem_add1 }</td></tr>
<tr><th>우편번호</th><td>${member.mem_zip }</td></tr>
<tr><th>회원취미</th><td>${member.mem_like }</td></tr>
<tr><th>회원</th><td>${member.mem_memorial }</td></tr>
<tr><th>회원기념일</th><td>${member.mem_memorialday }</td></tr>
<tr><th>회원마일리지</th><td>${member.mem_mileage }</td></tr>
<tr><th>회원삭제</th><td>${member.mem_delete }</td></tr>
<tr><th>회원등급</th><td>${member.mem_role }</td></tr>
<tr><th>회원직업</th><td>${member.mem_job }</td></tr>
<tr><th>회원전화번호</th><td>${member.mem_hp }</td></tr>
<tr><th>프로필 아이콘</th><td><img alt="" src="data:image/*;base64,${member.base64Image}"></td></tr>
<tr><th>회원집번호</th><td>${member.mem_hometel }</td></tr>
<tr><th>생일</th><td>${member.mem_bir }</td></tr>
<tr><th>주민번호2</th><td>${member.mem_regno2 }</td></tr>
<tr><th>주민번호1</th><td>${member.mem_regno1 }</td></tr>
<tr><th>회원회사번호</th><td>${member.mem_comtel }</td></tr>
</table>
</body>
</html>