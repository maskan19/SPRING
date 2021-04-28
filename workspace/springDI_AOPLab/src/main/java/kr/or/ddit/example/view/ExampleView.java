package kr.or.ddit.example.view;

import kr.or.ddit.example.dao.ExampleDAO_MySql;
import kr.or.ddit.example.dao.ExampleDAO_Oracle;
import kr.or.ddit.example.dao.IExampleDAO;
import kr.or.ddit.example.service.ExampleServiceImpl;
import kr.or.ddit.example.service.IExampleService;

public class ExampleView {

	public static void main(String[] args) {
//		view가 결합력을 떠맡은 형태
		IExampleDAO dao = new ExampleDAO_MySql();
		IExampleService service = new ExampleServiceImpl(dao);
		
		String info = service.readData("a001");
		System.out.println(info);
	}
}
