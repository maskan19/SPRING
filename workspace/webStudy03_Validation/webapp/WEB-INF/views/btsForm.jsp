<%@page import="java.util.List"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- <%
	pageContext.include("/includee/preScript.jsp");
%> --%>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
	action이 비어있으면 브라우저가 가지고 있는 주소를 사용
	<form method="post">
		<input type="text" name="test" />
		<select id="bts" name="member"
			onchange="$(this.form).trigger('submit')">
			<%
				Map<String, List<String>> btsMap = (Map) application.getAttribute("btsMap");

				for (Entry<String, List<String>> entry : btsMap.entrySet()) {
					String id = entry.getKey();
					String name = entry.getValue().get(0);
			%>
			<option value="<%=id%>"><%=name%></option>
			<%
				}
			%>
		</select>
	</form>
	<div id="resultArea"></div>
	<script type="text/javascript">
// 		console.log($("form").test2());
		let resultArea = $("#resultArea")
		$("form").formToAjax({
			dataType:"html",
			success: function(resp){
				resultArea.html(resp);
			}
		});
		
	</script>

</body>
</html>