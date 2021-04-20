package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public class AttatchDAOImpl implements IAttatchDAO {

	private static AttatchDAOImpl self;
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();//새로운 트랜잭션으로 간주된다.

	public AttatchDAOImpl() {
	}

	public static AttatchDAOImpl getInstance() {
		if (self == null)
			self = new AttatchDAOImpl();
		return self;
	}

	@Override
	public int insertAttatches(BoardVO board, SqlSession session) {
		return session.insert("kr.or.ddit.board.dao.IAttatchDAO.insertAttatches", board);
	}

	@Override
	public AttatchVO selectAttatch(int att_no) {
		try (SqlSession session = sessionFactory.openSession()) {
			IAttatchDAO mapper = session.getMapper(IAttatchDAO.class);
			return mapper.selectAttatch(att_no);
		}
	}

	@Override
	public int deleteAttatches(BoardVO boardVO, SqlSession session) {
		return session.delete("kr.or.ddit.board.dao.IAttatchDAO.deleteAttatches", boardVO);
	}

	@Override
	public List<String> selectSaveNamesForDelete(BoardVO boardVO) {
		try(
				SqlSession session = sessionFactory.openSession();	
			){
				IAttatchDAO mapper = session.getMapper(IAttatchDAO.class);
				return mapper.selectSaveNamesForDelete(boardVO);
			}
	}
	
	

}