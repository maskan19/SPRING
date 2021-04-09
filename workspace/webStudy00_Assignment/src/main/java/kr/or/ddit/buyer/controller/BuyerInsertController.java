package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class BuyerInsertController {

	private IBuyerService service = BuyerServiceImpl.getInstance();

	@RequestMapping(value = "/buyer/idCheck.do", method = RequestMethod.POST)
	public String idChecker(@RequestParam("id") String buyer_id, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, Object> resultMap = new HashMap<>();
		try {
			service.retrieveBuyer(buyer_id);
			resultMap.put("result", ServiceResult.FAIL);
		} catch (Exception e) {
			resultMap.put("result", ServiceResult.OK);
		}

		resp.setContentType(MimeType.JSON.getMime());
		try (PrintWriter out = resp.getWriter();) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, resultMap);
		}
		return null;
	}

	@RequestMapping("/buyer/buyerInsert.do")
	public String buyerInsertForm() {
		return "buyer/buyerForm";
	}

	@RequestMapping(value = "/buyer/buyerInsert.do", method = RequestMethod.POST)
	public String buyerInsert(@ModelAttribute("buyer") BuyerVO buyer, HttpServletRequest req, HttpServletResponse resp) {

//		2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);

		boolean valid = new CommonValidator<BuyerVO>().validate(buyer, errors, InsertGroup.class);

		String view = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case PKDUPLICATED:// forward
				view = "buyer/buyerForm";
				message = "아이디 중복";
				break;
			case OK: // redirect
				view = "redirect:/login/loginForm";
				break;
			default:
				message = "서버 오류, 잠시 뒤 다시 시도하세요.";
				view = "buyer/buyerForm";
				break;
			}
		} else {
			// 검증 불통
			view = "buyer/buyerForm";
		}

		req.setAttribute("message", message);
		// 중복 코드 발생
		return view;
	}
}
