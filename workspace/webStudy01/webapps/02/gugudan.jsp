<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
tr, td {
	border: 1px solid black;
}

table {
	border-collapse: collapse;
}

.red {
	background-color: red;
}

.green {
	background-color: green;
}
</style>
</head>
<body>
	<%
		String minDanStr = request.getParameter("minDan");
		String maxDanStr = request.getParameter("maxDan");

		int minDan = 2;
		int maxDan = 9;
		if (minDanStr != null && minDanStr.matches("[2-9]")) {
			minDan = Integer.parseInt(minDanStr);
		}
		if (maxDanStr != null && maxDanStr.matches("[2-9]")) {
			maxDan = Integer.parseInt(maxDanStr);
		}
	%>
	<form action="">
		<input type="number" name="minDan" placeholder="최소 단" min="2" max="9" value="<%=minDan%>">
		<select name="maxDan" required>
			<option value>최대 단</option>
			<%
				String OPTPTRN = "<option %2$s value='%1$d'>%1$d단</option>"; /* formatterclass참고 */
				StringBuffer options = new StringBuffer();
				for (int dan = 2; dan <= 9; dan++) {
					
					options.append(String.format(OPTPTRN, dan, dan==maxDan?"selected":""));
				}
				out.println(options);
			%>
		</select> <input type="submit" value="전송">
	</form>

	<!-- 2단부터 9단까지 구구단을 table 태그를 이용하여 출력 -->
	<!-- 하나의 row에 하나의 단이 출력되도록 -->
	<table>
		<%
			int rowcnt = 1;
			for (int j = minDan; j <= maxDan; j++) {
				String clz = "normal";
				if (rowcnt++ == 3) {
					clz = "red";
				}
				out.println(String.format("<tr class='%s'>", clz));
				for (int i = 1; i < 10; i++) {
					if (i == 4) {
						clz = "green";
					} else {
						clz = "normal";
					}
					out.println(gugudanText(i, j, clz));
				}
				out.println("</tr>");
			}
		%>


		<%!private String gugudanText(int i, int j, String clz) {
		return String.format("<td class='%s'>%d*%d=%d</td>", clz, j, i, j * i);
	}%>
	</table>

</body>
</html>