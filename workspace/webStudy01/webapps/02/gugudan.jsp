<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 JSP</title>
<style type="text/css">
	th, td {
		border: 1px solid black;
	}
	table {
		border-collapse: collapse;
	}
	.red{
		background: red;
	}
	.green {
		background: blue;
		color: white;
	}
</style>
</head>
<body>
<!-- 2단부터 9단까지의 구구단을 table 태그를 이용하여 출력. -->
<!-- 하나의 row 에 하나의 단이 출력되도록 -->
<!-- 3번 줄의 background 변경 -->
<!-- cal이 4인 경우에만 background 주기 -->
<%
	// 클라이언트가 데이터를 제대로 넘겼는지, 데이터가 2-9 사이의 값인지를 먼저 구분을 해야함
	String minDanStr = request.getParameter("minDan");
	String MaxDanStr = request.getParameter("maxDan");
	
	int minDan = 2;	// 데이터가 없을 경우 초기화
	int maxDan = 9; // 위와 동일
	
	// 서버 사이드에서 데이터 검증
	if(minDanStr != null && minDanStr.matches("[2-9]")) {
		minDan = Integer.parseInt(request.getParameter("minDan"));
	}
	if(minDanStr != null && minDanStr.matches("[2-9]")) {
		maxDan = Integer.parseInt(request.getParameter("maxDan"));
	}
%>
<form>
	<input type="number" name="minDan" placeholder="최소단" min="2" max="9" value="<%=minDan%>">
	<!-- required : 필수 데이터 -->
	<select name="maxDan" required>
		<option value>최대단</option>
		<%
			String OPTPTRN = "<option %2$s value='%1$d'>%1$d단</option>";
			StringBuffer options = new StringBuffer();
			for(int dan=2; dan<=9; dan++) {
				options.append(
					String.format(OPTPTRN, dan, (dan==maxDan) ? "selected" : "")
				);
			}
			out.println(options);
		%>
	</select>
	<!-- <input type="number" name="maxDan" placeholder="최대단" min="2" max="9"> -->
	<input type="submit" value="전송">
</form>
<table>
<%
	int rowcnt = 1;
	for(int i=minDan; i<=maxDan; i++)  {
		String clz = "normal";
		if(rowcnt++ == 3) clz = "red";
		
		out.println(String.format("<tr class='%s'>", clz));
		
		for(int j=1; j<=9; j++) {
			if(j == 4) clz = "green";
			else clz = "nomal";
			
			out.println(
				gugudanText(i, j, clz)
			);
		}
	}
%>
<%!
	private String gugudanText(int i, int j, String clz) {
		return String.format("<td class='%s'>%d*%d=%d</td>", clz, i, j, (i*j));
	}
%>
<%-- <%
	for(int i=2; i<=9; i++) {
		if(i==4) {
%>
			<tr style="background: lightgrey">
<%
		}
		for(int j=1; j<=9; j++) {
%>
			<td><%=i%> * <%=j%> = <%=i*j%></td>
			<td><%=String.format("%d*%d=%d", i, j, (i*j)) %></td>
<%			
		}
%>
		</tr>
<%
	}
%> --%>
</table>
</body>
</html>