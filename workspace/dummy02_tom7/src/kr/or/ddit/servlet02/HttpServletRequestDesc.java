package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * HttpServletRequest : 클라이언트와 요청에 대한 정보를 캡슐화한 객체
 * 
 * Http의 요청 패키징 방식 1. Request Line : URL, request method, protocol 1)request
 * method : 행위 + 방식(수단)에 대한 정보 - GET(Read) : 서버 자원을 조회할 목적. 클라이언트의 기본 설정 -
 * POST(Create) : 신규 등록 - PUT/PATCH(Update) : 갱신 - DELETE(Delete) : 삭제 - OPTION
 * : preflight 요청에 사용 - HEADER : body가 없는 응답을 요청할 때 사용 - TRACE : 서버 디버깅 요청에 사용
 * 
 * preflight 요청을 발생시켜 요청을 지원하는지 먼저 확인
 * 
 * 2. Request Header : meta data 영역 Accept, Content-Type, User-Agent 3. Request
 * Body(only POST : 서버로 전송할 컨텐츠
 * 
 *
 *
 */
@WebServlet("/requestDesc.do")
public class HttpServletRequestDesc extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// template을 변경하는 메소드

		// Line
		String uri = req.getRequestURI();
		String method = req.getMethod();
		String protocol = req.getProtocol();
		System.out.printf("Request  Line : %s %s %s", uri, method, protocol);

		// Header
		System.out.println("=============Request Header=================");
		Enumeration<String> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String headerName = (String) names.nextElement();
			String headerValue = req.getHeader(headerName);
			System.out.printf("%s : %s\n", headerName, headerValue);
		}

		// Body
		System.out.println("=============Request Body=================");
//		InputStream is = req.getInputStream();
//		int byteLength = is.available();
//		System.out.printf("body length : %d", byteLength);

		req.setCharacterEncoding("utf-8");

		//RFC2397 규약 - 특수문자를 percent encoding(URL encoding)방식으로 인코딩됨
		System.out.println("=================== Request Parameter=============");
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<String> parameterNames = parameterMap.keySet();
		Iterator<String> it = parameterNames.iterator();
		while (it.hasNext()) {
			String parameterName = (String) it.next();
			req.getParameterValues(parameterName);
			String[] values = req.getParameterValues(parameterName);
			System.out.printf("%s : %s\n", parameterName, Arrays.toString(values));
		}

	}
}
