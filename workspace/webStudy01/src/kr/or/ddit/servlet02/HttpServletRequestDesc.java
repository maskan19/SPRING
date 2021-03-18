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
 * HttpServletRequest : 클라이언트와 요청에 대한 모든 정보를 캡슐화한 객체.
 * 
 * Http 의 요청 패키징 방식
 * 1. Request Line : URL(자원에 대한 식별자), request method(자원에 대한 행위), protocol(프로토콜의 정보)
 *   1) request method : 자원에 대한 행위 + 방식(수단) => 메소드에 종류에 따른 행위와 방식이 다름
 *   	- GET(R) : 서버상의 자원을 조회할 목적(클라이언트의 기본 설정)
 *      - POST(C) : 신규 등록
 *   ==== 서버에 따라 지원 여부가 다르다. ====
 *      - PUT/PATCH(U) : 갱신
 *      	PUT : 어떠한 데이터 전체를 갱신 할 때 사용
 *      	PATCH : 어떠한 데이터의 일부를 갱신 할 때 사용
 *      - DELETE(D) : 삭제
 *      - OPTION : preflight 요청에 사용
 *      - HEADER : body 가 없는 응답을 요청할 때 사용
 *      - TRACE(추적) : 서버를 디버깅 할 때 사용 (잘 사용하지 않고 특수한 경우에만 사용한다.)
 *   ==== 현재 메소드가 서버에 존재하는지 검색 ====
 * 2. Request Header : metaData 영역(부가적인 정보)
 *      - Accept(허용하다) : 받아 드릴 수 있는 데이터가 무엇인지를 Accept를 통해 전달
 *      - Content-Type : body 라는 영역이 있을 때 어떤 데이터를 받는지 
 *      - User-Agent : 사용자가 사용하는 기기에 대한 시스템 정보를 서버로 전달
 * 3. Reqeust Body(only POST) : 서버로 전송할 컨텐츠
 *
 */
@WebServlet("/requestDesc.do")
public class HttpServletRequestDesc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// get이든 post든 상관없이 받음
		String uri = req.getRequestURI();
		String method = req.getMethod();
		String protocol = req.getProtocol();
		System.out.printf("Request Line : %s %s %s\n", uri, method, protocol);
		
		System.out.println("============== Request Header ==============");
		// header
		Enumeration<String> names = req.getHeaderNames();
		while(names.hasMoreElements()) {
			String headerName = (String) names.nextElement();
			String headerValue = req.getHeader(headerName);
			System.out.printf("%s : %s\n", headerName, headerValue);
		}
		System.out.println("============== Request Body ==============");
//		InputStream is = req.getInputStream();
//		int byteLength = is.available();	// 현재 입력 스트링에서 읽어 올 수 있는 녀석의 크기
//		System.out.printf("body length : %d", byteLength);
		
		System.out.println("============== Request Parameter ==============");
		Map<String, String[]> paraMap = req.getParameterMap();
		// Collection View
		Set<String> paraNames = paraMap.keySet();
		Iterator<String> it = paraNames.iterator();	// iterator()를 통해 불러옴
		while (it.hasNext()) {
			String paraName = (String) it.next();
			String[] values = req.getParameterValues(paraName);
			System.out.printf("%s : %s\n",paraName, Arrays.toString(values));
		}
	}
}
