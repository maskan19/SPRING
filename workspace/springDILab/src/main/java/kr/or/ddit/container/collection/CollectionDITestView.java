package kr.or.ddit.container.collection;

import java.util.Calendar;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionDITestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = new GenericXmlApplicationContext(
				"classpath:kr/or/ddit/container/conf/collectionDI-context.xml");
		container.registerShutdownHook();
		CollectionDIVO cvo1 = container.getBean("cvo1", CollectionDIVO.class);
		System.out.println(cvo1);

		CollectionDIVO cvo2 = container.getBean("cvo2", CollectionDIVO.class);
		System.out.println(cvo2);

		ConfigurableApplicationContext container2 = new GenericXmlApplicationContext(
				"classpath:kr/or/ddit/container/conf/calendar-context.xml");
		container2.registerShutdownHook();

//		Calendar cal = Calendar.getInstance();
		Calendar cal1 = container2.getBean("now", Calendar.class);
		System.out.printf("%tc \n",cal1);
		
		try {
			Thread.sleep(3000);
			Calendar cal2 = container2.getBean("now", Calendar.class);
			System.out.printf("%tc \n",cal2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
