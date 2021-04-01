package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);

		Map<Object, Object> map = new HashMap<Object, Object>();
		dePopulate(obj, map);
		System.out.println(map);

	}

	public static void dePopulate(Object bean, Map<Object, Object> map) {
		Class clz = bean.getClass();
		Field[] fields = clz.getDeclaredFields();
		for (Field tmp : fields) {
			String varName = tmp.getName();

			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);

				Class varType = pd.getPropertyType();// 전역변수의 타입
				Method getter = pd.getReadMethod(); // getter
//				pd.getWriteMethod()//setter
				Object value = getter.invoke(bean);
				
				Method setter = pd.getWriteMethod();
				setter.invoke(bean, "");
				

				System.out.printf("%s %s = %s;\n", varType.getSimpleName(), varName, value);

				map.put(varName, value);

			} catch (IntrospectionException e) {// 자바빈 규약에 어긋날 경우
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static void reflect1(Object obj) {
		Class clz = obj.getClass();
		System.out.println(clz);
//		Field[] fields = clz.getFields(); public 변수만
		Field[] fields = clz.getDeclaredFields(); // 모든 변수

		for (Field tmp : fields) {
			String varName = tmp.getName();
			Class varType = tmp.getType();
			try {
//				tmp.setAccessible(true); //외부에서 접근 가능한 상태로 바꾸는 것
//				Object value = tmp.get(obj);
				String readMethodName = "get" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
//				clz.getMethod(readMethodName, parameterTypes)// public 메소드만
				Method readMethod = clz.getDeclaredMethod(readMethodName);
				Object value = readMethod.invoke(obj);

				System.out.printf("%s %s=%s;\n", varType.getSimpleName(), varName, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {// 메소드 이름이 잘못된 경우
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static void reflect2(Object obj) {
		Class clz = obj.getClass();
		Field[] fields = clz.getDeclaredFields();
		for (Field tmp : fields) {
			String varName = tmp.getName();

			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);

				Class varType = pd.getPropertyType();// 전역변수의 타입
				Method getter = pd.getReadMethod(); // getter
//				pd.getWriteMethod()//setter

				Object value = getter.invoke(obj);

				System.out.printf("%s %s = %s;\n", varType.getSimpleName(), varName, value);
			} catch (IntrospectionException e) {// 자바빈 규약에 어긋날 경우
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
