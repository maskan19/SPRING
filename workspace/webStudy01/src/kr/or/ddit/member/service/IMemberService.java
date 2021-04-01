package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
/**
 * 회원 관리(CRUD)를 위한 Business Logic Layer
 * @author PC-25
 *
 */
public interface IMemberService {
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return
	 */
	public MemberVO retrieveMember(String mem_id);
	
	/**
	 * 신규등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	
	/**
	 * 자기 정보 수정
	 * @param member
	 * @return INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	
}
