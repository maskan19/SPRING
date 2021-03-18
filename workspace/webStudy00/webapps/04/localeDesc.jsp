<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<pre>

	: 언어나 지역이나 문화적 특성들을 캡슐화한 객체
	locale text : 언어코드_국가(지역)코드
	<%=request.getHeader("accept-language")%>
	<%=request.getLocale()%> 우선순위가 높은 첫째 locale
	
	<%
		Locale locale = request.getLocale();
		out.println(locale.getDisplayLanguage()); //한국어
		out.println(locale.getDisplayCountry()); //대한민국
	%>
	
	<%=request.getLocales()%> 전체 locale
</pre>
	<%
		String locParam = request.getParameter("loc");
		Locale selectedLocale = request.getLocale();
		if (locParam != null && !locParam.isEmpty()) {
			selectedLocale = Locale.forLanguageTag(locParam);
			
		}
		Calendar cal = Calendar.getInstance();
		//out.println(String.format("%tc", cal));
		out.print(String.format(selectedLocale, "%tc", cal));
	%>
	<%=Calendar.getInstance(Locale.US)%>


	<form action="">
		<select name="loc">
			<%
				Locale[] locales = Locale.getAvailableLocales();
				for (Locale tmp : Locale.getAvailableLocales()) {
					//out.println(tmp); //사용할 수 있는 모든 locale
					String dL = tmp.getDisplayLanguage(tmp);
					String dc = tmp.getDisplayCountry(tmp);
					if (dL.isEmpty() && dc.isEmpty())
						continue;
					String selected = tmp.equals(selectedLocale)? "selected" : "";
			%>
			<option <%=selected %> value="<%=tmp.toLanguageTag()%>"><%=String.format("%s[%s]", dL, dc)%></option>
			<%
				}
			%>
		</select>


	</form>

</body>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$("[name='loc']").on("change", function(event) {
		this.form.submit();
	})
</script>
</html>