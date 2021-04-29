package kr.or.ddit.example.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import kr.or.ddit.example.dao.IExampleDAO;

@Service
@Scope("prototype")
public class ExampleServiceImpl implements IExampleService {

//	1. new 키워드로 인스턴스 직접 생성
//	private IExampleDAO dao = new ExampleDAO_MySql();

//	2. Factory Object Pattern
//	private IExampleDAO dao = ExampleDAOFactory.getExampleDAO();

//	3. 전략 패턴(Dependency Injection)  : 전략의 주입자가 필요하다.
//	setter 주입 : optional 전략일 경우에만 사용할 수 있음
	private IExampleDAO dao;

	public ExampleServiceImpl() {
		super();
		System.out.println(getClass().getSimpleName() + "객체 생성-기본생성자");
	}

	@Resource(name="mySql")
//	@Inject
	@Required
	public void setDao(IExampleDAO dao) {
		this.dao = dao;
		System.out.println(getClass().getSimpleName() + "에서 setter injection 받음");
	}

	public void init() { //callback method는 void. parameter를 받을 수 없음.
		System.out.println(getClass().getSimpleName() + "객체 초기화");
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName() + "객체 소멸");
	}
	
//	생성자 주입 : 기본 생성자를 통해서 필수적으로 입력받음
	public ExampleServiceImpl(IExampleDAO dao) {
		super();
		this.dao = dao;
		System.out.println(getClass().getSimpleName() + "객체 생성-아규먼트 있는 생성자");
	}

	@Override
	public String readData(String pk) {
		String rawData = dao.selectData(pk);
		String info = rawData + "를 가공한 information";
//		if(1==1)
//			throw new RuntimeException("강제 발생 예외");
		return info;
	}

}
