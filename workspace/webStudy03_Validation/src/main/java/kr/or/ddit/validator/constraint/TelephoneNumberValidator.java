package kr.or.ddit.validator.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class TelephoneNumberValidator implements ConstraintValidator<TelephoneNumber, String>{

	private TelephoneNumber annotation;
	@Override
	public void initialize(TelephoneNumber constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isBlank(value)) {
			return true;//비어있으면 검증할 수 없으므로 리턴
		}
		String regexp = annotation.regexp();
		return value.matches(regexp);
	}

	
}
