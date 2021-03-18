package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * HttpServletResonse : client 로 전송될 응답과 관련된 모든 정보를 가진 객체.
 * 
 * Http 응답 패키징 방식
 * 1. Response Line : 데이터 처리 결과를 전달 - 상태코드(status code), protocol
 * 	 ** status code 종류 : 요청 처리 결과를 의미하는 숫자
 *   1) 1XX(Http 1.1 부터) : ing...  
 *   2) 2XX : 데이터 처리 성공(success)
 *   3) 3XX : 클라이언트의 추가 액션이 요구되는 상태 코드.
 *   	- 304(Not Modified) : 현재 연결되어있는 통로가 유지중
 *   	- 302 : 데이터의 현재위치와 요청한 위치가 변경 되었을 경우 찾는 데이터의 위치를 재 반환해주는 상태코드
 *   	- 307(Moved, location 헤더와 함께 사용)
 *   4) 4XX : 데이터 처리 실패(Fail) - client side 의 원인으로 실패
 *   	- 404(Not Found) : 페이지 경로 에러
 *   	- 400(Bad Request) : 서버에게 보내는 요청 처리를 잘못 해줬을 경우 (즉, 필요한 파라미터 데이터가 잘못 넘어가거나, 서버 측에서 설정된 데이터 값 이외를 넘겼을 경우)
 *   	- 405(Method Not Allowed) : 클라이언트가 get 방식으로 요청을 발생 시켰는데 서버측에선 get방식이 없는 경우 / 클라이언트가 post 방식으로 요청을 발생시켰는데 서버 측에는 post 방식이 없는 경우
 *   	- 415(Unsupported Media Type) : 클라이언트가 요구하는 것을 서버측이 제공을 하고 있지 않을 때 나타나는 에러
 *   	- 401(UnAuthorized) : 자원에 설정되어 있는 자원 등급에 따른 접근 권한을 띄움(허가되지 않은)
 *   	- 403(forbidden) : 외부로 노출을 시킬 수 없는 경우(접근제어)
 *   5) 5XX : 데이터 처리 실패(Fail) - server side 의 원인으로 실패
 *   	- Internal server Error로 통치는 경우 : 서버의 정보를 노출 시키지 않을려고 500으로만 뜸
 * 2. Response Header : mata data
 * 	 - Content-Type(mime), Content-Length(length)
 * 	   - response.setContentType(mime)
 * 	   - response.setHeader(name, value)
 * 	   - response.setDaeHeader(name, value(long))
 * 	   - response.setIntHeader(name, value(int))
 * 3. Response Body(message body, content body)
 * 	 - response.getWriter(), response.getOutputStream()으로 기록.
 * 
 */
@WebServlet("/respDesc.do")
public class HttpServletResponseDesc extends HttpServlet {
	
}
