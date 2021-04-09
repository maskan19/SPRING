package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

public class OthersServiceImpl implements IOthersService {

	private static OthersServiceImpl self;
	
	private OthersServiceImpl() {
		
	}
	
	public static OthersServiceImpl getInstance() {
		if (self == null)
			self = new OthersServiceImpl();
		return self;
	}
	private IOthersDAO dao = OthersDAOImpl.getInstance();
	
	@Override
	public List<Map<String, Object>> getLprodList() {
		 List<Map<String, Object>> lprodList = dao.selectLprodList();
		return lprodList;
	}

	@Override
	public List<BuyerVO> getBuyerList(String buyer_lgu) {
		 List<BuyerVO> buyerList = dao.selectBuyerList(buyer_lgu);
		return buyerList;
	}

	
}
