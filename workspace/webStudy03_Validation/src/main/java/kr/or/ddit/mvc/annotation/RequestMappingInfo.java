package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * 하나의 커맨드를 처리할 수 있는 핸들러 하나에 대한 정보를 가진 객체 
 * 차후 HandlerMapping의 value로 사용됨
 *
 */
public class RequestMappingInfo {

	private RequestMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;

	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}

	public RequestMappingCondition getMappingCondition() {
		return mappingCondition;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandHandler == null) ? 0 : commandHandler.hashCode());
		result = prime * result + ((handlerMethod == null) ? 0 : handlerMethod.hashCode());
		result = prime * result + ((mappingCondition == null) ? 0 : mappingCondition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestMappingInfo other = (RequestMappingInfo) obj;
		if (commandHandler == null) {
			if (other.commandHandler != null)
				return false;
		} else if (!commandHandler.equals(other.commandHandler))
			return false;
		if (handlerMethod == null) {
			if (other.handlerMethod != null)
				return false;
		} else if (!handlerMethod.equals(other.handlerMethod))
			return false;
		if (mappingCondition == null) {
			if (other.mappingCondition != null)
				return false;
		} else if (!mappingCondition.equals(other.mappingCondition))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("RequestMappingInfo [ %s : %s.%s ]\n", mappingCondition, commandHandler.getClass().getName(),
				handlerMethod.getName());
	}

}
