package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.IHandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestPartArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletSpecArgumentResolver;

public class HandlerAdapter implements IHandlerAdapter {
	private List<IHandlerMethodArgumentResolver> argumentResolvers;

	public HandlerAdapter() {
		argumentResolvers = new ArrayList<IHandlerMethodArgumentResolver>();
		argumentResolvers.add(new ServletSpecArgumentResolver());
		argumentResolvers.add(new ModelAttributeArgumentResolver());
		argumentResolvers.add(new RequestParamArgumentResolver());
		argumentResolvers.add(new RequestPartArgumentResolver());
	}
	
	public void addHandletMethodArgumentResolver(IHandlerMethodArgumentResolver...resolvers) {
		argumentResolvers.addAll(Arrays.asList(resolvers));
	}
	

	private IHandlerMethodArgumentResolver findArgumentResolver(Parameter parameter) {
		IHandlerMethodArgumentResolver finded = null;
		for (IHandlerMethodArgumentResolver resolver : argumentResolvers) {
			if (resolver.isSupported(parameter)) {
				finded = resolver;
				break;
			}
		}
		return finded;
	}

	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 어떤 메소드를 호출할지 결정
		Object controllerObj = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		int parameterCount = handlerMethod.getParameterCount();
		Parameter[] parameters = handlerMethod.getParameters();
		try {
			if (parameterCount == 0) {
				return (String) handlerMethod.invoke(controllerObj); // 파라미터가 아무것도 없는 경우
			}
			Object[] parameterValues = new Object[parameterCount];

			for (int idx = 0; idx < parameterCount; idx++) {
				Parameter parameter = parameters[idx];
				Class<?> parameterType = parameter.getType();
				IHandlerMethodArgumentResolver resolver = findArgumentResolver(parameter);
				if (resolver == null) {
					throw new ServletException(String.format("%s 타입의 핸들러 메소드 아규먼트는 처리할 수 없음", parameterType.getName()));
				}
				parameterValues[idx] = resolver.argumentResolve(parameter, request, response);
			}
			return (String) handlerMethod.invoke(controllerObj, parameterValues);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// handler의 access범위가 public 미만일 경우 | req, resp를 받지 않을 경우 |
			throw new ServletException(e);
		} catch (BadRequestException e) { //에러를 보내기 위해 response를 쓰지 않아도 됨
			response.sendError(400, e.getMessage());
			return null;
		}
	}

}
