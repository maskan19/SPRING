package kr.or.ddit.mvc.annotation.resolvers;

public class BadRequestException extends RuntimeException {

	public BadRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}// unchecked, runtime 계열

}