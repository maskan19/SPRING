package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapping {

	/**
	 * 하나의 요청을 처리할 수 있는 핸들러 검색
	 * @param request
	 * @return
	 */
	public RequestMappingInfo findCommandHandler(HttpServletRequest request);
}
