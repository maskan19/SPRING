package kr.or.ddit.filter;

import java.io.IOException;
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

//@WebFilter("/*") //모든 요청
public class BlindFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(BlindFilter.class);

	private Map<String, String> blindMap; // ipv4 : 사유
	private String view = "/messages/massageView.jsp";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {// 필터도 라이프 사이클이 존재한다.
		// TODO Auto-generated method stub
		logger.info("{} 필터 초기화", this.getClass().getName());
		blindMap = new LinkedHashMap<String, String>();
		blindMap.put("127.0.0.1", "블라인드 처리");
		blindMap.put("192.168.0.120", "블라인드 처리");
		blindMap.put("192.168.0.118", "수미 바보");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// FilterChain : filter를 여러개 모아놓은 것. 스택메모리의 복귀 주소로 관리
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();

		boolean pass = uri.contains(view);
		if (!pass) {
			String ip = request.getRemoteAddr();
			boolean blackList = blindMap.containsKey(ip);
			pass = !blackList;
			if (blackList) { // 잘못된 이용자> 만료시켜야하는 요청> redirect
				String reason = blindMap.get(ip);
				logger.info("{}, 블라인드 처리 사유: {}", ip, reason);
				req.getSession().setAttribute("msg", blindMap.get(ip));
			}
		}

		if (pass) {
			chain.doFilter(request, response);// 서비스를 정상 진행.
		} else {
			resp.sendRedirect(req.getContextPath() + view);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("{} 필터 소멸", this.getClass().getName());
	}

}