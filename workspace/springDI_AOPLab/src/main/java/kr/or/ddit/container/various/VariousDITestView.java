package kr.or.ddit.container.various;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class VariousDITestView {
	public static void main(String[] args) throws IOException {
		ApplicationContext container = new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/variousDI-context.xml");
		
		VariousDIVO various1 = container.getBean("vo1", VariousDIVO.class);
		System.out.println(various1);
		
		VariousDIVO various2 = container.getBean("vo2", VariousDIVO.class);
		System.out.println(various2);
		
//		ConfigurableApplicationContext cont = new GenericXmlApplicationContext("kr/or/ddit/example/conf/variousDI-context.xml");
//		VariousDIVO vo1 = cont.getBean(VariousDIVO.class);
//		System.out.println(vo1);
		
//		Resource rs = new FileSystemResource(various.getFile());
//		InputStream is = rs.getInputStream();
//		int w = is.read();
	}
}
