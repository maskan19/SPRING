package kr.or.ddit.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//클래스용 annotation
@Retention(RetentionPolicy.RUNTIME)//runtime까지 적용됨
public @interface Controller {

}
