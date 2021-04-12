package kr.or.ddit.filter.security;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 접근 제어를 통한 자원의 보호
 * 1. 인증(authentication) : 본인 여부를 호가인하는 신원확인 절차
 * 2. 인가(authorization) : 보호 자원에 접근할 수 있는 역할이 부여되어있는지 확인하는 절차(권한 확인)
 * 
 * (보호할 필요가 있는 자원가 보호가 필요 없는 자원의 구분이 우선되어야한다.)
 * 
 *
 */
public class AuthenticationFilterOrigin implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilterOrigin.class);
	private Map<String, String []> securedResources;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("{}필터 초기화",getClass().getName());
		//securedResources 읽고 초기화
//		/webStudy03_Validation/src/main/resources/kr/or/ddit/securedResource.properties
		
		String qualifiedName = "/kr/or/ddit/securedResource.properties";//   앞에 / 가 있어야 전체  클래스 패스부터 찾는다.
		URL url = AuthenticationFilterOrigin.class.getResource(qualifiedName); //type자체. 현재 파일의 경로부터 시작함
		
		logger.info("URL : {}",url);
		String temp = null;
		try (
				InputStream is = AuthenticationFilterOrigin.class.getResourceAsStream(qualifiedName);//1차. 
				InputStreamReader reader = new InputStreamReader(is); // byte를 char로 변환
				BufferedReader br = new BufferedReader(reader);// char
		) {
			String[] lines = null;
			while ((temp = br.readLine()) != null) {
				lines = temp.split("\n");
			}
			for(String line : lines ) {
				String key = line.substring(0, line.indexOf("="));
				String valueString = line.substring(line.indexOf("=")+1);
		
				if(valueString.contains(",")) {
					String[] value = valueString.split(",");
					securedResources.put(key, value);
					logger.info("key : {}  / value : {}", key, value);
				}else {
					String[] value= {valueString};
					securedResources.put(key, value);
					logger.info("key : {}  / value : {}", key, value);
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 1. 보호자원에 대한 요청인지 식별
		HttpServletRequest req = (HttpServletRequest) request;
		String findPath = req.getRequestURL().substring(req.getContextPath().length());
		logger.info("findPath : {}", findPath);
		// 2. 보호 필요 자원
		if (securedResources.containsKey(findPath)) {
			String role = (String) req.getSession().getAttribute("role");
			String[] value = securedResources.get(findPath);
			// 3. 인증 여부 확인
			if(Arrays.binarySearch(value, role)!=-1) {
				// 인증시 통과
				chain.doFilter(request, response);// 서비스를 정상 진행.
			}else {
				// 미인증시 loginForm으로 이동
				((HttpServletResponse)response).sendRedirect(req.getContextPath());
			}
			// 2-1. 보호할 필요 없는 자원 : 통과
		} else {
			chain.doFilter(request, response);// 서비스를 정상 진행.
		}
		// 통과

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
