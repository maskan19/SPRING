package kr.or.ddit.validator.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IDRegValidator.class)
public @interface IDReg {
	String regexp() default "(?=.*[a-z])(?=.*[0-9])[a-z0-9]{4,13}";

	String placeholder() default "영어소문자, 숫자로 이루어진 13자 이하의 아이디";

	String message() default "{kr.or.ddit.validator.constraint.IDReg.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}