package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * HttpServletResponse: client로 전송될 응답과 관련된 모든 정보를 가진 객체
 * 
 * Http 응답 패키징 방식
 * 1. Response Line: 상태 코드(status code), protocol
 * 		**status code: 요청의 처리 결과를 의미하는 숫자
 * 			1) 1XX: Http: 

			2) 2XX: OK(success)
 * 			3) 3XX: 클라이언트의 추가 액션이 요구되는 상태 코드, redirect 할 때
 * 				304(Not Modified):
 * 			4) 4XX: client side Fail
 * 				400(Bad Request): 필수 파라미터가 없는 경우, 파싱 불가의 데이터를 넘긴 경우, 파라미터가 데이터의 범위를 초과한 경우
 * 				401(UnAuthorized)
 * 				403(Forbidden): 접근 제한
 * 				405(Method Not Allowed): get을 요청했으나 get method가 없는 경우. post를 요청했으나 post method가 없는 경우
 * 				404(Not found)
 * 				415(Unsupported Media Type): 
 * 			5) 5XX: server side Fail, Internal server Error
 * 				서버사이드에서 실패가 발생한 경우, 서버의 정보를 유출하지 않음
 * 
 * 2. Response Header: meta data. 데이터의 종류
 * 		Content-Type( mime), Conent-Length(length) 
 * 		response.setContentType(mime)
 * 		response.setHeader(name, value)
 * 		response.setDateHeader(name, value(long)) 
 * 		response.setIntHeader(name, value(int))
 * 			1) 캐시 제어
 * 			2) auto request(polling)
 * 			3) 흐름 제어(redirect / forward[include])
 * 
 * 
 * 
 * 3. Response Body(message body, contetn body)
 * 		response.getWriter(), response.getOutputStream()으로 기록
 *
 */
@WebServlet("/responseDesc.do")
public class HttpServletResponseDesc extends HttpServlet{

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
