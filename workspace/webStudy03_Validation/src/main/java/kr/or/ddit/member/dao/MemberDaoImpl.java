package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDaoImpl implements IMemberDAO {

	private static MemberDaoImpl self;

	private MemberDaoImpl() {
	}

	public static MemberDaoImpl getInstance() {
		if (self == null)
			self = new MemberDaoImpl();
		return self;
	}

	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public MemberVO selectMemberForAuth(String mem_id) {
		try (SqlSession session = sessionFactory.openSession(true);) {
//			return (MemberVO) session.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMemberForAuth", mem_id);
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			// mapper proxy를 받아와서 구현
			return mapper.selectMemberForAuth(mem_id);
		}
	}

	@Override
	public MemberVO selectMemberDetail(String mem_id) {
		try (SqlSession session = sessionFactory.openSession();) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectMemberDetail(mem_id);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		try (SqlSession session = sessionFactory.openSession();) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int cnt = mapper.insertMember(member);
			session.commit();
			return cnt;
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try (SqlSession session = sessionFactory.openSession();) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int cnt = mapper.updateMember(member);
			session.commit();
			return cnt;
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try (SqlSession session = sessionFactory.openSession();) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int cnt = mapper.deleteMember(mem_id);
			session.commit();
			return cnt;
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try (SqlSession session = sessionFactory.openSession();) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			List<MemberVO> memberList = mapper.selectMemberList(pagingVO);
			return memberList;
		}
	}

	@Override
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO) {
		try (SqlSession session = sessionFactory.openSession();) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}
}
