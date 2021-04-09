package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리를 위한 Business Logic Layer
 *
 */
public interface IProdService {
	/**
	 * 상품 상세 조회
	 * 
	 * @param prod_id
	 * @return 해당 상품이 존재하지 않는 경우, CustomException 발생
	 */
	public ProdVO retrieveProd(String prod_id);

	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO);

	public ServiceResult createProd(ProdVO prod);

	public ServiceResult modifyProd(ProdVO prod);

	public ServiceResult removeProd(String prod_id);

	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
}
