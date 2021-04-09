package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerService {
	/**
	 * 거래처 정보 상세 조회
	 * 
	 * @param buyer_id
	 * @return 존재하지 않으면 custom exception 발생
	 */
	public BuyerVO retrieveBuyer(String buyer_id);

	/**
	 * 거래처 신규 등록
	 * 
	 * @param Buyer
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createBuyer(BuyerVO buyer);

	/**
	 * 거래처 정보 수정
	 * 
	 * @param buyer
	 * @return 존재하지 않으면 custom exception 발생 INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);

	/**
	 * 거래처 삭제
	 * 
	 * @param buyer
	 * @return 존재하지 않으면 custom exception 발생 INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeBuyer(String buyer_id);

	/**
	 * 거래처 목록 조회
	 * 
	 * @param pagingVO TODO
	 * @return 조건에 맞는 거래처이 없으면 size() ==0;
	 */
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO);

	/**
	 * 페이징 처리를 위한 거래처수 조회
	 * 
	 * @return
	 */
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVO);

}
