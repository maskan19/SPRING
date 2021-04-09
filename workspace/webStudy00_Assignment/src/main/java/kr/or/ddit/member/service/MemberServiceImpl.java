package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDAO dao = MemberDaoImpl.getInstance();
	private IAuthenticateService authService = new AuthenticateServiceImpl();

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO savedMember = dao.selectMemberDetail(mem_id);
		if (savedMember == null) {
			// custom exception 발생
			throw new UserNotFoundException("아이디에 해당하는 회원이 존재하지 않음.");// unchecked Exception
		}
		return savedMember;
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if (dao.selectMemberDetail(member.getMem_id()) == null) {
			int rowcnt = dao.insertMember(member);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		
//		MemberVO savedMember = retrieveMember(member.getMem_id());
//		String savedPass = savedMember.getMem_pass();
//		String inputPass = member.getMem_pass();
//		ServiceResult result = null;
//		if (savedPass.equals(inputPass)) {
//			int rowcnt = dao.updateMember(member);
//			if (rowcnt > 0) {
//				result = ServiceResult.OK;
//			} else {
//				result = ServiceResult.FAIL;
//			}
//		} else {
//			result = ServiceResult.INVALIDPASSWORD;
//		}
//		return result;
		
		
		retrieveMember(member.getMem_id());//예외를 발생시킬 목적
		ServiceResult result = authService.authenticate(new MemberVO(member.getMem_id(), member.getMem_pass()));
		
		if (ServiceResult.OK.equals(result)) {
			int rowcnt = dao.updateMember(member);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
		} 
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		retrieveMember(member.getMem_id());//예외를 발생시킬 목적. 존재하는지 확인
		ServiceResult result = authService.authenticate(new MemberVO(member.getMem_id(), member.getMem_pass()));
		
		if (ServiceResult.OK.equals(result)) {
			int rowcnt = dao.deleteMember(member.getMem_id());
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
		} 
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO) {
		List<MemberVO> memberlist = null;
		memberlist = dao.selectMemberList(pagingVO);
		return memberlist;
	}

	@Override
	public int retrieveMemberCount(PagingVO<MemberVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

}
