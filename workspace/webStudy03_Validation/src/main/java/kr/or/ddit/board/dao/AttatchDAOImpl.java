package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public class AttatchDAOImpl implements IAttatchDAO{

	private static AttatchDAOImpl self;
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	private AttatchDAOImpl(){
	}
	
	public static AttatchDAOImpl getInstance() {
		if(self == null) self= new AttatchDAOImpl();
		return self;
	}
	
	@Override
	public int insertAttaches(BoardVO boardVO) {
		try(SqlSession session = sessionFactory.openSession()){
			IAttatchDAO mapper = session.getMapper(AttatchDAOImpl.class);
			int cnt = mapper.insertAttaches(boardVO);
			session.commit();
			return cnt;
		}
	}
	@Override
	public AttatchVO selectAttatch(int att_no) {
		try(SqlSession session = sessionFactory.openSession()){
			IAttatchDAO mapper = session.getMapper(AttatchDAOImpl.class);
			return mapper.selectAttatch(att_no);
		}
	}
	@Override
	public int deleteAttatches(BoardVO boardVO) {
		try(SqlSession session = sessionFactory.openSession()){
			IAttatchDAO mapper = session.getMapper(AttatchDAOImpl.class);
			int cnt = mapper.insertAttaches(boardVO);
			session.commit();
			return cnt;
		}
	}
	
}