package kr.or.ddit.exception;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 * 예외(Throwable) : 설계 당시에 고려하지 않은 모든 상황
 * - Error : 개발자가 처리하지 않고, VM이 제어권을 넘겨받는 예외의 한 종류(fatal error).
 * - Exception : 개발자가 처리할 수 있는 예외
 * 		- checked exception : Exception의 직계
 * 				> 예외 발생 가능한 코드가 있으면, 반드시 처리를 해야하는 예외(SQL Exception, IOException, SocketException 등)
 * 		- unchecked exception : RuntimeException의 직계
 *				> 명시적으로 처리하지 않더라도 호출자 혹은 VM에게 제어권이 전달되는 예외(NullpointerException, IllegalArgumentException)
 *
 * ** 예외 처리 방법
 * 1. 직접 처리
 * try{예외 발생 가능 코드} catch(captuable exception) {예외 처리 코드} finally{예외 발생 여부와 무관하게 처리할 코드}
 * try(closable){예외발생 가능코드}  
 * 
 * 2. 호출자에게 위임하는 처리 : throws
 * 
 * **예외 발생 방법
 * throw new 예외 객체 생성(throw가 없으면 일반 객체가 된다.)
 * 
 * ** custom 예외 정의 방법
 * 상위(예외의 특성에 따라 Exception/RuntimeException)에 예외 클래스를 상속
 *
 */
public class ExceptionDesc {
	public static void main(String[] args){
//		try {
//			String retValue = method1();
//			System.out.println(retValue);
//		}catch (IOException e) {
//			e.printStackTrace();
////			throw e;
//		}
		
		try {
			System.out.println(method2());
		}catch (RuntimeException e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
	
	private static String method2(){
		try {
			if(1==1)
	//			throw new IllegalArgumentException("강제발생예외");
				throw new SQLException("강제발생예외");	
			return "method2RetValue";
		}catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	private static String method1() throws IOException {
		try {
			if(1==1) {
				throw new IOException("강제 발생 예외");
			}
			return "method1RetValue";
		}catch (IOException e) {
			System.err.println("예외 발생했음."+e.getMessage());			
			throw e;
		}
	}
}
