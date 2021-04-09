package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerDAO {
	/**
	 * PK 를 기준으로 한명의 거래처 조회(인증용)
	 * 
	 * @param buyer_id
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public BuyerVO selectBuyer(String buyer_id);

	/**
	 * 거래처 정보 상세 조회
	 * 
	 * @param buyer_id
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public BuyerVO selectBuyerDetail(String buyer_id);

	/**
	 * 거래처 신규 등록
	 * 
	 * @param buyer
	 * @return 등록된 row count > 0 성공
	 */
	public int insertBuyer(BuyerVO buyer);

	/**
	 * 거래처 정보 수정
	 * 
	 * @param buyer
	 * @return 수정된 row count > 0 성공
	 */
	public int updateBuyer(BuyerVO buyer);

	/**
	 * 거래처 정보 삭제
	 * 
	 * @param buyer_id
	 * @return 삭제된 row count > 0 성공
	 */
	public int deleteBuyer(String buyer_id);

	/**
	 * 전체 거래처 목록 조회
	 * 
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없다면, size()==0
	 */
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);

	/**
	 * 전체 데이터 수 조회
	 * 
	 * @param pagingVO
	 * @return 전체 데이터 수
	 */
	public int selectTotalRecord(PagingVO<BuyerVO> pagingVO);

}
