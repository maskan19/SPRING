package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerAdapter implements IHandlerAdapter {

	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//어떤 메소드를 호출할지 결정
		Object controllerObj = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		try {
			return (String) handlerMethod.invoke(controllerObj, req, resp);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			//handler의 access범위가 public 미만일 경우 | req, resp를 받지 않을 경우 |  
			throw new ServletException(e);
		}
	}

}
