package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.mvc.filter.wrapper.MultipartHttpServletRequest;

/**
 * @RequestPart 어노테이션으로 설정된 핸들러 메소드 아규먼트를 처리할 처리자
 *
 */
public class RequestPartArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		// annotation과 parameter클래스 확인하기
		Class<?> parameterType = parameter.getType();
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);

		// annotation의 존재여부 + parameterType이 multipartFile인지
		boolean supported = annotation != null && (MultipartFile.class.equals(parameterType)
				|| (parameterType.isArray() && MultipartFile.class.equals(parameterType.getComponentType()))); //배열 파라미터

		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request가 변경되었는지 확인
		if (!(request instanceof MultipartHttpServletRequest)) {// 멀티파트가 아니면
			throw new ServletException("현재 요청은 multipart요청이 아님");
		}

		RequestPart annotation = parameter.getAnnotation(RequestPart.class);

		String partName = annotation.value();
		boolean required = annotation.required();

		MultipartHttpServletRequest wrapper = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = wrapper.getFiles(partName);
		if (required && files == null) {
			throw new BadRequestException(partName + "에 해당하는 파일이 업로드되지 않음");
		}

		Class<?> parameterType = parameter.getType();
		Object retValue = null;

		if (files != null && files.size() > 0) {// 업로드된 Part가 있음
			if(parameterType.isArray()) {
				MultipartFile[] array = new MultipartFile[files.size()];
				retValue = files.toArray(array);//리스트를 어레이로
			}else {
				retValue = files.get(0);
			}
		}

		return retValue;

	}

}
