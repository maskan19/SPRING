package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService {

	private static BuyerServiceImpl self;

	private BuyerServiceImpl() {

	}

	public static BuyerServiceImpl getInstance() {
		if (self == null) {
			self = new BuyerServiceImpl();
		}
		return self;
	}

	private IBuyerDAO dao = BuyerDAOImpl.getInstance();

	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO buyer = dao.selectBuyer(buyer_id);
		if (buyer == null)
			throw new CustomException(String.format("%s 에 해당하는 거래처가 없음.", buyer_id));
		return buyer;
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		ServiceResult result = ServiceResult.FAIL;
		if(dao.insertBuyer(buyer)>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		ServiceResult result = ServiceResult.FAIL;
		if(dao.updateBuyer(buyer)>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult removeBuyer(String buyer_id) {
		ServiceResult result = ServiceResult.FAIL;
		if(dao.deleteBuyer(buyer_id)>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO) {
		List<BuyerVO> buyerList = dao.selectBuyerList(pagingVO);
		if (buyerList == null)
			throw new CustomException("거래처가 없음.");
		return buyerList;
	}

	@Override
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

}
