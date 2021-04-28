package kr.or.ddit.example.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.example.service.IExampleService;

public class ExampleSpringView {
	public static void main(String[] args) {
//		위치와 이름을 전달해 class path 에 존재하면 가져온다.
		ApplicationContext container = new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/example-context.xml");
//		IExampleService service = (IExampleService) container.getBean("exampleServiceImpl");
		IExampleService service = container.getBean("exampleServiceImpl", IExampleService.class);
		String info = service.readData("a001");
		System.out.println(info);
	
	}
}
