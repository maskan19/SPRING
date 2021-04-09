package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerAdapter {

	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
