package kr.or.ddit.example.dao;

public class ExampleDAO_Oracle implements IExampleDAO {

	public ExampleDAO_Oracle() {
		super();
		System.out.println(getClass().getSimpleName() + "객체 생성");
	}
	public void init() { //callback method는 void. parameter를 받을 수 없음.
		System.out.println(getClass().getSimpleName() + "객체 초기화");
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName() + "객체 소멸");
	}

	@Override
	public String selectData(String pk) {
		// TODO Auto-generated method stub
		return String.format("%s로 Oracle에서 조회된 raw data", pk);
	}

}
