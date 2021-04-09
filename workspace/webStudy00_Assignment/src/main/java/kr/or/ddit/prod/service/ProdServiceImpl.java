package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	private static ProdServiceImpl self;

	private ProdServiceImpl() {
	}

	public static ProdServiceImpl getInstance() {
		if (self == null)
			self = new ProdServiceImpl();
		return self;
	}

	private IProdDAO dao = ProdDAOImpl.getInstance();

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if (prod == null)
			throw new CustomException(String.format("%s 에 해당하는 상품이 없음.", prod_id));
		return prod;
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
		List<ProdVO> prodList = dao.selectProdList(pagingVO);
		if (prodList == null)
			throw new CustomException("상품이 없음.");
		return prodList;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = ServiceResult.FAIL;
		if(dao.insertProd(prod)>0) {
			result = ServiceResult.OK;
		};
		return result;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = ServiceResult.FAIL;
		if(dao.updateProd(prod)>0) {
			result = ServiceResult.OK;
		};
		return result;
	}

	@Override
	public ServiceResult removeProd(String prod_id) {
		ServiceResult result = ServiceResult.FAIL;
		if(dao.deleteProd(prod_id)>0) {
			result = ServiceResult.OK;
		};
		return result;
	}

	public int selectTotalRecord(PagingVO<ProdVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

}
