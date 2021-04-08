package kr.or.ddit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //사용 위치는 타입(클래스)
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstAnnotation {
	String value() default "기본값";//메소드x 속성 정의(o)
	int number();
}
