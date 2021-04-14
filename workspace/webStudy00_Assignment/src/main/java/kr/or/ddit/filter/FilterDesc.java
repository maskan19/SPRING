package kr.or.ddit.filter;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 필터 : 요청과 응답에 대한 전/후처리를 담당하는 재사용 가능한 객체
 * 후처리로 압축을 할 수도 있다.
 * 필터 활용 단계
 * 1. Filter 구현체 정의
 * 		1) lifecycle callback : init, destroy //코드상 관리하지 않고 서버가 관리한다.
 * 		2) filtering callback : doFilter
 * 			chain.doFilter를 기준으로 요청과 응답에 대한 필터링 구분
 * 2. 서버에 등록 : 서버는 싱글턴으로 관리(init 과 destroy는 한번씩만 실행된다), FilterChain이 형성됨
 * 		** FilterChain내에는 필터링이 되는 순서는 필터 등록 순서를 따른다.
 * 			요청에 대한 필터링과 응답의 필터링은 역순으로 처리됨.
 * 3. 필터링 요청 매핑 설정
 * 
 */
public class FilterDesc implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(FilterDesc.class);
	
	private Map<String, String> blindMap; //ipv4 : 사유

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {//필터도 라이프 사이클이 존재한다.
		// TODO Auto-generated method stub
		logger.info("{} 필터 초기화", this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// FilterChain : filter를 여러개 모아놓은 것. 스택메모리의 복귀 주소로 관리
		
		//request filtering
		HttpServletRequest req = (HttpServletRequest) request; //캐스팅을 해야 하위 메소드를 사용할 수 있음
		String uri = req.getRequestURI();
		logger.info("{}요청 필터링", uri);
		
		
		
//		
//		String ip = request.getRemoteAddr(); 
//		if(blindMap.containsKey(ip)) {
//			// 잘못된 이용자> 만료시켜야하는 요청
//			HttpServletResponse resp = (HttpServletResponse)response;
//			HttpServletRequest req = (HttpServletRequest)request;
//			
//			String reason = blindMap.get(ip);
//			logger.info("{}, 블라인드 처리 사유: {}", ip, reason);
//			req.getSession().setAttribute("msg", blindMap.get(ip));
//			resp.sendRedirect(req.getContextPath() + "/webStudy03_Validation/webapp/messages/massageView.jsp");
//			
//			
//		} else {
//			chain.doFilter(request, response);//서비스를 계속 진행.
//		}
		
		
		
		chain.doFilter(request, response);//이 메소드를 기준으로 이전은 request, 이후는 response
		//response filtering
		String mime = response.getContentType();
		logger.info("{}응답 필터링, 응답 컨텐츠 : {}" , uri, mime);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("{} 필터 소멸", this.getClass().getName());
	}

}
