package kr.or.ddit.mvc.annotation.resolvers;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;//static member를 임포트 함

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(PARAMETER)
@Retention(RUNTIME)
public @interface ModelAttribute {
	public String value();

}
