package kr.or.ddit.method;

import kr.or.ddit.annotation.FirstAnnotation;

@FirstAnnotation(number=2)
public class MethodInvocation {

	public static void main(String[] args) {
		String text1 = "original";//C.pool에 저장되는 상수
		StringBuffer text2 = new StringBuffer("original");//hip에 저장되는 레퍼런스 객체
		
		callByValue(text1);
		System.out.println(text1);
		callByReference(text2);
		System.out.println(text2);
	}
	private static void callByValue(String data) {
		data = data + "append data";
	}
	
	private static void callByReference(StringBuffer data) {
		data.append("append data");
	}
}
