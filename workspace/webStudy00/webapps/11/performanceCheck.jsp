<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>성능 체크</h4>
<pre>
반응 시간(소요 시간) = processing time(프로세싱 시간) + latency time(지연시간)
PT(WS, WAS 내에서 데이터를 가공하는 데에 걸리는 시간)
LT(client와 서버 사이의 네트워킹 시간 + 서버와 DB사이의 네트워킹 시간)

<a href="oneConnOneProcess.jsp">1번 연결하고 1번 처리한 소요시간 : 7ms</a>
<a href="100Conn100Process.jsp">100번 연결하고 100번 처리한 소요시간 : 550ms</a>
<a href="oneConn100Process.jsp">1번 연결하고 100번 처리한 소요시간 : 11ms</a>

</pre>
</body>
</html>