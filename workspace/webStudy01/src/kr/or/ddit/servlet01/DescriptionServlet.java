package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet : 자바를 기반으로 웹어플리케이션을 구현하기 위한 명세 혹은 그 명세에 따른 AIP 모음.
 * 개발 단계
 * 1. HttpServlet 의 구현체로 서블릿 소스 작성.
 * 2. WEB-INF/classes(context classpath)에 컴파일의 후 클래스 파일 배포 (컴파일 단계)
 * 3. 컨테이너에 서블릿을 등록(web.xml)
 * 		1) 2.x : web.xml 을 이용
 * 				servlet -> servlet-name, servlet-class
 * 		2) since 3.xc : CoC(Convention over Configuration): 명시적으로 이름을 적지 않으면 클래스명으로 자동으로 지정됨
 * 4. 서블릿 매핑으로 웹 상의 명령(url/uri)을 받을 수 있도록 연결.
 * 		1) 2.x : web.xml 에 등록
 * 				servlet-mapping -> servlet-name, url-pattern
 * 		2) 3.x : @WebServlet(value, urlPatterns)
 * 5. 컨테이너 재구동 (서버 재시작)
 * 
 * ** Servlet Container 의 역할 : servlet 의 lifecycle 관리자
 * 	  Container : 컨테이너 내부에서 관리되는 컴포넌트의 생명주기 관리자
 *    
 *    3가지 이벤트
 *    생성 : init
 *    요청 : service, doXXX
 *    소멸 : destory
 *    
 *    서블릿 관리 정책
 *    1. 특별한 설정(loadOnStartup)이 없는 한 해당 서블릿에 매핑된 최초의 요청이 발생하면, 인스턴스 생성 : (서블릿 객체가 생성되는 시점을 바꿀수 도 있다)
 *    2. 서블릿 초기화 단계에서 초기화 파라미터 전달(init-param)
 *     ** ServletConfig : 서블릿의 메타 정보를 캡슐화한 객체
 */
// loadOnStartup=1 : 서버를 시작함과 동시에 servlet 객체 생성이 가능하다
//어노테이션 중 value 는 생략이 가능 (urlPatterns은 value 와 동일하다)
//@WebServlet(value="/desc.do", loadOnStartup=1, 
//			initParams= {@WebInitParam(name="paramName", value="paramValue")})	
public class DescriptionServlet extends HttpServlet {
	// 생성
	@Override
	public void init(ServletConfig config) throws ServletException {	// 싱글톤으로 돌기 때문에 처음 실행 할 때만 사용한다.
		super.init(config);
		String param = config.getInitParameter("paramName");
		System.out.printf("%s 서블릿 초기화, 전달 파라미터 : %s \n", 
							this.getClass().getName(), param);	// 현재 클래스의 콸러 파일경로
	}
	
	// 작업의 순서는 동일하나 세세한 작업은 다르다 . 템플릿 메소드 패턴
	// 확장 CUI 형식
	@Override // template method : 작업의 순서가 제어
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("요청 접수, 메소드 판단");
		//super.service(req, resp);
		// servie 메소드는 doXXX 친구들을 호출해주는 역할을 함
	}
	@Override // hook method : 세세한 작업의 처리해주는 메소드
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// CGI 형식과 확장 CGI 형식의 차이점
		
		System.out.printf("특정 메소드(GET) 을 처리할 수 있는 callback thread name : %s\n", Thread.currentThread().getName());
		// 일정 개수의 스레드를 할당해주고 요청이 들어올 때마다 스레드를 줌
		// 장점1. 소스 재사용성
		// 장점2. 메모리 공간 절약
		// --> 스레드 풀링
	}
	
	// 소멸
	@Override
	public void destroy() {	 // destroy() 한다고 해서 반드시 종료 되는건 아니다 
		super.destroy();
		System.out.printf("%s 객체 소멸 \n", DescriptionServlet.class.getName());
	}
}
