<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="10;url=https://www.naver.com"> -->
<title>05/autoResuest.jsp</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<%
		Locale locale = request.getLocale();
		String locParam = request.getParameter("loc");
		Locale selectedLocale = request.getLocale();
	%>

	<h4>AutoRequestHeader</h4>
	1) Refresh 헤더를 이용한 방식
	<br>
	<%-- <%
response.setIntHeader("Refresh", 1); //갱신주기를 초단위로 설정
//동기요청. 응답 헤더에 있음
%> --%>

	2) html의 meta태그 이용
	<br>
	<!-- 응답 바디에 있음 -->

	3) JS의 스케쥴링 함수 이용 : setInterval, setTimeout
	<br>

	<h4>
		현재 서버의 시각 : <span id="serverTimer"></span>
	</h4>
	<h4>
		현재 클라이언트의 시각 : <span id="clientTimer"></span>
	</h4>

	현재 서버사이드 시각 :
	<%=String.format("%tc", Calendar.getInstance())%>
	<h4>
		<span id="countdown"></span>초 뒤에 네이버로 감
	</h4>



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
		</select> <select name="timeZone">
			<%
				for (String local : TimeZone.getAvailableIDs()) {
					TimeZone tz = TimeZone.getTimeZone(local);
				
			%>
			<option value="<%=local%>"><%=tz.getDisplayName(locale) %></option>
			<%
				}
			%>
		</select>
	</form>



	<!-- 매1초마다 비동기 요청을 발생시키고, 클라이언트에게 사용 언어를 입력받을 것 -->
	<!-- 클라이언트에게 시간대를 입력받을 것 -->

	<script type="text/javascript">
// 	setTimeout(function(){
// 		location.reload();
// 	}, 1000);
var span = document.querySelector("#countdown");
var locSel = $("[name='loc']");
var tzSel = $("[name='timeZone']");
var serverTimer = $("#serverTimer");
var clientTimer = $("#clientTimer");
const INITTIME = 10;
var timer = INITTIME;

setInterval(function(){
	var jobID = span.innerHTML = --timer;
	if(timer ==0 ) clearInterval(jobID);
}, 1000);

setInterval(function(){
	
	var data = {
	 		loc : locSel.val(),
	 		zone: tzSel.val() //매번 확인
 	}
	var df = new Intl.DateTimeFormat(
			new Intl.Locale(data.loc),{
				dateStyle: "long",
				timeStyle: "long"
			}
	);
	clientTimer.text(df.format(new Date()));
	
	$.ajax({
		url : "<%=request.getContextPath()%>/04/serverTime",
				//	 	method : "get",
				data : data,
				dataType : "text",
				success : function(resp) {
					serverTimer.text(resp);
				},
				error : function(xhr, error, msg) {
					console.log(xhr, error, msg);
				}

			});
		}, 1000);
	</script>
</body>
</html>