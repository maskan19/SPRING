package kr.or.ddit.container.auto.dao;

public interface SampleDAOImpl extends ISampleDAO {

	@Override
	default String selectData(String pk) {
		// TODO Auto-generated method stub
		return "조회된" +pk+"데이터";
	}

}
