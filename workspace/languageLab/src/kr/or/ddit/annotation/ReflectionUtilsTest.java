package kr.or.ddit.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kr.or.ddit.utils.ReflectionUtils;

public class ReflectionUtilsTest {
	public static void main(String[] args) {
		String basePackage= "kr.or.ddit";
		List<Class<?>> clzList =  ReflectionUtils.getAllClassesAtBasePackages(basePackage);
		for(Class<?> tmp : clzList) {
			System.out.println(tmp.getName());
		}
		
		Map<Class<?>, Annotation> withGroup = ReflectionUtils.getClassesWithAnnotationAtBasePackages(FirstAnnotation.class, basePackage);
		
		for(Entry<Class<?>,Annotation> entry: withGroup.entrySet()) {
			System.err.printf("%s : %s\n", entry.getKey().getName(), entry.getValue());
			
			Class<?> targetClz = entry.getKey();
			Class<? extends Annotation> annotationType = SecondAnnotation.class;
			
			Map<Method, Annotation> methods = ReflectionUtils.getMethodsWithAnnotationAtClass(targetClz, annotationType, void.class);
			
			System.out.println(methods);
		}
		
	}
}
