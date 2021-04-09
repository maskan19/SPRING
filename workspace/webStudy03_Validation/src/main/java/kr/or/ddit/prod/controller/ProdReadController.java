package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdReadController {
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance(); // 세션 팩토리가 싱글톤이므로 모든 DAO의 상태가 동일하다?

	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		String prod_lgu;
		prod_lgu = req.getParameter("prod_lgu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(prod_lgu);

		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}

	@RequestMapping("/prod/prodList.do")
	public String prodList(
			@RequestParam(value = "page", required = false, defaultValue="1") int currentPage,
//			@RequestParam(value = "prod_lgu", required = false) String prod_lgu,
//			@RequestParam(value = "prod_buyer", required = false) String prod_buyer,
//			@RequestParam(value = "prod_name", required = false) String prod_name, 
			@ModelAttribute("detailSearch") ProdVO detailSearch,
			HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);

//		String prodLgu = req.getParameter("prod_lgu");
//		String prodBuyer = req.getParameter("prod_buyer");
//		String prodName = req.getParameter("prod_name");
//		ProdVO detailSearch = ProdVO.builder().prod_lgu(prod_lgu).prod_buyer(prod_buyer).prod_name(prod_name).build();

//		builder패턴을 사용하면 생성자를 대신할 수 있다.

//		String pageParam = req.getParameter("page");
//		int currentPage = 1;
//		if (pageParam != null && pageParam.matches("\\d+")) {
//			currentPage = Integer.parseInt(pageParam);
//		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setDetailSearch(detailSearch);
		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);

		String accept = req.getHeader("Accept");
		String view = null;
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			// 마샬링+직렬화
			ObjectMapper mapper = new ObjectMapper();
			try (PrintWriter out = resp.getWriter();) {
				mapper.writeValue(out, pagingVO);// write 계열이 마샬링
			}
		} else {
			req.setAttribute("pagingVO", pagingVO);
			view = "prod/prodList";
		}
		return view;
	}

	@RequestMapping("/prod/prodView.do")
	public String prodView(@RequestParam(value = "what", required = true, defaultValue = "") String prod_id,
			HttpServletRequest req, HttpServletResponse resp) {

		ProdVO prod = service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		return "prod/prodView";
	}

}
