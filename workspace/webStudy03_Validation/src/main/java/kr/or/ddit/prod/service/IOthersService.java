package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BuyerVO;

public interface IOthersService {

	public List<Map<String, Object>> getLprodList();
	
	public List<BuyerVO> getBuyerList(String buyer_lgu);
	
}
