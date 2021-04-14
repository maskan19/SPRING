package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestChangeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {//필터링 콜백
//		ServletRequest 객체는 set 메소드가 없기 때문에 원하는 기능을 수행하기 위해 HttpServletRequest로 변경(상속 구조 활용)
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		//HttpServletRequestWrapper는 HttpServletRequest의 자식
		 //요청을 변경하기 위해 wrapper 생성
		request = new HttpServletRequestWrapper(req){
			@Override
			public String getParameter(String name) {
				if("what".equals(name)){
					return "P101000001";//what 파라미터를 P101000001로 변경
				}else {
					return super.getParameter(name);//원본 요청 그대로 리턴
				}
			}
		};
		
		chain.doFilter(request, response);
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
