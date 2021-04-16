package kr.or.ddit.exception;

public class DataNotFoundException extends RuntimeException{

	public DataNotFoundException() {
		super();

	}

	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public DataNotFoundException(String message) {
		super(message);

	}

	public DataNotFoundException(Throwable cause) {
		super(cause);

	}

}
