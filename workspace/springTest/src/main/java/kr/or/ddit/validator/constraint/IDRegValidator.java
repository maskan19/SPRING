package kr.or.ddit.validator.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class IDRegValidator implements ConstraintValidator<IDReg, String> {
	private IDReg annotation;

	@Override
	public void initialize(IDReg constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value))
			return true;
		String regexp = annotation.regexp();
		return value.matches(regexp);
	}

}