package kr.or.ddit.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	private Map<String, Cookie> cookieMap;
	private HttpServletRequest req; // request가 없이는 사용할 수 없음
	// 전략패턴

	public CookieUtils(HttpServletRequest req) { // 기본 생성자를 삭제해서 request객체를 무조건 만들도록
		super();
		this.req = req;
		cookieMap = new LinkedHashMap<String, Cookie>();
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie tmp : cookies) {
				cookieMap.put(tmp.getName(), tmp);
			}

		}
		
	}
	
	
	/**
	 * 쿠키 검색
	 * @param name 검색할 쿠키명
	 * @return 존재하지 않는 경우, null반환
	 */
	public Cookie getCookie (String name) {
		return cookieMap.get(name);
	}
	/**
	 * 쿠키값 조회
	 * @param name
	 * @return 존재하지 않으면 null반환
	 * @throws IOException
	 */
	public String getCookieValue(String name) throws IOException { //exception을 톰캣까지 보냄
		Cookie cookie = getCookie(name);
		String value = null;
		if(cookie!=null) {
			value = URLDecoder.decode(cookie.getValue(), "UTF-8");
		}
		return value;
	}
	
	public boolean exists(String name) {
		return cookieMap.containsKey(name);
	}
	
	
	
	
	
	

}
