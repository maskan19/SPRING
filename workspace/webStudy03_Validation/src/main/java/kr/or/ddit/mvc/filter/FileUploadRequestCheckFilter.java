package kr.or.ddit.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.filter.wrapper.MultipartHttpServletRequest;

/**
 * 1. 파일이 포함된 multipart 요청인지 식별(File이 포함되지 않는 요청과 File이 업로드되고 있는 요청을 구분)
 * 2. multipart 요청이라면, 원본 요청을 wrapper로 변경(원본 요청의 구조를 변경)
 * 3. wrapper를 이용해 Part 데이터를 쉽게 핸들링할 수 있는 구조 설정
 *  
 */
public class FileUploadRequestCheckFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(FileUploadRequestCheckFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{}필터 초기화", getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String contentType = req.getContentType();
		if(contentType!=null && contentType.startsWith("multipart")) { //파일 업로드 중인 경우
			MultipartHttpServletRequest wrapper = new MultipartHttpServletRequest(req);
			chain.doFilter(wrapper, response);//변경된 요청을 내보냄
			
		} else {
			chain.doFilter(request, response);//파일이 아닌 경우는 변경할 내용이 없어 원본 요청을 그대로 내보냄
		}
		
		
	}

	@Override
	public void destroy() {
		logger.info("{}필터 소멸", getClass().getName());
	}

}
