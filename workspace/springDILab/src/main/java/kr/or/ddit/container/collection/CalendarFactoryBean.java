package kr.or.ddit.container.collection;

import java.util.Calendar;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class CalendarFactoryBean extends AbstractFactoryBean<Calendar> {

	public CalendarFactoryBean() {
		// TODO Auto-generated constructor stub
		setSingleton(false);
	}
	
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Calendar.class;
	}

	@Override
	protected Calendar createInstance() throws Exception {
		// TODO Auto-generated method stub
		return Calendar.getInstance();
	}

	
}
