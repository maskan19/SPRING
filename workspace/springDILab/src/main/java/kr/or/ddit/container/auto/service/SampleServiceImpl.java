package kr.or.ddit.container.auto.service;

import kr.or.ddit.container.auto.dao.ISampleDAO;

public class SampleServiceImpl implements ISampleService {

//	객체는 생성하지 않고 주입을 받을 용도
	private ISampleDAO dao;

//	주입받을 수 있는 곳
	public SampleServiceImpl(ISampleDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public String ReadData(String pk) {
		String raw = dao.selectData(pk);
		String info = raw + "를 가공한 information";
		return info;
	}

}
