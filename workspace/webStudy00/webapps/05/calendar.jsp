<%@page import="java.util.Locale"%>
<%@page import="java.util.TimeZone"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>




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
			%>
			<option value="<%=tmp.toLanguageTag()%>"><%=String.format("%s[%s]", dL, dc)%></option>
			<%
				}
			%>
		</select> <select name="timezone">
			<%
				for (String local : TimeZone.getAvailableIDs()) {
					TimeZone tz = TimeZone.getTimeZone(local);
					if (tz != null)
						continue;
			%>
			<option value="<%=local%>"><%=tz.getDisplayName(locales)%></option>
			<%
				}
			%>
		</select>
	</form>









</body>
</html>