<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
table {
	border: 1px solid black;
	text-align: center;
	border-collapse: collapse;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<h4>회원 목록 조회</h4>
	<table>
		<thead>
			<tr>
				<th>회원 아이디</th>
				<th>회원명</th>
				<th>이메일</th>
				<th>휴대폰</th>
				<th>마일리지</th>
				<th>탈퇴여부</th>
			</tr>
		</thead>

		<%
			List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");
			if (memberList != null && memberList.size() > 0) {
		%>
		<tbody>


			<%
				for (MemberVO member : memberList) {
			%>
			<tr>
				<td><%=member.getMem_id()%></td>
				<td><%=member.getMem_name()%></td>
				<td><%=member.getMem_mail()%></td>
				<td><%=member.getMem_hp()%></td>
				<td><%=member.getMem_mileage()%></td>
				<td><%="Y".equals(member.getMem_delete())?"탈퇴" : ""%></td>
			</tr>
			<%
				}
			%>

		</tbody>
		<%
			} else{
				%>
				<td colspan="5">등록된 회원이 없음</td>
				<%
			}
		%>

	</table>
</body>
</html>