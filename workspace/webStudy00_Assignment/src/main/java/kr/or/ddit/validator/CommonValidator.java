package kr.or.ddit.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class CommonValidator<T> {

	private Validator validator;
	{
		ValidatorFactory factory = Validation.byDefaultProvider().configure().messageInterpolator(
				new ResourceBundleMessageInterpolator(
                        new PlatformResourceBundleLocator("kr.or.ddit.messages.errorMessages")
                )
				).buildValidatorFactory();
				
				
		validator = factory.getValidator();
	}

	public boolean validate(T target, Map<String, List<String>> errors, Class<?>...groups) {
		Set<ConstraintViolation<T>> violations = validator.validate(target, groups);
		boolean valid = violations.size() == 0;
		Iterator<ConstraintViolation<T>> it = violations.iterator();
		while (it.hasNext()) {
			ConstraintViolation<T> violation = it.next();
			String propName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			List<String> already = errors.get(propName);
			if (already == null) {
				already = new ArrayList<String>();
				errors.put(propName, already);
			}
			already.add(message);
		}
		return valid;
	}
}
