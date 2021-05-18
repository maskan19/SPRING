package kr.or.ddit.validator.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PWRegValidator.class)
public @interface PWReg {
	String regexp() default "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,13}";

	String placeholder() default "영문자, 숫자, 특수문자로 이루어진 13자 이하의 비밀번호";

	String message() default "{kr.or.ddit.validator.constraint.PWReg.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}